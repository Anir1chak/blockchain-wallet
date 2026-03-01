package com.blockchain.wallet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "wallets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private String userId;
    
    @Column(nullable = false, unique = true)
    private String address;
    
    @Column(nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WalletType type;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NetworkType network;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String encryptedPrivateKey;
    
    @Column(columnDefinition = "TEXT")
    private String encryptedMnemonic;
    
    @Column
    private Integer derivationIndex;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    public enum WalletType {
        SINGLE_SIG, MULTI_SIG
    }
    
    public enum NetworkType {
        ETHEREUM_MAINNET, ETHEREUM_SEPOLIA, POLYGON_MAINNET, POLYGON_MUMBAI
    }
}
