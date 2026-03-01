package com.blockchain.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class TransactionDto {
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SendTransactionRequest {
        private String walletId;
        private String toAddress;
        private String amount;
        private String password;
        private String gasSpeed;
        private String data;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMultiSigTxRequest {
        private String walletId;
        private String initiatorAddress;
        private String toAddress;
        private String amount;
        private String description;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignMultiSigTxRequest {
        private String signerAddress;
        private String password;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionResponse {
        private String txHash;
        private String fromAddress;
        private String toAddress;
        private String amount;
        private String gasPrice;
        private String gasUsed;
        private String status;
        private String type;
        private long blockNumber;
        private long timestamp;
        private String network;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionHistoryResponse {
        private List<TransactionResponse> transactions;
        private int totalCount;
        private int page;
        private int pageSize;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GasEstimateResponse {
        private String gasLimit;
        private String slowGasPrice;
        private String standardGasPrice;
        private String fastGasPrice;
        private String instantGasPrice;
        private String slowTotalCost;
        private String standardTotalCost;
        private String fastTotalCost;
        private String instantTotalCost;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultiSigTxResponse {
        private String multiSigTxId;
        private String walletId;
        private String toAddress;
        private String amount;
        private String description;
        private int signaturesCollected;
        private int signaturesRequired;
        private List<SignatureInfo> signatures;
        private String status;
        private long createdAt;
        private long expiresAt;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignatureInfo {
        private String signerAddress;
        private String signature;
        private long signedAt;
    }
}
