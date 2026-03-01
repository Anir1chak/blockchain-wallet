package com.wallet.transaction.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "multi_sig_transactions")
public class MultiSigTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tx_hash", unique = true)
    private String txHash;

    @Column(name = "multi_sig_wallet_id", nullable = false)
    private UUID multiSigWalletId;

    @Column(name = "to_address", nullable = false, length = 42)
    private String toAddress;

    @Column(nullable = false, length = 78)
    private String amount;

    @Column(columnDefinition = "TEXT")
    private String data;

    @Column(name = "required_signatures", nullable = false)
    private int requiredSignatures;

    @Column(name = "current_signatures")
    private int currentSignatures = 0;

    @Column(nullable = false, length = 50)
    private String status;

    private Long nonce;

    @Column(name = "executed_at")
    private LocalDateTime executedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public UUID getMultiSigWalletId() {
        return multiSigWalletId;
    }

    public void setMultiSigWalletId(UUID multiSigWalletId) {
        this.multiSigWalletId = multiSigWalletId;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getRequiredSignatures() {
        return requiredSignatures;
    }

    public void setRequiredSignatures(int requiredSignatures) {
        this.requiredSignatures = requiredSignatures;
    }

    public int getCurrentSignatures() {
        return currentSignatures;
    }

    public void setCurrentSignatures(int currentSignatures) {
        this.currentSignatures = currentSignatures;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getNonce() {
        return nonce;
    }

    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }

    public LocalDateTime getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
