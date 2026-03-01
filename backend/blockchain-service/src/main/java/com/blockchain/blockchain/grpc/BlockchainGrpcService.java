package com.blockchain.blockchain.grpc;

import com.blockchain.blockchain.service.BlockchainQueryService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@RequiredArgsConstructor
@Slf4j
public class BlockchainGrpcService extends BlockchainServiceGrpc.BlockchainServiceImplBase {
    
    private final BlockchainQueryService blockchainQueryService;
    
    @Override
    public void getBalance(GetBalanceRequest request, StreamObserver<BalanceResponse> responseObserver) {
        log.info("gRPC: Getting balance for address: {} on network: {}", request.getAddress(), request.getNetwork());
        
        try {
            BlockchainQueryService.BalanceResult result = blockchainQueryService.getBalance(
                    request.getAddress(),
                    request.getNetwork().isEmpty() ? "ETHEREUM_SEPOLIA" : request.getNetwork()
            );
            
            BalanceResponse response = BalanceResponse.newBuilder()
                    .setAddress(result.address())
                    .setBalanceWei(result.balanceWei())
                    .setBalanceEth(result.balanceEth())
                    .setBalanceUsd(result.balanceUsd())
                    .setNetwork(result.network())
                    .setTimestamp(result.timestamp())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to get balance", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void getBalances(GetBalancesRequest request, StreamObserver<GetBalancesResponse> responseObserver) {
        log.info("gRPC: Getting balances for {} addresses", request.getAddressesList().size());
        
        try {
            List<BlockchainQueryService.BalanceResult> results = blockchainQueryService.getBalances(
                    request.getAddressesList(),
                    request.getNetwork().isEmpty() ? "ETHEREUM_SEPOLIA" : request.getNetwork()
            );
            
            GetBalancesResponse.Builder responseBuilder = GetBalancesResponse.newBuilder();
            for (BlockchainQueryService.BalanceResult result : results) {
                responseBuilder.addBalances(BalanceResponse.newBuilder()
                        .setAddress(result.address())
                        .setBalanceWei(result.balanceWei())
                        .setBalanceEth(result.balanceEth())
                        .setBalanceUsd(result.balanceUsd())
                        .setNetwork(result.network())
                        .setTimestamp(result.timestamp())
                        .build());
            }
            
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to get balances", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void getGasPrices(GetGasPricesRequest request, StreamObserver<GasPricesResponse> responseObserver) {
        log.info("gRPC: Getting gas prices for network: {}", request.getNetwork());
        
        try {
            BlockchainQueryService.GasPricesResult result = blockchainQueryService.getGasPrices(
                    request.getNetwork().isEmpty() ? "ETHEREUM_SEPOLIA" : request.getNetwork()
            );
            
            GasPricesResponse response = GasPricesResponse.newBuilder()
                    .setSlow(result.slow())
                    .setStandard(result.standard())
                    .setFast(result.fast())
                    .setInstant(result.instant())
                    .setBaseFee(result.baseFee())
                    .setNetwork(result.network())
                    .setTimestamp(result.timestamp())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to get gas prices", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void getBlockNumber(GetBlockNumberRequest request, StreamObserver<BlockNumberResponse> responseObserver) {
        log.info("gRPC: Getting block number for network: {}", request.getNetwork());
        
        try {
            BlockchainQueryService.BlockNumberResult result = blockchainQueryService.getBlockNumber(
                    request.getNetwork().isEmpty() ? "ETHEREUM_SEPOLIA" : request.getNetwork()
            );
            
            BlockNumberResponse response = BlockNumberResponse.newBuilder()
                    .setBlockNumber(result.blockNumber())
                    .setNetwork(result.network())
                    .setTimestamp(result.timestamp())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to get block number", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void validateAddress(ValidateAddressRequest request, StreamObserver<ValidateAddressResponse> responseObserver) {
        log.info("gRPC: Validating address: {}", request.getAddress());
        
        try {
            BlockchainQueryService.AddressValidationResult result = blockchainQueryService.validateAddress(
                    request.getAddress()
            );
            
            ValidateAddressResponse response = ValidateAddressResponse.newBuilder()
                    .setIsValid(result.isValid())
                    .setIsContract(result.isContract())
                    .setChecksumAddress(result.checksumAddress())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to validate address", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void getNetworkStatus(GetNetworkStatusRequest request, StreamObserver<NetworkStatusResponse> responseObserver) {
        log.info("gRPC: Getting network status for: {}", request.getNetwork());
        
        try {
            BlockchainQueryService.NetworkStatusResult result = blockchainQueryService.getNetworkStatus(
                    request.getNetwork().isEmpty() ? "ETHEREUM_SEPOLIA" : request.getNetwork()
            );
            
            NetworkStatusResponse response = NetworkStatusResponse.newBuilder()
                    .setNetwork(result.network())
                    .setChainId(result.chainId())
                    .setLatestBlock(result.latestBlock())
                    .setPeerCount(result.peerCount())
                    .setIsSyncing(result.isSyncing())
                    .setGasPrice(result.gasPrice())
                    .setTimestamp(result.timestamp())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to get network status", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void streamBlocks(StreamBlocksRequest request, StreamObserver<BlockUpdate> responseObserver) {
        log.info("gRPC: Starting block stream for network: {}", request.getNetwork());
        
        try {
            BlockchainQueryService.BlockNumberResult result = blockchainQueryService.getBlockNumber(
                    request.getNetwork().isEmpty() ? "ETHEREUM_SEPOLIA" : request.getNetwork()
            );
            
            BlockUpdate update = BlockUpdate.newBuilder()
                    .setBlockNumber(result.blockNumber())
                    .setBlockHash("0x" + Long.toHexString(result.blockNumber()))
                    .setTransactionCount(0)
                    .setGasUsed("0")
                    .setGasLimit("30000000")
                    .setTimestamp(result.timestamp())
                    .build();
            
            responseObserver.onNext(update);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to stream blocks", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void streamBalanceUpdates(StreamBalanceRequest request, StreamObserver<BalanceUpdate> responseObserver) {
        log.info("gRPC: Starting balance stream for {} addresses", request.getAddressesList().size());
        
        try {
            for (String address : request.getAddressesList()) {
                BlockchainQueryService.BalanceResult result = blockchainQueryService.getBalance(
                        address,
                        request.getNetwork().isEmpty() ? "ETHEREUM_SEPOLIA" : request.getNetwork()
                );
                
                BalanceUpdate update = BalanceUpdate.newBuilder()
                        .setAddress(address)
                        .setOldBalance("0")
                        .setNewBalance(result.balanceWei())
                        .setChange(result.balanceWei())
                        .setTxHash("")
                        .setBlockNumber(0)
                        .setTimestamp(result.timestamp())
                        .build();
                
                responseObserver.onNext(update);
            }
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to stream balance updates", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void getTokenBalance(GetTokenBalanceRequest request, StreamObserver<TokenBalanceResponse> responseObserver) {
        log.info("gRPC: Getting token balance for wallet: {} token: {}", 
                request.getWalletAddress(), request.getTokenAddress());
        
        TokenBalanceResponse response = TokenBalanceResponse.newBuilder()
                .setWalletAddress(request.getWalletAddress())
                .setTokenAddress(request.getTokenAddress())
                .setTokenSymbol("USDC")
                .setTokenName("USD Coin")
                .setDecimals(6)
                .setBalance("0")
                .setBalanceFormatted("0.00")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
