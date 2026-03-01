package com.blockchain.wallet.repository;

import com.blockchain.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    
    List<Wallet> findByUserId(String userId);
    
    Optional<Wallet> findByAddress(String address);
    
    boolean existsByAddress(String address);
    
    List<Wallet> findByUserIdAndType(String userId, Wallet.WalletType type);
    
    List<Wallet> findByUserIdAndNetwork(String userId, Wallet.NetworkType network);
}
