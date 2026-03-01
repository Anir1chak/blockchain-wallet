package com.wallet.transaction.repository;

import com.wallet.transaction.entity.MultiSigTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MultiSigTransactionRepository extends JpaRepository<MultiSigTransaction, UUID> {
    
    Optional<MultiSigTransaction> findByTxHash(String txHash);
    
    List<MultiSigTransaction> findByMultiSigWalletIdOrderByCreatedAtDesc(UUID multiSigWalletId);
    
    List<MultiSigTransaction> findByMultiSigWalletIdAndStatus(UUID multiSigWalletId, String status);
    
    List<MultiSigTransaction> findByStatus(String status);
}
