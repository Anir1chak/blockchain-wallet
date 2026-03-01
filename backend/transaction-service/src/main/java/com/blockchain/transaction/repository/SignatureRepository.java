package com.blockchain.transaction.repository;

import com.blockchain.transaction.entity.MultiSigTransaction;
import com.blockchain.transaction.entity.Signature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SignatureRepository extends JpaRepository<Signature, UUID> {
    
    List<Signature> findByMultiSigTransaction(MultiSigTransaction multiSigTransaction);
    
    Optional<Signature> findByMultiSigTransactionAndSignerAddress(MultiSigTransaction multiSigTransaction, String signerAddress);
    
    boolean existsByMultiSigTransactionAndSignerAddress(MultiSigTransaction multiSigTransaction, String signerAddress);
    
    int countByMultiSigTransaction(MultiSigTransaction multiSigTransaction);
}
