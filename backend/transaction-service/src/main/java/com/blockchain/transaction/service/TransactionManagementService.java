package com.blockchain.transaction.service;

import com.blockchain.transaction.entity.MultiSigTransaction;
import com.blockchain.transaction.entity.Signature;
import com.blockchain.transaction.entity.Transaction;
import com.blockchain.transaction.repository.MultiSigTransactionRepository;
import com.blockchain.transaction.repository.SignatureRepository;
import com.blockchain.transaction.repository.TransactionRepository;
import com.blockchain.wallet.grpc.*;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionManagementService {
    
    private final TransactionRepository transactionRepository;
    private final MultiSigTransactionRepository multiSigTransactionRepository;
    private final SignatureRepository signatureRepository;
    private final Map<String, Web3j> web3jClients;
    
    @GrpcClient("wallet-service")
    private WalletServiceGrpc.WalletServiceBlockingStub walletServiceStub;
    
    @Transactional
    public Transaction sendTransaction(UUID walletId, String toAddress, String amountWei, 
                                        String network, String gasSpeed) {
        log.info("Sending transaction from wallet: {} to: {}", walletId, toAddress);
        
        try {
            WalletResponse walletResponse = walletServiceStub.getWallet(
                    GetWalletRequest.newBuilder()
                            .setWalletId(walletId.toString())
                            .build()
            );
            
            String fromAddress = walletResponse.getAddress();
            Web3j web3j = getWeb3jClient(network);
            
            EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                    fromAddress, DefaultBlockParameterName.PENDING).send();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            
            BigInteger gasPrice = getGasPrice(web3j, gasSpeed);
            BigInteger gasLimit = BigInteger.valueOf(21000);
            BigInteger value = new BigInteger(amountWei);
            
            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                    nonce, gasPrice, gasLimit, toAddress, value
            );
            
            Transaction transaction = Transaction.builder()
                    .walletId(walletId)
                    .fromAddress(fromAddress)
                    .toAddress(toAddress)
                    .amount(new BigDecimal(amountWei))
                    .gasPrice(new BigDecimal(gasPrice))
                    .gasLimit(gasLimit)
                    .status(Transaction.TransactionStatus.PENDING)
                    .type(Transaction.TransactionType.SEND)
                    .network(network)
                    .nonce(nonce.longValue())
                    .build();
            
            transaction = transactionRepository.save(transaction);
            log.info("Transaction created: {}", transaction.getId());
            
            return transaction;
        } catch (StatusRuntimeException e) {
            log.error("gRPC error calling wallet service", e);
            throw new RuntimeException("Failed to get wallet info: " + e.getStatus().getDescription());
        } catch (Exception e) {
            log.error("Failed to send transaction", e);
            throw new RuntimeException("Transaction failed: " + e.getMessage());
        }
    }
    
    public Transaction getTransaction(String txHash, String network) {
        return transactionRepository.findByTxHash(txHash)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + txHash));
    }
    
    public Page<Transaction> getTransactionHistory(UUID walletId, int page, int pageSize, 
                                                    Transaction.TransactionType filterType) {
        PageRequest pageable = PageRequest.of(page, pageSize);
        
        if (filterType != null) {
            return transactionRepository.findByWalletIdAndTypeOrderByCreatedAtDesc(walletId, filterType, pageable);
        }
        return transactionRepository.findByWalletIdOrderByCreatedAtDesc(walletId, pageable);
    }
    
    public GasEstimate estimateGas(String fromAddress, String toAddress, String amountWei, 
                                    String data, String network) {
        try {
            Web3j web3j = getWeb3jClient(network);
            
            BigInteger baseGasPrice = web3j.ethGasPrice().send().getGasPrice();
            BigInteger gasLimit = BigInteger.valueOf(21000);
            
            BigInteger slowPrice = baseGasPrice.multiply(BigInteger.valueOf(80)).divide(BigInteger.valueOf(100));
            BigInteger standardPrice = baseGasPrice;
            BigInteger fastPrice = baseGasPrice.multiply(BigInteger.valueOf(120)).divide(BigInteger.valueOf(100));
            BigInteger instantPrice = baseGasPrice.multiply(BigInteger.valueOf(150)).divide(BigInteger.valueOf(100));
            
            return new GasEstimate(
                    gasLimit,
                    slowPrice, standardPrice, fastPrice, instantPrice,
                    gasLimit.multiply(slowPrice),
                    gasLimit.multiply(standardPrice),
                    gasLimit.multiply(fastPrice),
                    gasLimit.multiply(instantPrice)
            );
        } catch (Exception e) {
            log.error("Failed to estimate gas", e);
            throw new RuntimeException("Gas estimation failed: " + e.getMessage());
        }
    }
    
    @Transactional
    public MultiSigTransaction createMultiSigTransaction(UUID walletId, String initiatorAddress,
                                                          String toAddress, String amount, String description) {
        log.info("Creating multi-sig transaction for wallet: {}", walletId);
        
        try {
            MultiSigWalletResponse multiSigWallet = walletServiceStub.getMultiSigWallet(
                    GetMultiSigRequest.newBuilder()
                            .setWalletId(walletId.toString())
                            .build()
            );
            
            MultiSigTransaction multiSigTx = MultiSigTransaction.builder()
                    .walletId(walletId)
                    .initiatorAddress(initiatorAddress)
                    .toAddress(toAddress)
                    .amount(new BigDecimal(amount))
                    .description(description)
                    .signaturesRequired(multiSigWallet.getThreshold())
                    .signaturesCollected(0)
                    .status(MultiSigTransaction.MultiSigStatus.PENDING)
                    .expiresAt(LocalDateTime.now().plusDays(7)) // 7 days expiry
                    .build();
            
            multiSigTx = multiSigTransactionRepository.save(multiSigTx);
            log.info("Multi-sig transaction created: {}", multiSigTx.getId());
            
            return multiSigTx;
        } catch (StatusRuntimeException e) {
            log.error("gRPC error", e);
            throw new RuntimeException("Failed to get multi-sig wallet info: " + e.getStatus().getDescription());
        }
    }
    
    @Transactional
    public MultiSigTransaction signMultiSigTransaction(UUID multiSigTxId, String signerAddress, String signatureData) {
        log.info("Signing multi-sig transaction: {} by: {}", multiSigTxId, signerAddress);
        
        MultiSigTransaction multiSigTx = multiSigTransactionRepository.findById(multiSigTxId)
                .orElseThrow(() -> new IllegalArgumentException("Multi-sig transaction not found"));
        
        if (multiSigTx.getStatus() != MultiSigTransaction.MultiSigStatus.PENDING) {
            throw new IllegalStateException("Transaction is not in pending state");
        }
        
        if (signatureRepository.existsByMultiSigTransactionAndSignerAddress(multiSigTx, signerAddress)) {
            throw new IllegalArgumentException("Signer has already signed this transaction");
        }
        
        Signature signature = Signature.builder()
                .multiSigTransaction(multiSigTx)
                .signerAddress(signerAddress)
                .signature(signatureData)
                .build();
        signatureRepository.save(signature);
        
        int newCount = multiSigTx.getSignaturesCollected() + 1;
        multiSigTx.setSignaturesCollected(newCount);
        
        if (newCount >= multiSigTx.getSignaturesRequired()) {
            multiSigTx.setStatus(MultiSigTransaction.MultiSigStatus.READY);
        }
        
        return multiSigTransactionRepository.save(multiSigTx);
    }
    
    public List<MultiSigTransaction> getPendingMultiSigTransactions(UUID walletId) {
        return multiSigTransactionRepository.findByWalletIdAndStatusOrderByCreatedAtDesc(
                walletId, MultiSigTransaction.MultiSigStatus.PENDING);
    }
    
    public List<Signature> getSignatures(MultiSigTransaction multiSigTx) {
        return signatureRepository.findByMultiSigTransaction(multiSigTx);
    }
    
    @Transactional
    public Transaction executeMultiSigTransaction(UUID multiSigTxId) {
        log.info("Executing multi-sig transaction: {}", multiSigTxId);
        
        MultiSigTransaction multiSigTx = multiSigTransactionRepository.findById(multiSigTxId)
                .orElseThrow(() -> new IllegalArgumentException("Multi-sig transaction not found"));
        
        if (multiSigTx.getStatus() != MultiSigTransaction.MultiSigStatus.READY) {
            throw new IllegalStateException("Transaction is not ready for execution");
        }
        
        Transaction transaction = Transaction.builder()
                .walletId(multiSigTx.getWalletId())
                .fromAddress(multiSigTx.getInitiatorAddress())
                .toAddress(multiSigTx.getToAddress())
                .amount(multiSigTx.getAmount())
                .status(Transaction.TransactionStatus.PENDING)
                .type(Transaction.TransactionType.SEND)
                .network("ETHEREUM_SEPOLIA")
                .build();
        
        transaction = transactionRepository.save(transaction);
        
        multiSigTx.setStatus(MultiSigTransaction.MultiSigStatus.EXECUTED);
        multiSigTx.setExecutedAt(LocalDateTime.now());
        multiSigTx.setExecutedTxHash(transaction.getId().toString()); // Placeholder
        multiSigTransactionRepository.save(multiSigTx);
        
        return transaction;
    }
    
    private Web3j getWeb3jClient(String network) {
        Web3j client = web3jClients.get(network);
        if (client == null) {
            client = web3jClients.get("ETHEREUM_SEPOLIA"); // Default
        }
        return client;
    }
    
    private BigInteger getGasPrice(Web3j web3j, String speed) throws Exception {
        BigInteger basePrice = web3j.ethGasPrice().send().getGasPrice();
        
        return switch (speed.toUpperCase()) {
            case "SLOW" -> basePrice.multiply(BigInteger.valueOf(80)).divide(BigInteger.valueOf(100));
            case "FAST" -> basePrice.multiply(BigInteger.valueOf(120)).divide(BigInteger.valueOf(100));
            case "INSTANT" -> basePrice.multiply(BigInteger.valueOf(150)).divide(BigInteger.valueOf(100));
            default -> basePrice;
        };
    }
    
    public record GasEstimate(
            BigInteger gasLimit,
            BigInteger slowPrice, BigInteger standardPrice, BigInteger fastPrice, BigInteger instantPrice,
            BigInteger slowTotal, BigInteger standardTotal, BigInteger fastTotal, BigInteger instantTotal
    ) {}
}
