package com.wallet.transaction.repository;

import com.wallet.transaction.entity.Signature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SignatureRepository extends JpaRepository<Signature, UUID> {
    
    List<Signature> findByMultiSigTxId(UUID multiSigTxId);
    
    Optional<Signature> findByMultiSigTxIdAndSignerAddress(UUID multiSigTxId, String signerAddress);
    
    long countByMultiSigTxId(UUID multiSigTxId);
    
    boolean existsByMultiSigTxIdAndSignerAddress(UUID multiSigTxId, String signerAddress);
}
