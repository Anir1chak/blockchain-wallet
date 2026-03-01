package com.blockchain.transaction.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "multisig_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MultiSigTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private UUID walletId;
    
    @Column(nullable = false)
    private String initiatorAddress;
    
    @Column(nullable = false)
    private String toAddress;
    
    @Column(nullable = false, precision = 36, scale = 18)
    private BigDecimal amount;
    
    @Column
    private String description;
    
    @Column(nullable = false)
    private Integer signaturesRequired;
    
    @Column(nullable = false)
    private Integer signaturesCollected;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MultiSigStatus status;
    
    @Column
    private String executedTxHash;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @Column
    private LocalDateTime expiresAt;
    
    @Column
    private LocalDateTime executedAt;
    
    public enum MultiSigStatus {
        PENDING, READY, EXECUTED, EXPIRED, CANCELLED
    }
}
