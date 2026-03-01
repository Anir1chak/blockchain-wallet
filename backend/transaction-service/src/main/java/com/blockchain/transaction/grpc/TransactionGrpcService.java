package com.blockchain.transaction.grpc;

import com.blockchain.transaction.entity.MultiSigTransaction;
import com.blockchain.transaction.entity.Signature;
import com.blockchain.transaction.entity.Transaction;
import com.blockchain.transaction.service.TransactionManagementService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Page;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@GrpcService
@RequiredArgsConstructor
@Slf4j
public class TransactionGrpcService extends TransactionServiceGrpc.TransactionServiceImplBase {
    
    private final TransactionManagementService transactionService;
    
    @Override
    public void sendTransaction(SendTransactionRequest request, StreamObserver<TransactionResponse> responseObserver) {
        log.info("gRPC: Sending transaction from wallet: {}", request.getWalletId());
        
        try {
            String gasSpeed = switch (request.getGasSpeed()) {
                case SLOW -> "SLOW";
                case FAST -> "FAST";
                case INSTANT -> "INSTANT";
                default -> "STANDARD";
            };
            
            Transaction tx = transactionService.sendTransaction(
                    UUID.fromString(request.getWalletId()),
                    request.getToAddress(),
                    request.getAmount(),
                    "ETHEREUM_SEPOLIA", // Default network
                    gasSpeed
            );
            
            TransactionResponse response = mapToTransactionResponse(tx);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to send transaction", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void getTransaction(GetTransactionRequest request, StreamObserver<TransactionResponse> responseObserver) {
        log.info("gRPC: Getting transaction: {}", request.getTxHash());
        
        try {
            Transaction tx = transactionService.getTransaction(request.getTxHash(), request.getNetwork());
            
            TransactionResponse response = mapToTransactionResponse(tx);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to get transaction", e);
            responseObserver.onError(io.grpc.Status.NOT_FOUND
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void getTransactionHistory(GetHistoryRequest request, StreamObserver<TransactionHistoryResponse> responseObserver) {
        log.info("gRPC: Getting transaction history for wallet: {}", request.getWalletId());
        
        try {
            Transaction.TransactionType filterType = null;
            if (request.getFilterType() != TransactionType.UNRECOGNIZED) {
                filterType = mapTransactionType(request.getFilterType());
            }
            
            Page<Transaction> transactions = transactionService.getTransactionHistory(
                    UUID.fromString(request.getWalletId()),
                    request.getPage(),
                    request.getPageSize() > 0 ? request.getPageSize() : 20,
                    filterType
            );
            
            TransactionHistoryResponse response = TransactionHistoryResponse.newBuilder()
                    .addAllTransactions(transactions.getContent().stream()
                            .map(this::mapToTransactionResponse)
                            .collect(Collectors.toList()))
                    .setTotalCount((int) transactions.getTotalElements())
                    .setPage(request.getPage())
                    .setPageSize(request.getPageSize())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to get transaction history", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void estimateGas(EstimateGasRequest request, StreamObserver<GasEstimateResponse> responseObserver) {
        log.info("gRPC: Estimating gas for transaction");
        
        try {
            TransactionManagementService.GasEstimate estimate = transactionService.estimateGas(
                    request.getFromAddress(),
                    request.getToAddress(),
                    request.getAmount(),
                    request.getData(),
                    request.getNetwork().isEmpty() ? "ETHEREUM_SEPOLIA" : request.getNetwork()
            );
            
            GasEstimateResponse response = GasEstimateResponse.newBuilder()
                    .setGasLimit(estimate.gasLimit().toString())
                    .setSlowGasPrice(estimate.slowPrice().toString())
                    .setStandardGasPrice(estimate.standardPrice().toString())
                    .setFastGasPrice(estimate.fastPrice().toString())
                    .setInstantGasPrice(estimate.instantPrice().toString())
                    .setSlowTotalCost(estimate.slowTotal().toString())
                    .setStandardTotalCost(estimate.standardTotal().toString())
                    .setFastTotalCost(estimate.fastTotal().toString())
                    .setInstantTotalCost(estimate.instantTotal().toString())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to estimate gas", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void createMultiSigTransaction(CreateMultiSigTxRequest request, StreamObserver<MultiSigTxResponse> responseObserver) {
        log.info("gRPC: Creating multi-sig transaction for wallet: {}", request.getWalletId());
        
        try {
            MultiSigTransaction multiSigTx = transactionService.createMultiSigTransaction(
                    UUID.fromString(request.getWalletId()),
                    request.getInitiatorAddress(),
                    request.getToAddress(),
                    request.getAmount(),
                    request.getDescription()
            );
            
            MultiSigTxResponse response = mapToMultiSigTxResponse(multiSigTx);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to create multi-sig transaction", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void signMultiSigTransaction(SignMultiSigTxRequest request, StreamObserver<MultiSigTxResponse> responseObserver) {
        log.info("gRPC: Signing multi-sig transaction: {}", request.getMultisigTxId());
        
        try {
            MultiSigTransaction multiSigTx = transactionService.signMultiSigTransaction(
                    UUID.fromString(request.getMultisigTxId()),
                    request.getSignerAddress(),
                    "signature_placeholder" // In real impl, this would be actual signature
            );
            
            MultiSigTxResponse response = mapToMultiSigTxResponse(multiSigTx);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to sign multi-sig transaction", e);
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void getPendingMultiSigTransactions(GetPendingMultiSigTxRequest request, StreamObserver<PendingMultiSigTxResponse> responseObserver) {
        log.info("gRPC: Getting pending multi-sig transactions for wallet: {}", request.getWalletId());
        
        try {
            List<MultiSigTransaction> pending = transactionService.getPendingMultiSigTransactions(
                    UUID.fromString(request.getWalletId())
            );
            
            PendingMultiSigTxResponse response = PendingMultiSigTxResponse.newBuilder()
                    .addAllPendingTransactions(pending.stream()
                            .map(this::mapToMultiSigTxResponse)
                            .collect(Collectors.toList()))
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to get pending multi-sig transactions", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void executeMultiSigTransaction(ExecuteMultiSigTxRequest request, StreamObserver<TransactionResponse> responseObserver) {
        log.info("gRPC: Executing multi-sig transaction: {}", request.getMultisigTxId());
        
        try {
            Transaction tx = transactionService.executeMultiSigTransaction(
                    UUID.fromString(request.getMultisigTxId())
            );
            
            TransactionResponse response = mapToTransactionResponse(tx);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to execute multi-sig transaction", e);
            responseObserver.onError(io.grpc.Status.FAILED_PRECONDITION
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    @Override
    public void streamTransactionStatus(StreamTxStatusRequest request, StreamObserver<TransactionStatusUpdate> responseObserver) {
        log.info("gRPC: Starting transaction status stream for: {}", request.getTxHash());
        
        try {
            Transaction tx = transactionService.getTransaction(request.getTxHash(), "ETHEREUM_SEPOLIA");
            
            TransactionStatusUpdate update = TransactionStatusUpdate.newBuilder()
                    .setTxHash(tx.getTxHash() != null ? tx.getTxHash() : "")
                    .setStatus(mapToGrpcStatus(tx.getStatus()))
                    .setBlockNumber(tx.getBlockNumber() != null ? tx.getBlockNumber() : 0)
                    .setConfirmations(0)
                    .setTimestamp(tx.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                    .build();
            
            responseObserver.onNext(update);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Failed to stream transaction status", e);
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
    
    // Helper methods
    private TransactionResponse mapToTransactionResponse(Transaction tx) {
        return TransactionResponse.newBuilder()
                .setTxHash(tx.getTxHash() != null ? tx.getTxHash() : tx.getId().toString())
                .setFromAddress(tx.getFromAddress())
                .setToAddress(tx.getToAddress())
                .setAmount(tx.getAmount().toPlainString())
                .setGasPrice(tx.getGasPrice() != null ? tx.getGasPrice().toPlainString() : "0")
                .setGasUsed(tx.getGasUsed() != null ? tx.getGasUsed().toString() : "0")
                .setStatus(mapToGrpcStatus(tx.getStatus()))
                .setType(mapToGrpcType(tx.getType()))
                .setBlockNumber(tx.getBlockNumber() != null ? tx.getBlockNumber() : 0)
                .setTimestamp(tx.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                .setNetwork(tx.getNetwork())
                .build();
    }
    
    private MultiSigTxResponse mapToMultiSigTxResponse(MultiSigTransaction multiSigTx) {
        List<Signature> signatures = transactionService.getSignatures(multiSigTx);
        
        return MultiSigTxResponse.newBuilder()
                .setMultisigTxId(multiSigTx.getId().toString())
                .setWalletId(multiSigTx.getWalletId().toString())
                .setToAddress(multiSigTx.getToAddress())
                .setAmount(multiSigTx.getAmount().toPlainString())
                .setDescription(multiSigTx.getDescription() != null ? multiSigTx.getDescription() : "")
                .setSignaturesCollected(multiSigTx.getSignaturesCollected())
                .setSignaturesRequired(multiSigTx.getSignaturesRequired())
                .addAllSignatures(signatures.stream()
                        .map(s -> SignatureInfo.newBuilder()
                                .setSignerAddress(s.getSignerAddress())
                                .setSignature(s.getSignature())
                                .setSignedAt(s.getSignedAt().toEpochSecond(ZoneOffset.UTC))
                                .build())
                        .collect(Collectors.toList()))
                .setStatus(multiSigTx.getStatus().name())
                .setCreatedAt(multiSigTx.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                .setExpiresAt(multiSigTx.getExpiresAt().toEpochSecond(ZoneOffset.UTC))
                .build();
    }
    
    private TransactionStatus mapToGrpcStatus(Transaction.TransactionStatus status) {
        return switch (status) {
            case PENDING -> TransactionStatus.PENDING;
            case CONFIRMED -> TransactionStatus.CONFIRMED;
            case FAILED -> TransactionStatus.FAILED;
            case CANCELLED -> TransactionStatus.CANCELLED;
        };
    }
    
    private TransactionType mapToGrpcType(Transaction.TransactionType type) {
        return switch (type) {
            case SEND -> TransactionType.SEND;
            case RECEIVE -> TransactionType.RECEIVE;
            case SWAP -> TransactionType.SWAP;
            case CONTRACT_CALL -> TransactionType.CONTRACT_CALL;
        };
    }
    
    private Transaction.TransactionType mapTransactionType(TransactionType grpcType) {
        return switch (grpcType) {
            case SEND -> Transaction.TransactionType.SEND;
            case RECEIVE -> Transaction.TransactionType.RECEIVE;
            case SWAP -> Transaction.TransactionType.SWAP;
            case CONTRACT_CALL -> Transaction.TransactionType.CONTRACT_CALL;
            default -> null;
        };
    }
}
