package com.blockchain.wallet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "signers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Signer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "multisig_wallet_id", nullable = false)
    private MultiSigWallet multiSigWallet;
    
    @Column(nullable = false)
    private String address;
    
    @Column
    private String name;
    
    @Column(nullable = false)
    private Boolean isOwner;
    
    @CreationTimestamp
    private LocalDateTime addedAt;
}
