package com.wallet.transaction.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "signatures")
public class Signature {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "multi_sig_tx_id", nullable = false)
    private UUID multiSigTxId;

    @Column(name = "signer_address", nullable = false, length = 42)
    private String signerAddress;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String signature;

    @Column(name = "signed_at")
    private LocalDateTime signedAt;

    @PrePersist
    protected void onCreate() {
        signedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMultiSigTxId() {
        return multiSigTxId;
    }

    public void setMultiSigTxId(UUID multiSigTxId) {
        this.multiSigTxId = multiSigTxId;
    }

    public String getSignerAddress() {
        return signerAddress;
    }

    public void setSignerAddress(String signerAddress) {
        this.signerAddress = signerAddress;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public LocalDateTime getSignedAt() {
        return signedAt;
    }

    public void setSignedAt(LocalDateTime signedAt) {
        this.signedAt = signedAt;
    }
}
