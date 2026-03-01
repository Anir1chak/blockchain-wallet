package com.blockchain.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class BlockchainDto {
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BalanceResponse {
        private String address;
        private String balanceWei;
        private String balanceEth;
        private String balanceUsd;
        private String network;
        private long timestamp;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GasPricesResponse {
        private String slow;
        private String standard;
        private String fast;
        private String instant;
        private String baseFee;
        private String network;
        private long timestamp;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BlockNumberResponse {
        private long blockNumber;
        private String network;
        private long timestamp;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressValidationResponse {
        private boolean isValid;
        private boolean isContract;
        private String checksumAddress;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NetworkStatusResponse {
        private String network;
        private String chainId;
        private long latestBlock;
        private String peerCount;
        private boolean isSyncing;
        private String gasPrice;
        private long timestamp;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenBalanceResponse {
        private String walletAddress;
        private String tokenAddress;
        private String tokenSymbol;
        private String tokenName;
        private int decimals;
        private String balance;
        private String balanceFormatted;
    }
}
