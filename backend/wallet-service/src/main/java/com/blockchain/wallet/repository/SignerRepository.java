package com.blockchain.wallet.repository;

import com.blockchain.wallet.entity.MultiSigWallet;
import com.blockchain.wallet.entity.Signer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SignerRepository extends JpaRepository<Signer, UUID> {
    
    List<Signer> findByMultiSigWallet(MultiSigWallet multiSigWallet);
    
    Optional<Signer> findByMultiSigWalletAndAddress(MultiSigWallet multiSigWallet, String address);
    
    boolean existsByMultiSigWalletAndAddress(MultiSigWallet multiSigWallet, String address);
    
    void deleteByMultiSigWalletAndAddress(MultiSigWallet multiSigWallet, String address);
}
