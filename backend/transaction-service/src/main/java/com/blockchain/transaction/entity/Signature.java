package com.blockchain.transaction.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "signatures")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Signature {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "multisig_tx_id", nullable = false)
    private MultiSigTransaction multiSigTransaction;
    
    @Column(nullable = false)
    private String signerAddress;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String signature;
    
    @CreationTimestamp
    private LocalDateTime signedAt;
}
