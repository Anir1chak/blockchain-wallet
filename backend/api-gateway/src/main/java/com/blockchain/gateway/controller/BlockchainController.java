package com.blockchain.gateway.controller;

import com.blockchain.blockchain.grpc.*;
import com.blockchain.gateway.dto.BlockchainDto;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/blockchain")
@Slf4j
public class BlockchainController {
    
    @GrpcClient("blockchain-service")
    private BlockchainServiceGrpc.BlockchainServiceBlockingStub blockchainServiceStub;
    
    @GetMapping("/balance/{address}")
    public ResponseEntity<?> getBalance(@PathVariable String address,
                                         @RequestParam(defaultValue = "ETHEREUM_SEPOLIA") String network) {
        log.info("REST: Getting balance for address: {} on network: {}", address, network);
        
        try {
            BalanceResponse response = blockchainServiceStub.getBalance(
                    GetBalanceRequest.newBuilder()
                            .setAddress(address)
                            .setNetwork(network)
                            .build()
            );
            
            return ResponseEntity.ok(
                    BlockchainDto.BalanceResponse.builder()
                            .address(response.getAddress())
                            .balanceWei(response.getBalanceWei())
                            .balanceEth(response.getBalanceEth())
                            .balanceUsd(response.getBalanceUsd())
                            .network(response.getNetwork())
                            .timestamp(response.getTimestamp())
                            .build()
            );
        } catch (StatusRuntimeException e) {
            log.error("gRPC error getting balance", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @PostMapping("/balances")
    public ResponseEntity<?> getBalances(@RequestBody List<String> addresses,
                                          @RequestParam(defaultValue = "ETHEREUM_SEPOLIA") String network) {
        log.info("REST: Getting balances for {} addresses", addresses.size());
        
        try {
            GetBalancesResponse response = blockchainServiceStub.getBalances(
                    GetBalancesRequest.newBuilder()
                            .addAllAddresses(addresses)
                            .setNetwork(network)
                            .build()
            );
            
            List<BlockchainDto.BalanceResponse> balances = response.getBalancesList().stream()
                    .map(b -> BlockchainDto.BalanceResponse.builder()
                            .address(b.getAddress())
                            .balanceWei(b.getBalanceWei())
                            .balanceEth(b.getBalanceEth())
                            .balanceUsd(b.getBalanceUsd())
                            .network(b.getNetwork())
                            .timestamp(b.getTimestamp())
                            .build())
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(balances);
        } catch (StatusRuntimeException e) {
            log.error("gRPC error getting balances", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @GetMapping("/gas-prices")
    public ResponseEntity<?> getGasPrices(@RequestParam(defaultValue = "ETHEREUM_SEPOLIA") String network) {
        log.info("REST: Getting gas prices for network: {}", network);
        
        try {
            GasPricesResponse response = blockchainServiceStub.getGasPrices(
                    GetGasPricesRequest.newBuilder()
                            .setNetwork(network)
                            .build()
            );
            
            return ResponseEntity.ok(
                    BlockchainDto.GasPricesResponse.builder()
                            .slow(response.getSlow())
                            .standard(response.getStandard())
                            .fast(response.getFast())
                            .instant(response.getInstant())
                            .baseFee(response.getBaseFee())
                            .network(response.getNetwork())
                            .timestamp(response.getTimestamp())
                            .build()
            );
        } catch (StatusRuntimeException e) {
            log.error("gRPC error getting gas prices", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @GetMapping("/block-number")
    public ResponseEntity<?> getBlockNumber(@RequestParam(defaultValue = "ETHEREUM_SEPOLIA") String network) {
        log.info("REST: Getting block number for network: {}", network);
        
        try {
            BlockNumberResponse response = blockchainServiceStub.getBlockNumber(
                    GetBlockNumberRequest.newBuilder()
                            .setNetwork(network)
                            .build()
            );
            
            return ResponseEntity.ok(
                    BlockchainDto.BlockNumberResponse.builder()
                            .blockNumber(response.getBlockNumber())
                            .network(response.getNetwork())
                            .timestamp(response.getTimestamp())
                            .build()
            );
        } catch (StatusRuntimeException e) {
            log.error("gRPC error getting block number", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @GetMapping("/validate-address/{address}")
    public ResponseEntity<?> validateAddress(@PathVariable String address) {
        log.info("REST: Validating address: {}", address);
        
        try {
            ValidateAddressResponse response = blockchainServiceStub.validateAddress(
                    ValidateAddressRequest.newBuilder()
                            .setAddress(address)
                            .build()
            );
            
            return ResponseEntity.ok(
                    BlockchainDto.AddressValidationResponse.builder()
                            .isValid(response.getIsValid())
                            .isContract(response.getIsContract())
                            .checksumAddress(response.getChecksumAddress())
                            .build()
            );
        } catch (StatusRuntimeException e) {
            log.error("gRPC error validating address", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @GetMapping("/network-status")
    public ResponseEntity<?> getNetworkStatus(@RequestParam(defaultValue = "ETHEREUM_SEPOLIA") String network) {
        log.info("REST: Getting network status for: {}", network);
        
        try {
            NetworkStatusResponse response = blockchainServiceStub.getNetworkStatus(
                    GetNetworkStatusRequest.newBuilder()
                            .setNetwork(network)
                            .build()
            );
            
            return ResponseEntity.ok(
                    BlockchainDto.NetworkStatusResponse.builder()
                            .network(response.getNetwork())
                            .chainId(response.getChainId())
                            .latestBlock(response.getLatestBlock())
                            .peerCount(response.getPeerCount())
                            .isSyncing(response.getIsSyncing())
                            .gasPrice(response.getGasPrice())
                            .timestamp(response.getTimestamp())
                            .build()
            );
        } catch (StatusRuntimeException e) {
            log.error("gRPC error getting network status", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @GetMapping("/token-balance")
    public ResponseEntity<?> getTokenBalance(@RequestParam String walletAddress,
                                              @RequestParam String tokenAddress,
                                              @RequestParam(defaultValue = "ETHEREUM_SEPOLIA") String network) {
        log.info("REST: Getting token balance for wallet: {} token: {}", walletAddress, tokenAddress);
        
        try {
            TokenBalanceResponse response = blockchainServiceStub.getTokenBalance(
                    GetTokenBalanceRequest.newBuilder()
                            .setWalletAddress(walletAddress)
                            .setTokenAddress(tokenAddress)
                            .setNetwork(network)
                            .build()
            );
            
            return ResponseEntity.ok(
                    BlockchainDto.TokenBalanceResponse.builder()
                            .walletAddress(response.getWalletAddress())
                            .tokenAddress(response.getTokenAddress())
                            .tokenSymbol(response.getTokenSymbol())
                            .tokenName(response.getTokenName())
                            .decimals(response.getDecimals())
                            .balance(response.getBalance())
                            .balanceFormatted(response.getBalanceFormatted())
                            .build()
            );
        } catch (StatusRuntimeException e) {
            log.error("gRPC error getting token balance", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    record ErrorResponse(String message) {}
}
