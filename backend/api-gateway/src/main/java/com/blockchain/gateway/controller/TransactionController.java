package com.blockchain.gateway.controller;

import com.blockchain.gateway.dto.TransactionDto;
import com.blockchain.transaction.grpc.*;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
@Slf4j
public class TransactionController {
    
    @GrpcClient("transaction-service")
    private TransactionServiceGrpc.TransactionServiceBlockingStub transactionServiceStub;
    
    @PostMapping
    public ResponseEntity<?> sendTransaction(@RequestBody TransactionDto.SendTransactionRequest request) {
        log.info("REST: Sending transaction from wallet: {}", request.getWalletId());
        
        try {
            TransactionResponse response = transactionServiceStub.sendTransaction(
                    SendTransactionRequest.newBuilder()
                            .setWalletId(request.getWalletId())
                            .setToAddress(request.getToAddress())
                            .setAmount(request.getAmount())
                            .setPassword(request.getPassword() != null ? request.getPassword() : "")
                            .setGasSpeed(mapGasSpeed(request.getGasSpeed()))
                            .setData(request.getData() != null ? request.getData() : "")
                            .build()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(mapTransactionResponse(response));
        } catch (StatusRuntimeException e) {
            log.error("gRPC error sending transaction", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @GetMapping("/{txHash}")
    public ResponseEntity<?> getTransaction(@PathVariable String txHash,
                                             @RequestParam(defaultValue = "ETHEREUM_SEPOLIA") String network) {
        log.info("REST: Getting transaction: {}", txHash);
        
        try {
            TransactionResponse response = transactionServiceStub.getTransaction(
                    GetTransactionRequest.newBuilder()
                            .setTxHash(txHash)
                            .setNetwork(network)
                            .build()
            );
            
            return ResponseEntity.ok(mapTransactionResponse(response));
        } catch (StatusRuntimeException e) {
            log.error("gRPC error getting transaction", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @GetMapping("/wallet/{walletId}")
    public ResponseEntity<?> getTransactionHistory(@PathVariable String walletId,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "20") int pageSize,
                                                    @RequestParam(required = false) String type) {
        log.info("REST: Getting transaction history for wallet: {}", walletId);
        
        try {
            GetHistoryRequest.Builder builder = GetHistoryRequest.newBuilder()
                    .setWalletId(walletId)
                    .setPage(page)
                    .setPageSize(pageSize);
            
            if (type != null) {
                builder.setFilterType(mapTransactionType(type));
            }
            
            TransactionHistoryResponse response = transactionServiceStub.getTransactionHistory(builder.build());
            
            return ResponseEntity.ok(
                    TransactionDto.TransactionHistoryResponse.builder()
                            .transactions(response.getTransactionsList().stream()
                                    .map(this::mapTransactionResponse)
                                    .collect(Collectors.toList()))
                            .totalCount(response.getTotalCount())
                            .page(response.getPage())
                            .pageSize(response.getPageSize())
                            .build()
            );
        } catch (StatusRuntimeException e) {
            log.error("gRPC error getting transaction history", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @PostMapping("/estimate-gas")
    public ResponseEntity<?> estimateGas(@RequestParam String fromAddress,
                                          @RequestParam String toAddress,
                                          @RequestParam String amount,
                                          @RequestParam(required = false) String data,
                                          @RequestParam(defaultValue = "ETHEREUM_SEPOLIA") String network) {
        log.info("REST: Estimating gas for transaction");
        
        try {
            GasEstimateResponse response = transactionServiceStub.estimateGas(
                    EstimateGasRequest.newBuilder()
                            .setFromAddress(fromAddress)
                            .setToAddress(toAddress)
                            .setAmount(amount)
                            .setData(data != null ? data : "")
                            .setNetwork(network)
                            .build()
            );
            
            return ResponseEntity.ok(
                    TransactionDto.GasEstimateResponse.builder()
                            .gasLimit(response.getGasLimit())
                            .slowGasPrice(response.getSlowGasPrice())
                            .standardGasPrice(response.getStandardGasPrice())
                            .fastGasPrice(response.getFastGasPrice())
                            .instantGasPrice(response.getInstantGasPrice())
                            .slowTotalCost(response.getSlowTotalCost())
                            .standardTotalCost(response.getStandardTotalCost())
                            .fastTotalCost(response.getFastTotalCost())
                            .instantTotalCost(response.getInstantTotalCost())
                            .build()
            );
        } catch (StatusRuntimeException e) {
            log.error("gRPC error estimating gas", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @PostMapping("/multisig")
    public ResponseEntity<?> createMultiSigTransaction(@RequestBody TransactionDto.CreateMultiSigTxRequest request) {
        log.info("REST: Creating multi-sig transaction for wallet: {}", request.getWalletId());
        
        try {
            MultiSigTxResponse response = transactionServiceStub.createMultiSigTransaction(
                    CreateMultiSigTxRequest.newBuilder()
                            .setWalletId(request.getWalletId())
                            .setInitiatorAddress(request.getInitiatorAddress())
                            .setToAddress(request.getToAddress())
                            .setAmount(request.getAmount())
                            .setDescription(request.getDescription() != null ? request.getDescription() : "")
                            .build()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(mapMultiSigTxResponse(response));
        } catch (StatusRuntimeException e) {
            log.error("gRPC error creating multi-sig transaction", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @PostMapping("/multisig/{multiSigTxId}/sign")
    public ResponseEntity<?> signMultiSigTransaction(@PathVariable String multiSigTxId,
                                                      @RequestBody TransactionDto.SignMultiSigTxRequest request) {
        log.info("REST: Signing multi-sig transaction: {}", multiSigTxId);
        
        try {
            MultiSigTxResponse response = transactionServiceStub.signMultiSigTransaction(
                    SignMultiSigTxRequest.newBuilder()
                            .setMultisigTxId(multiSigTxId)
                            .setSignerAddress(request.getSignerAddress())
                            .setPassword(request.getPassword() != null ? request.getPassword() : "")
                            .build()
            );
            
            return ResponseEntity.ok(mapMultiSigTxResponse(response));
        } catch (StatusRuntimeException e) {
            log.error("gRPC error signing multi-sig transaction", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @GetMapping("/multisig/pending/{walletId}")
    public ResponseEntity<?> getPendingMultiSigTransactions(@PathVariable String walletId) {
        log.info("REST: Getting pending multi-sig transactions for wallet: {}", walletId);
        
        try {
            PendingMultiSigTxResponse response = transactionServiceStub.getPendingMultiSigTransactions(
                    GetPendingMultiSigTxRequest.newBuilder()
                            .setWalletId(walletId)
                            .build()
            );
            
            List<TransactionDto.MultiSigTxResponse> pending = response.getPendingTransactionsList().stream()
                    .map(this::mapMultiSigTxResponse)
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(pending);
        } catch (StatusRuntimeException e) {
            log.error("gRPC error getting pending multi-sig transactions", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    @PostMapping("/multisig/{multiSigTxId}/execute")
    public ResponseEntity<?> executeMultiSigTransaction(@PathVariable String multiSigTxId) {
        log.info("REST: Executing multi-sig transaction: {}", multiSigTxId);
        
        try {
            TransactionResponse response = transactionServiceStub.executeMultiSigTransaction(
                    ExecuteMultiSigTxRequest.newBuilder()
                            .setMultisigTxId(multiSigTxId)
                            .build()
            );
            
            return ResponseEntity.ok(mapTransactionResponse(response));
        } catch (StatusRuntimeException e) {
            log.error("gRPC error executing multi-sig transaction", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getStatus().getDescription()));
        }
    }
    
    private GasSpeed mapGasSpeed(String speed) {
        if (speed == null) return GasSpeed.STANDARD;
        return switch (speed.toUpperCase()) {
            case "SLOW" -> GasSpeed.SLOW;
            case "FAST" -> GasSpeed.FAST;
            case "INSTANT" -> GasSpeed.INSTANT;
            default -> GasSpeed.STANDARD;
        };
    }
    
    private TransactionType mapTransactionType(String type) {
        return switch (type.toUpperCase()) {
            case "SEND" -> TransactionType.SEND;
            case "RECEIVE" -> TransactionType.RECEIVE;
            case "SWAP" -> TransactionType.SWAP;
            case "CONTRACT_CALL" -> TransactionType.CONTRACT_CALL;
            default -> TransactionType.SEND;
        };
    }
    
    private TransactionDto.TransactionResponse mapTransactionResponse(TransactionResponse response) {
        return TransactionDto.TransactionResponse.builder()
                .txHash(response.getTxHash())
                .fromAddress(response.getFromAddress())
                .toAddress(response.getToAddress())
                .amount(response.getAmount())
                .gasPrice(response.getGasPrice())
                .gasUsed(response.getGasUsed())
                .status(response.getStatus().name())
                .type(response.getType().name())
                .blockNumber(response.getBlockNumber())
                .timestamp(response.getTimestamp())
                .network(response.getNetwork())
                .build();
    }
    
    private TransactionDto.MultiSigTxResponse mapMultiSigTxResponse(MultiSigTxResponse response) {
        return TransactionDto.MultiSigTxResponse.builder()
                .multiSigTxId(response.getMultisigTxId())
                .walletId(response.getWalletId())
                .toAddress(response.getToAddress())
                .amount(response.getAmount())
                .description(response.getDescription())
                .signaturesCollected(response.getSignaturesCollected())
                .signaturesRequired(response.getSignaturesRequired())
                .signatures(response.getSignaturesList().stream()
                        .map(s -> TransactionDto.SignatureInfo.builder()
                                .signerAddress(s.getSignerAddress())
                                .signature(s.getSignature())
                                .signedAt(s.getSignedAt())
                                .build())
                        .collect(Collectors.toList()))
                .status(response.getStatus())
                .createdAt(response.getCreatedAt())
                .expiresAt(response.getExpiresAt())
                .build();
    }
    
    record ErrorResponse(String message) {}
}
