package com.blockchain.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class WalletDto {
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateWalletRequest {
        private String userId;
        private String name;
        private String network;
        private String password;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImportWalletRequest {
        private String userId;
        private String name;
        private String network;
        private String password;
        private String privateKey;
        private String mnemonic;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMultiSigRequest {
        private String userId;
        private String name;
        private String network;
        private int threshold;
        private java.util.List<String> signerAddresses;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddSignerRequest {
        private String signerAddress;
        private String signerName;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WalletResponse {
        private String walletId;
        private String address;
        private String name;
        private String type;
        private String network;
        private String mnemonic;
        private long createdAt;
        private long updatedAt;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultiSigWalletResponse {
        private String walletId;
        private String address;
        private String name;
        private String network;
        private int threshold;
        private int totalSigners;
        private java.util.List<SignerInfo> signers;
        private long createdAt;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignerInfo {
        private String address;
        private String name;
        private boolean isOwner;
    }
}
