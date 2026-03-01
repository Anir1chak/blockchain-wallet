package com.blockchain.transaction.repository;

import com.blockchain.transaction.entity.MultiSigTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MultiSigTransactionRepository extends JpaRepository<MultiSigTransaction, UUID> {
    
    List<MultiSigTransaction> findByWalletIdAndStatusOrderByCreatedAtDesc(UUID walletId, MultiSigTransaction.MultiSigStatus status);
    
    List<MultiSigTransaction> findByWalletIdOrderByCreatedAtDesc(UUID walletId);
}
