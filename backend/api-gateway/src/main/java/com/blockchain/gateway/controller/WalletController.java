package com.blockchain.gateway.controller;

import com.blockchain.gateway.dto.WalletDto;
import com.blockchain.wallet.grpc.*;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wallets")
@Slf4j
public class WalletController {
    
    @GrpcClient("wallet-service")
    private WalletServiceGrpc.WalletServiceBlockingStub walletServiceStub;
    
    @PostMapping
    public ResponseEntity<?> createWallet(@RequestBody WalletDto.CreateWalletRequest request) {
        log.info("REST: Creating wallet for user: {}", request.getUserId());
        
        try {
            CreateWalletResponse response = walletServiceStub.createWallet(
                    CreateWalletRequest.newBuilder()
                            .setUserId(request.getUserId())
                            .setName(request.getName())
                            .setNetwork(mapNetwork(request.getNetwork()))
                            .setPassword(request.getPassword())
                            .build()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    WalletDto.WalletResponse.builder()
                            .walletId(response.getWalletId())
                            .address(response.getAddress())
                            .name(response.getName())
                            .type("SINGLE_SIG")
                            .network(response.getNetwork().name())
                            .mnemonic(response.getMnemonic())
                            .createdAt(response.getCreatedAt())
                            .build()
            );
        } catch (StatusRuntimeException e) {
            log.error("gRPC error creating wallet", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @GetMapping("/{walletId}")
    public ResponseEntity<?> getWallet(@PathVariable String walletId) {
        log.info("REST: Getting wallet: {}", walletId);
        
        try {
            WalletResponse response = walletServiceStub.getWallet(
                    GetWalletRequest.newBuilder()
                            .setWalletId(walletId)
                            .build()
            );
            
            return ResponseEntity.ok(
                    WalletDto.WalletResponse.builder()
                            .walletId(response.getWalletId())
                            .address(response.getAddress())
                            .name(response.getName())
                            .type(response.getType().name())
                            .network(response.getNetwork().name())
                            .createdAt(response.getCreatedAt())
                            .updatedAt(response.getUpdatedAt())
                            .build()
            );
        } catch (StatusRuntimeException e) {
            log.error("gRPC error getting wallet", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserWallets(@PathVariable String userId) {
        log.info("REST: Getting wallets for user: {}", userId);
        
        try {
            GetUserWalletsResponse response = walletServiceStub.getUserWallets(
                    GetUserWalletsRequest.newBuilder()
                            .setUserId(userId)
                            .build()
            );
            
            List<WalletDto.WalletResponse> wallets = response.getWalletsList().stream()
                    .map(w -> WalletDto.WalletResponse.builder()
                            .walletId(w.getWalletId())
                            .address(w.getAddress())
                            .name(w.getName())
                            .type(w.getType().name())
                            .network(w.getNetwork().name())
                            .createdAt(w.getCreatedAt())
                            .updatedAt(w.getUpdatedAt())
                            .build())
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(wallets);
        } catch (StatusRuntimeException e) {
            log.error("gRPC error getting user wallets", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @PostMapping("/import")
    public ResponseEntity<?> importWallet(@RequestBody WalletDto.ImportWalletRequest request) {
        log.info("REST: Importing wallet for user: {}", request.getUserId());
        
        try {
            ImportWalletRequest.Builder builder = ImportWalletRequest.newBuilder()
                    .setUserId(request.getUserId())
                    .setName(request.getName())
                    .setNetwork(mapNetwork(request.getNetwork()))
                    .setPassword(request.getPassword());
            
            if (request.getMnemonic() != null && !request.getMnemonic().isEmpty()) {
                builder.setMnemonic(request.getMnemonic());
            } else if (request.getPrivateKey() != null && !request.getPrivateKey().isEmpty()) {
                builder.setPrivateKey(request.getPrivateKey());
            }
            
            CreateWalletResponse response = walletServiceStub.importWallet(builder.build());
            
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    WalletDto.WalletResponse.builder()
                            .walletId(response.getWalletId())
                            .address(response.getAddress())
                            .name(response.getName())
                            .type("SINGLE_SIG")
                            .network(response.getNetwork().name())
                            .createdAt(response.getCreatedAt())
                            .build()
            );
        } catch (StatusRuntimeException e) {
            log.error("gRPC error importing wallet", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @PostMapping("/multisig")
    public ResponseEntity<?> createMultiSigWallet(@RequestBody WalletDto.CreateMultiSigRequest request) {
        log.info("REST: Creating multi-sig wallet for user: {}", request.getUserId());
        
        try {
            MultiSigWalletResponse response = walletServiceStub.createMultiSigWallet(
                    CreateMultiSigRequest.newBuilder()
                            .setUserId(request.getUserId())
                            .setName(request.getName())
                            .setNetwork(mapNetwork(request.getNetwork()))
                            .setThreshold(request.getThreshold())
                            .addAllSignerAddresses(request.getSignerAddresses())
                            .build()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(mapMultiSigResponse(response));
        } catch (StatusRuntimeException e) {
            log.error("gRPC error creating multi-sig wallet", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @GetMapping("/multisig/{walletId}")
    public ResponseEntity<?> getMultiSigWallet(@PathVariable String walletId) {
        log.info("REST: Getting multi-sig wallet: {}", walletId);
        
        try {
            MultiSigWalletResponse response = walletServiceStub.getMultiSigWallet(
                    GetMultiSigRequest.newBuilder()
                            .setWalletId(walletId)
                            .build()
            );
            
            return ResponseEntity.ok(mapMultiSigResponse(response));
        } catch (StatusRuntimeException e) {
            log.error("gRPC error getting multi-sig wallet", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @PostMapping("/multisig/{walletId}/signers")
    public ResponseEntity<?> addSigner(@PathVariable String walletId, 
                                        @RequestBody WalletDto.AddSignerRequest request) {
        log.info("REST: Adding signer to wallet: {}", walletId);
        
        try {
            MultiSigWalletResponse response = walletServiceStub.addSigner(
                    AddSignerRequest.newBuilder()
                            .setWalletId(walletId)
                            .setSignerAddress(request.getSignerAddress())
                            .setSignerName(request.getSignerName())
                            .build()
            );
            
            return ResponseEntity.ok(mapMultiSigResponse(response));
        } catch (StatusRuntimeException e) {
            log.error("gRPC error adding signer", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @DeleteMapping("/multisig/{walletId}/signers/{signerAddress}")
    public ResponseEntity<?> removeSigner(@PathVariable String walletId, 
                                           @PathVariable String signerAddress) {
        log.info("REST: Removing signer from wallet: {}", walletId);
        
        try {
            MultiSigWalletResponse response = walletServiceStub.removeSigner(
                    RemoveSignerRequest.newBuilder()
                            .setWalletId(walletId)
                            .setSignerAddress(signerAddress)
                            .build()
            );
            
            return ResponseEntity.ok(mapMultiSigResponse(response));
        } catch (StatusRuntimeException e) {
            log.error("gRPC error removing signer", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    private NetworkType mapNetwork(String network) {
        if (network == null) return NetworkType.ETHEREUM_SEPOLIA;
        return switch (network.toUpperCase()) {
            case "ETHEREUM_MAINNET" -> NetworkType.ETHEREUM_MAINNET;
            case "POLYGON_MAINNET" -> NetworkType.POLYGON_MAINNET;
            case "POLYGON_MUMBAI" -> NetworkType.POLYGON_MUMBAI;
            default -> NetworkType.ETHEREUM_SEPOLIA;
        };
    }
    
    private WalletDto.MultiSigWalletResponse mapMultiSigResponse(MultiSigWalletResponse response) {
        return WalletDto.MultiSigWalletResponse.builder()
                .walletId(response.getWalletId())
                .address(response.getAddress())
                .name(response.getName())
                .network(response.getNetwork().name())
                .threshold(response.getThreshold())
                .totalSigners(response.getTotalSigners())
                .signers(response.getSignersList().stream()
                        .map(s -> WalletDto.SignerInfo.builder()
                                .address(s.getAddress())
                                .name(s.getName())
                                .isOwner(s.getIsOwner())
                                .build())
                        .collect(Collectors.toList()))
                .createdAt(response.getCreatedAt())
                .build();
    }
    
    record ErrorResponse(String message) {}
}
