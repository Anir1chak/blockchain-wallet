package com.blockchain.wallet.repository;

import com.blockchain.wallet.entity.MultiSigWallet;
import com.blockchain.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MultiSigWalletRepository extends JpaRepository<MultiSigWallet, UUID> {
    
    Optional<MultiSigWallet> findByWallet(Wallet wallet);
    
    Optional<MultiSigWallet> findByWalletId(UUID walletId);
}
