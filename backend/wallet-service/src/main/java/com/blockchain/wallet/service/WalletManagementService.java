package com.blockchain.wallet.service;

import com.blockchain.wallet.entity.MultiSigWallet;
import com.blockchain.wallet.entity.Signer;
import com.blockchain.wallet.entity.Wallet;
import com.blockchain.wallet.repository.MultiSigWalletRepository;
import com.blockchain.wallet.repository.SignerRepository;
import com.blockchain.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletManagementService {
    
    private final WalletRepository walletRepository;
    private final MultiSigWalletRepository multiSigWalletRepository;
    private final SignerRepository signerRepository;
    private final EncryptionService encryptionService;
    
    @Transactional
    public WalletCreationResult createWallet(String userId, String name, Wallet.NetworkType network, String password) {
        log.info("Creating new wallet for user: {}", userId);
        
        try {
            // Generate mnemonic (BIP39)
            byte[] entropy = new byte[16]; // 128 bits for 12 words
            new SecureRandom().nextBytes(entropy);
            String mnemonic = MnemonicUtils.generateMnemonic(entropy);
            
            // Derive credentials from mnemonic (BIP44)
            byte[] seed = MnemonicUtils.generateSeed(mnemonic, password);
            Bip32ECKeyPair masterKeypair = Bip32ECKeyPair.generateKeyPair(seed);
            
            // Ethereum derivation path: m/44'/60'/0'/0/0
            int[] derivationPath = {44 | 0x80000000, 60 | 0x80000000, 0x80000000, 0, 0};
            Bip32ECKeyPair derivedKeyPair = Bip32ECKeyPair.deriveKeyPair(masterKeypair, derivationPath);
            
            Credentials credentials = Credentials.create(derivedKeyPair);
            String address = credentials.getAddress();
            String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
            
            String encryptedPrivateKey = encryptionService.encrypt(privateKey);
            String encryptedMnemonic = encryptionService.encrypt(mnemonic);
            
            Wallet wallet = Wallet.builder()
                    .userId(userId)
                    .address(address)
                    .name(name)
                    .type(Wallet.WalletType.SINGLE_SIG)
                    .network(network)
                    .encryptedPrivateKey(encryptedPrivateKey)
                    .encryptedMnemonic(encryptedMnemonic)
                    .derivationIndex(0)
                    .build();
            
            wallet = walletRepository.save(wallet);
            log.info("Wallet created successfully: {}", wallet.getId());
            
            return new WalletCreationResult(wallet, mnemonic);
        } catch (Exception e) {
            log.error("Failed to create wallet", e);
            throw new RuntimeException("Wallet creation failed", e);
        }
    }
    
    @Transactional
    public WalletCreationResult importWalletFromMnemonic(String userId, String name, Wallet.NetworkType network, 
                                                          String mnemonic, String password) {
        log.info("Importing wallet from mnemonic for user: {}", userId);
        
        try {
            if (!MnemonicUtils.validateMnemonic(mnemonic)) {
                throw new IllegalArgumentException("Invalid mnemonic phrase");
            }
            
            byte[] seed = MnemonicUtils.generateSeed(mnemonic, password);
            Bip32ECKeyPair masterKeypair = Bip32ECKeyPair.generateKeyPair(seed);
            
            int[] derivationPath = {44 | 0x80000000, 60 | 0x80000000, 0x80000000, 0, 0};
            Bip32ECKeyPair derivedKeyPair = Bip32ECKeyPair.deriveKeyPair(masterKeypair, derivationPath);
            
            Credentials credentials = Credentials.create(derivedKeyPair);
            String address = credentials.getAddress();
            
            if (walletRepository.existsByAddress(address)) {
                throw new IllegalArgumentException("Wallet with this address already exists");
            }
            
            String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
            String encryptedPrivateKey = encryptionService.encrypt(privateKey);
            String encryptedMnemonic = encryptionService.encrypt(mnemonic);
            
            Wallet wallet = Wallet.builder()
                    .userId(userId)
                    .address(address)
                    .name(name)
                    .type(Wallet.WalletType.SINGLE_SIG)
                    .network(network)
                    .encryptedPrivateKey(encryptedPrivateKey)
                    .encryptedMnemonic(encryptedMnemonic)
                    .derivationIndex(0)
                    .build();
            
            wallet = walletRepository.save(wallet);
            log.info("Wallet imported successfully: {}", wallet.getId());
            
            return new WalletCreationResult(wallet, null); // Don't return mnemonic on import
        } catch (Exception e) {
            log.error("Failed to import wallet", e);
            throw new RuntimeException("Wallet import failed: " + e.getMessage(), e);
        }
    }
    
    @Transactional
    public WalletCreationResult importWalletFromPrivateKey(String userId, String name, Wallet.NetworkType network, 
                                                            String privateKey, String password) {
        log.info("Importing wallet from private key for user: {}", userId);
        
        try {
            Credentials credentials = Credentials.create(privateKey);
            String address = credentials.getAddress();
            
            if (walletRepository.existsByAddress(address)) {
                throw new IllegalArgumentException("Wallet with this address already exists");
            }
            
            String encryptedPrivateKey = encryptionService.encrypt(privateKey);
            
            Wallet wallet = Wallet.builder()
                    .userId(userId)
                    .address(address)
                    .name(name)
                    .type(Wallet.WalletType.SINGLE_SIG)
                    .network(network)
                    .encryptedPrivateKey(encryptedPrivateKey)
                    .build();
            
            wallet = walletRepository.save(wallet);
            log.info("Wallet imported successfully: {}", wallet.getId());
            
            return new WalletCreationResult(wallet, null);
        } catch (Exception e) {
            log.error("Failed to import wallet from private key", e);
            throw new RuntimeException("Wallet import failed: " + e.getMessage(), e);
        }
    }
    
    @Cacheable(value = "wallets", key = "#walletId")
    public Wallet getWallet(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found: " + walletId));
    }
    
    public List<Wallet> getUserWallets(String userId) {
        return walletRepository.findByUserId(userId);
    }
    
    public String getDecryptedPrivateKey(UUID walletId) {
        Wallet wallet = getWallet(walletId);
        return encryptionService.decrypt(wallet.getEncryptedPrivateKey());
    }
    
    @Transactional
    @CacheEvict(value = "wallets", key = "#walletId")
    public MultiSigWallet createMultiSigWallet(String userId, String name, Wallet.NetworkType network,
                                                int threshold, List<String> signerAddresses) {
        log.info("Creating multi-sig wallet with {}-of-{} for user: {}", threshold, signerAddresses.size(), userId);
        
        if (threshold > signerAddresses.size()) {
            throw new IllegalArgumentException("Threshold cannot be greater than number of signers");
        }
        
        if (threshold < 1) {
            throw new IllegalArgumentException("Threshold must be at least 1");
        }
        
        WalletCreationResult result = createWallet(userId, name, network, "");
        Wallet wallet = result.wallet();
        wallet.setType(Wallet.WalletType.MULTI_SIG);
        walletRepository.save(wallet);
        
        MultiSigWallet multiSigWallet = MultiSigWallet.builder()
                .wallet(wallet)
                .threshold(threshold)
                .totalSigners(signerAddresses.size())
                .build();
        multiSigWallet = multiSigWalletRepository.save(multiSigWallet);
        
        for (int i = 0; i < signerAddresses.size(); i++) {
            Signer signer = Signer.builder()
                    .multiSigWallet(multiSigWallet)
                    .address(signerAddresses.get(i))
                    .name("Signer " + (i + 1))
                    .isOwner(i == 0)
                    .build();
            signerRepository.save(signer);
        }
        
        log.info("Multi-sig wallet created: {}", multiSigWallet.getId());
        return multiSigWallet;
    }
    
    public MultiSigWallet getMultiSigWallet(UUID walletId) {
        return multiSigWalletRepository.findByWalletId(walletId)
                .orElseThrow(() -> new IllegalArgumentException("Multi-sig wallet not found: " + walletId));
    }
    
    public List<Signer> getSigners(MultiSigWallet multiSigWallet) {
        return signerRepository.findByMultiSigWallet(multiSigWallet);
    }
    
    @Transactional
    public MultiSigWallet addSigner(UUID walletId, String signerAddress, String signerName) {
        MultiSigWallet multiSigWallet = getMultiSigWallet(walletId);
        
        if (signerRepository.existsByMultiSigWalletAndAddress(multiSigWallet, signerAddress)) {
            throw new IllegalArgumentException("Signer already exists");
        }
        
        Signer signer = Signer.builder()
                .multiSigWallet(multiSigWallet)
                .address(signerAddress)
                .name(signerName)
                .isOwner(false)
                .build();
        signerRepository.save(signer);
        
        multiSigWallet.setTotalSigners(multiSigWallet.getTotalSigners() + 1);
        return multiSigWalletRepository.save(multiSigWallet);
    }
    
    @Transactional
    public MultiSigWallet removeSigner(UUID walletId, String signerAddress) {
        MultiSigWallet multiSigWallet = getMultiSigWallet(walletId);
        
        if (multiSigWallet.getTotalSigners() <= multiSigWallet.getThreshold()) {
            throw new IllegalArgumentException("Cannot remove signer: would violate threshold requirement");
        }
        
        Signer signer = signerRepository.findByMultiSigWalletAndAddress(multiSigWallet, signerAddress)
                .orElseThrow(() -> new IllegalArgumentException("Signer not found"));
        
        if (signer.getIsOwner()) {
            throw new IllegalArgumentException("Cannot remove owner signer");
        }
        
        signerRepository.delete(signer);
        
        multiSigWallet.setTotalSigners(multiSigWallet.getTotalSigners() - 1);
        return multiSigWalletRepository.save(multiSigWallet);
    }
    
    public record WalletCreationResult(Wallet wallet, String mnemonic) {}
}
