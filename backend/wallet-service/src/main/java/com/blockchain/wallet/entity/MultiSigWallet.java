package com.blockchain.wallet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "multisig_wallets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MultiSigWallet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;
    
    @Column(nullable = false)
    private Integer threshold;
    
    @Column(nullable = false)
    private Integer totalSigners;
    
    @Column
    private String contractAddress;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
}
