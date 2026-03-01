package com.blockchain.wallet.grpc;

import com.blockchain.wallet.entity.MultiSigWallet;
import com.blockchain.wallet.entity.Signer;
import com.blockchain.wallet.entity.Wallet;
import com.blockchain.wallet.service.WalletManagementService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@GrpcService
@RequiredArgsConstructor
@Slf4j
public class WalletGrpcService extends WalletServiceGrpc.WalletServiceImplBase {
    
    private final WalletManagementService walletManagementService;
    
    @Override
    public void createWallet(CreateWalletRequest request, StreamObserver<CreateWalletResponse> responseObserver) {
        log.info("gRPC: Creating wallet for user: {}", request.getUserId());
        
        try {
            Wallet.NetworkType networkType = mapNetworkType(request.getNetwork());
            WalletManagementService.WalletCreationResult result = walletManagementService.createWallet(
                    request.getUserId(),
                    request.getName(),
                    networkType,
                    request.getPassword()
            );
            
            CreateWalletResponse response = CreateWalletResponse.newBuilder()
                    .setWalletId(result.wallet().getId().toString())
                    .setAddress(result.wallet().getAddress())
                    .setMnemonic(result.mnemonic() != null ? result.mnemonic() : "")
                    .setName(result.wallet().getName())
                    .setNetwork(request.getNetwork())
                    .setCreatedAt(result.wallet().getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to create wallet", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void getWallet(GetWalletRequest request, StreamObserver<WalletResponse> responseObserver) {
        log.info("gRPC: Getting wallet: {}", request.getWalletId());
        
        try {
            Wallet wallet = walletManagementService.getWallet(UUID.fromString(request.getWalletId()));
            
            WalletResponse response = mapToWalletResponse(wallet);
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to get wallet", e);
            responseObserver.onError(io.grpc.Status.NOT_FOUND
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void getUserWallets(GetUserWalletsRequest request, StreamObserver<GetUserWalletsResponse> responseObserver) {
        log.info("gRPC: Getting wallets for user: {}", request.getUserId());
        
        try {
            List<Wallet> wallets = walletManagementService.getUserWallets(request.getUserId());
            
            GetUserWalletsResponse.Builder responseBuilder = GetUserWalletsResponse.newBuilder();
            for (Wallet wallet : wallets) {
                responseBuilder.addWallets(mapToWalletResponse(wallet));
            }
            
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to get user wallets", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void importWallet(ImportWalletRequest request, StreamObserver<CreateWalletResponse> responseObserver) {
        log.info("gRPC: Importing wallet for user: {}", request.getUserId());
        
        try {
            Wallet.NetworkType networkType = mapNetworkType(request.getNetwork());
            WalletManagementService.WalletCreationResult result;
            
            if (request.hasMnemonic()) {
                result = walletManagementService.importWalletFromMnemonic(
                        request.getUserId(),
                        request.getName(),
                        networkType,
                        request.getMnemonic(),
                        request.getPassword()
                );
            } else if (request.hasPrivateKey()) {
                result = walletManagementService.importWalletFromPrivateKey(
                        request.getUserId(),
                        request.getName(),
                        networkType,
                        request.getPrivateKey(),
                        request.getPassword()
                );
            } else {
                throw new IllegalArgumentException("Either mnemonic or private key must be provided");
            }
            
            CreateWalletResponse response = CreateWalletResponse.newBuilder()
                    .setWalletId(result.wallet().getId().toString())
                    .setAddress(result.wallet().getAddress())
                    .setName(result.wallet().getName())
                    .setNetwork(request.getNetwork())
                    .setCreatedAt(result.wallet().getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to import wallet", e);
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void createMultiSigWallet(CreateMultiSigRequest request, StreamObserver<MultiSigWalletResponse> responseObserver) {
        log.info("gRPC: Creating multi-sig wallet for user: {}", request.getUserId());
        
        try {
            Wallet.NetworkType networkType = mapNetworkType(request.getNetwork());
            List<String> signerAddresses = request.getSignerAddressesList();
            
            MultiSigWallet multiSigWallet = walletManagementService.createMultiSigWallet(
                    request.getUserId(),
                    request.getName(),
                    networkType,
                    request.getThreshold(),
                    signerAddresses
            );
            
            MultiSigWalletResponse response = mapToMultiSigResponse(multiSigWallet);
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to create multi-sig wallet", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void getMultiSigWallet(GetMultiSigRequest request, StreamObserver<MultiSigWalletResponse> responseObserver) {
        log.info("gRPC: Getting multi-sig wallet: {}", request.getWalletId());
        
        try {
            MultiSigWallet multiSigWallet = walletManagementService.getMultiSigWallet(
                    UUID.fromString(request.getWalletId())
            );
            
            MultiSigWalletResponse response = mapToMultiSigResponse(multiSigWallet);
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to get multi-sig wallet", e);
            responseObserver.onError(io.grpc.Status.NOT_FOUND
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void addSigner(AddSignerRequest request, StreamObserver<MultiSigWalletResponse> responseObserver) {
        log.info("gRPC: Adding signer to wallet: {}", request.getWalletId());
        
        try {
            MultiSigWallet multiSigWallet = walletManagementService.addSigner(
                    UUID.fromString(request.getWalletId()),
                    request.getSignerAddress(),
                    request.getSignerName()
            );
            
            MultiSigWalletResponse response = mapToMultiSigResponse(multiSigWallet);
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to add signer", e);
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void removeSigner(RemoveSignerRequest request, StreamObserver<MultiSigWalletResponse> responseObserver) {
        log.info("gRPC: Removing signer from wallet: {}", request.getWalletId());
        
        try {
            MultiSigWallet multiSigWallet = walletManagementService.removeSigner(
                    UUID.fromString(request.getWalletId()),
                    request.getSignerAddress()
            );
            
            MultiSigWalletResponse response = mapToMultiSigResponse(multiSigWallet);
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to remove signer", e);
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    // Helper methods
    private Wallet.NetworkType mapNetworkType(NetworkType grpcNetworkType) {
        return switch (grpcNetworkType) {
            case ETHEREUM_MAINNET -> Wallet.NetworkType.ETHEREUM_MAINNET;
            case ETHEREUM_SEPOLIA -> Wallet.NetworkType.ETHEREUM_SEPOLIA;
            case POLYGON_MAINNET -> Wallet.NetworkType.POLYGON_MAINNET;
            case POLYGON_MUMBAI -> Wallet.NetworkType.POLYGON_MUMBAI;
            default -> Wallet.NetworkType.ETHEREUM_SEPOLIA;
        };
    }
    
    private NetworkType mapToGrpcNetworkType(Wallet.NetworkType networkType) {
        return switch (networkType) {
            case ETHEREUM_MAINNET -> NetworkType.ETHEREUM_MAINNET;
            case ETHEREUM_SEPOLIA -> NetworkType.ETHEREUM_SEPOLIA;
            case POLYGON_MAINNET -> NetworkType.POLYGON_MAINNET;
            case POLYGON_MUMBAI -> NetworkType.POLYGON_MUMBAI;
        };
    }
    
    private WalletType mapToGrpcWalletType(Wallet.WalletType walletType) {
        return switch (walletType) {
            case SINGLE_SIG -> WalletType.SINGLE_SIG;
            case MULTI_SIG -> WalletType.MULTI_SIG;
        };
    }
    
    private WalletResponse mapToWalletResponse(Wallet wallet) {
        return WalletResponse.newBuilder()
                .setWalletId(wallet.getId().toString())
                .setAddress(wallet.getAddress())
                .setName(wallet.getName())
                .setType(mapToGrpcWalletType(wallet.getType()))
                .setNetwork(mapToGrpcNetworkType(wallet.getNetwork()))
                .setCreatedAt(wallet.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                .setUpdatedAt(wallet.getUpdatedAt().toEpochSecond(ZoneOffset.UTC))
                .build();
    }
    
    private MultiSigWalletResponse mapToMultiSigResponse(MultiSigWallet multiSigWallet) {
        List<Signer> signers = walletManagementService.getSigners(multiSigWallet);
        
        return MultiSigWalletResponse.newBuilder()
                .setWalletId(multiSigWallet.getWallet().getId().toString())
                .setAddress(multiSigWallet.getWallet().getAddress())
                .setName(multiSigWallet.getWallet().getName())
                .setNetwork(mapToGrpcNetworkType(multiSigWallet.getWallet().getNetwork()))
                .setThreshold(multiSigWallet.getThreshold())
                .setTotalSigners(multiSigWallet.getTotalSigners())
                .addAllSigners(signers.stream()
                        .map(s -> com.blockchain.wallet.grpc.Signer.newBuilder()
                                .setAddress(s.getAddress())
                                .setName(s.getName() != null ? s.getName() : "")
                                .setIsOwner(s.getIsOwner())
                                .build())
                        .collect(Collectors.toList()))
                .setCreatedAt(multiSigWallet.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                .build();
    }
}
