package com.blockchain.transaction.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private UUID walletId;
    
    @Column(unique = true)
    private String txHash;
    
    @Column(nullable = false)
    private String fromAddress;
    
    @Column(nullable = false)
    private String toAddress;
    
    @Column(nullable = false, precision = 36, scale = 18)
    private BigDecimal amount;
    
    @Column(precision = 36, scale = 18)
    private BigDecimal gasPrice;
    
    @Column
    private BigInteger gasUsed;
    
    @Column
    private BigInteger gasLimit;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;
    
    @Column
    private Long blockNumber;
    
    @Column(nullable = false)
    private String network;
    
    @Column(columnDefinition = "TEXT")
    private String data;
    
    @Column
    private Long nonce;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @Column
    private LocalDateTime confirmedAt;
    
    public enum TransactionStatus {
        PENDING, CONFIRMED, FAILED, CANCELLED
    }
    
    public enum TransactionType {
        SEND, RECEIVE, SWAP, CONTRACT_CALL
    }
}
