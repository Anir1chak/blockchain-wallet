package com.blockchain.transaction.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Transaction Service Definition
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.59.0)",
    comments = "Source: transaction.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TransactionServiceGrpc {

  private TransactionServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "transaction.TransactionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.SendTransactionRequest,
      com.blockchain.transaction.grpc.TransactionResponse> getSendTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendTransaction",
      requestType = com.blockchain.transaction.grpc.SendTransactionRequest.class,
      responseType = com.blockchain.transaction.grpc.TransactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.SendTransactionRequest,
      com.blockchain.transaction.grpc.TransactionResponse> getSendTransactionMethod() {
    io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.SendTransactionRequest, com.blockchain.transaction.grpc.TransactionResponse> getSendTransactionMethod;
    if ((getSendTransactionMethod = TransactionServiceGrpc.getSendTransactionMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getSendTransactionMethod = TransactionServiceGrpc.getSendTransactionMethod) == null) {
          TransactionServiceGrpc.getSendTransactionMethod = getSendTransactionMethod =
              io.grpc.MethodDescriptor.<com.blockchain.transaction.grpc.SendTransactionRequest, com.blockchain.transaction.grpc.TransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.SendTransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.TransactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("SendTransaction"))
              .build();
        }
      }
    }
    return getSendTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.GetTransactionRequest,
      com.blockchain.transaction.grpc.TransactionResponse> getGetTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTransaction",
      requestType = com.blockchain.transaction.grpc.GetTransactionRequest.class,
      responseType = com.blockchain.transaction.grpc.TransactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.GetTransactionRequest,
      com.blockchain.transaction.grpc.TransactionResponse> getGetTransactionMethod() {
    io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.GetTransactionRequest, com.blockchain.transaction.grpc.TransactionResponse> getGetTransactionMethod;
    if ((getGetTransactionMethod = TransactionServiceGrpc.getGetTransactionMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getGetTransactionMethod = TransactionServiceGrpc.getGetTransactionMethod) == null) {
          TransactionServiceGrpc.getGetTransactionMethod = getGetTransactionMethod =
              io.grpc.MethodDescriptor.<com.blockchain.transaction.grpc.GetTransactionRequest, com.blockchain.transaction.grpc.TransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.GetTransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.TransactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("GetTransaction"))
              .build();
        }
      }
    }
    return getGetTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.GetHistoryRequest,
      com.blockchain.transaction.grpc.TransactionHistoryResponse> getGetTransactionHistoryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTransactionHistory",
      requestType = com.blockchain.transaction.grpc.GetHistoryRequest.class,
      responseType = com.blockchain.transaction.grpc.TransactionHistoryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.GetHistoryRequest,
      com.blockchain.transaction.grpc.TransactionHistoryResponse> getGetTransactionHistoryMethod() {
    io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.GetHistoryRequest, com.blockchain.transaction.grpc.TransactionHistoryResponse> getGetTransactionHistoryMethod;
    if ((getGetTransactionHistoryMethod = TransactionServiceGrpc.getGetTransactionHistoryMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getGetTransactionHistoryMethod = TransactionServiceGrpc.getGetTransactionHistoryMethod) == null) {
          TransactionServiceGrpc.getGetTransactionHistoryMethod = getGetTransactionHistoryMethod =
              io.grpc.MethodDescriptor.<com.blockchain.transaction.grpc.GetHistoryRequest, com.blockchain.transaction.grpc.TransactionHistoryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTransactionHistory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.GetHistoryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.TransactionHistoryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("GetTransactionHistory"))
              .build();
        }
      }
    }
    return getGetTransactionHistoryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.EstimateGasRequest,
      com.blockchain.transaction.grpc.GasEstimateResponse> getEstimateGasMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EstimateGas",
      requestType = com.blockchain.transaction.grpc.EstimateGasRequest.class,
      responseType = com.blockchain.transaction.grpc.GasEstimateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.EstimateGasRequest,
      com.blockchain.transaction.grpc.GasEstimateResponse> getEstimateGasMethod() {
    io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.EstimateGasRequest, com.blockchain.transaction.grpc.GasEstimateResponse> getEstimateGasMethod;
    if ((getEstimateGasMethod = TransactionServiceGrpc.getEstimateGasMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getEstimateGasMethod = TransactionServiceGrpc.getEstimateGasMethod) == null) {
          TransactionServiceGrpc.getEstimateGasMethod = getEstimateGasMethod =
              io.grpc.MethodDescriptor.<com.blockchain.transaction.grpc.EstimateGasRequest, com.blockchain.transaction.grpc.GasEstimateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EstimateGas"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.EstimateGasRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.GasEstimateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("EstimateGas"))
              .build();
        }
      }
    }
    return getEstimateGasMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.CreateMultiSigTxRequest,
      com.blockchain.transaction.grpc.MultiSigTxResponse> getCreateMultiSigTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateMultiSigTransaction",
      requestType = com.blockchain.transaction.grpc.CreateMultiSigTxRequest.class,
      responseType = com.blockchain.transaction.grpc.MultiSigTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.CreateMultiSigTxRequest,
      com.blockchain.transaction.grpc.MultiSigTxResponse> getCreateMultiSigTransactionMethod() {
    io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.CreateMultiSigTxRequest, com.blockchain.transaction.grpc.MultiSigTxResponse> getCreateMultiSigTransactionMethod;
    if ((getCreateMultiSigTransactionMethod = TransactionServiceGrpc.getCreateMultiSigTransactionMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getCreateMultiSigTransactionMethod = TransactionServiceGrpc.getCreateMultiSigTransactionMethod) == null) {
          TransactionServiceGrpc.getCreateMultiSigTransactionMethod = getCreateMultiSigTransactionMethod =
              io.grpc.MethodDescriptor.<com.blockchain.transaction.grpc.CreateMultiSigTxRequest, com.blockchain.transaction.grpc.MultiSigTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateMultiSigTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.CreateMultiSigTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.MultiSigTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("CreateMultiSigTransaction"))
              .build();
        }
      }
    }
    return getCreateMultiSigTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.SignMultiSigTxRequest,
      com.blockchain.transaction.grpc.MultiSigTxResponse> getSignMultiSigTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SignMultiSigTransaction",
      requestType = com.blockchain.transaction.grpc.SignMultiSigTxRequest.class,
      responseType = com.blockchain.transaction.grpc.MultiSigTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.SignMultiSigTxRequest,
      com.blockchain.transaction.grpc.MultiSigTxResponse> getSignMultiSigTransactionMethod() {
    io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.SignMultiSigTxRequest, com.blockchain.transaction.grpc.MultiSigTxResponse> getSignMultiSigTransactionMethod;
    if ((getSignMultiSigTransactionMethod = TransactionServiceGrpc.getSignMultiSigTransactionMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getSignMultiSigTransactionMethod = TransactionServiceGrpc.getSignMultiSigTransactionMethod) == null) {
          TransactionServiceGrpc.getSignMultiSigTransactionMethod = getSignMultiSigTransactionMethod =
              io.grpc.MethodDescriptor.<com.blockchain.transaction.grpc.SignMultiSigTxRequest, com.blockchain.transaction.grpc.MultiSigTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SignMultiSigTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.SignMultiSigTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.MultiSigTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("SignMultiSigTransaction"))
              .build();
        }
      }
    }
    return getSignMultiSigTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.GetPendingMultiSigTxRequest,
      com.blockchain.transaction.grpc.PendingMultiSigTxResponse> getGetPendingMultiSigTransactionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPendingMultiSigTransactions",
      requestType = com.blockchain.transaction.grpc.GetPendingMultiSigTxRequest.class,
      responseType = com.blockchain.transaction.grpc.PendingMultiSigTxResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.GetPendingMultiSigTxRequest,
      com.blockchain.transaction.grpc.PendingMultiSigTxResponse> getGetPendingMultiSigTransactionsMethod() {
    io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.GetPendingMultiSigTxRequest, com.blockchain.transaction.grpc.PendingMultiSigTxResponse> getGetPendingMultiSigTransactionsMethod;
    if ((getGetPendingMultiSigTransactionsMethod = TransactionServiceGrpc.getGetPendingMultiSigTransactionsMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getGetPendingMultiSigTransactionsMethod = TransactionServiceGrpc.getGetPendingMultiSigTransactionsMethod) == null) {
          TransactionServiceGrpc.getGetPendingMultiSigTransactionsMethod = getGetPendingMultiSigTransactionsMethod =
              io.grpc.MethodDescriptor.<com.blockchain.transaction.grpc.GetPendingMultiSigTxRequest, com.blockchain.transaction.grpc.PendingMultiSigTxResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPendingMultiSigTransactions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.GetPendingMultiSigTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.PendingMultiSigTxResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("GetPendingMultiSigTransactions"))
              .build();
        }
      }
    }
    return getGetPendingMultiSigTransactionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.ExecuteMultiSigTxRequest,
      com.blockchain.transaction.grpc.TransactionResponse> getExecuteMultiSigTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExecuteMultiSigTransaction",
      requestType = com.blockchain.transaction.grpc.ExecuteMultiSigTxRequest.class,
      responseType = com.blockchain.transaction.grpc.TransactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.ExecuteMultiSigTxRequest,
      com.blockchain.transaction.grpc.TransactionResponse> getExecuteMultiSigTransactionMethod() {
    io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.ExecuteMultiSigTxRequest, com.blockchain.transaction.grpc.TransactionResponse> getExecuteMultiSigTransactionMethod;
    if ((getExecuteMultiSigTransactionMethod = TransactionServiceGrpc.getExecuteMultiSigTransactionMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getExecuteMultiSigTransactionMethod = TransactionServiceGrpc.getExecuteMultiSigTransactionMethod) == null) {
          TransactionServiceGrpc.getExecuteMultiSigTransactionMethod = getExecuteMultiSigTransactionMethod =
              io.grpc.MethodDescriptor.<com.blockchain.transaction.grpc.ExecuteMultiSigTxRequest, com.blockchain.transaction.grpc.TransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExecuteMultiSigTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.ExecuteMultiSigTxRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.TransactionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("ExecuteMultiSigTransaction"))
              .build();
        }
      }
    }
    return getExecuteMultiSigTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.StreamTxStatusRequest,
      com.blockchain.transaction.grpc.TransactionStatusUpdate> getStreamTransactionStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamTransactionStatus",
      requestType = com.blockchain.transaction.grpc.StreamTxStatusRequest.class,
      responseType = com.blockchain.transaction.grpc.TransactionStatusUpdate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.StreamTxStatusRequest,
      com.blockchain.transaction.grpc.TransactionStatusUpdate> getStreamTransactionStatusMethod() {
    io.grpc.MethodDescriptor<com.blockchain.transaction.grpc.StreamTxStatusRequest, com.blockchain.transaction.grpc.TransactionStatusUpdate> getStreamTransactionStatusMethod;
    if ((getStreamTransactionStatusMethod = TransactionServiceGrpc.getStreamTransactionStatusMethod) == null) {
      synchronized (TransactionServiceGrpc.class) {
        if ((getStreamTransactionStatusMethod = TransactionServiceGrpc.getStreamTransactionStatusMethod) == null) {
          TransactionServiceGrpc.getStreamTransactionStatusMethod = getStreamTransactionStatusMethod =
              io.grpc.MethodDescriptor.<com.blockchain.transaction.grpc.StreamTxStatusRequest, com.blockchain.transaction.grpc.TransactionStatusUpdate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamTransactionStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.StreamTxStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.transaction.grpc.TransactionStatusUpdate.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionServiceMethodDescriptorSupplier("StreamTransactionStatus"))
              .build();
        }
      }
    }
    return getStreamTransactionStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TransactionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionServiceStub>() {
        @java.lang.Override
        public TransactionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionServiceStub(channel, callOptions);
        }
      };
    return TransactionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TransactionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionServiceBlockingStub>() {
        @java.lang.Override
        public TransactionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionServiceBlockingStub(channel, callOptions);
        }
      };
    return TransactionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TransactionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionServiceFutureStub>() {
        @java.lang.Override
        public TransactionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionServiceFutureStub(channel, callOptions);
        }
      };
    return TransactionServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Transaction Service Definition
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Sign and broadcast a transaction
     * </pre>
     */
    default void sendTransaction(com.blockchain.transaction.grpc.SendTransactionRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get transaction by hash
     * </pre>
     */
    default void getTransaction(com.blockchain.transaction.grpc.GetTransactionRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get transaction history for a wallet
     * </pre>
     */
    default void getTransactionHistory(com.blockchain.transaction.grpc.GetHistoryRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionHistoryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTransactionHistoryMethod(), responseObserver);
    }

    /**
     * <pre>
     * Estimate gas for a transaction
     * </pre>
     */
    default void estimateGas(com.blockchain.transaction.grpc.EstimateGasRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.GasEstimateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEstimateGasMethod(), responseObserver);
    }

    /**
     * <pre>
     * Create pending multi-sig transaction
     * </pre>
     */
    default void createMultiSigTransaction(com.blockchain.transaction.grpc.CreateMultiSigTxRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.MultiSigTxResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateMultiSigTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Sign a pending multi-sig transaction
     * </pre>
     */
    default void signMultiSigTransaction(com.blockchain.transaction.grpc.SignMultiSigTxRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.MultiSigTxResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSignMultiSigTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get pending multi-sig transactions
     * </pre>
     */
    default void getPendingMultiSigTransactions(com.blockchain.transaction.grpc.GetPendingMultiSigTxRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.PendingMultiSigTxResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPendingMultiSigTransactionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Execute multi-sig transaction (when threshold reached)
     * </pre>
     */
    default void executeMultiSigTransaction(com.blockchain.transaction.grpc.ExecuteMultiSigTxRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExecuteMultiSigTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Stream transaction status updates
     * </pre>
     */
    default void streamTransactionStatus(com.blockchain.transaction.grpc.StreamTxStatusRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionStatusUpdate> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamTransactionStatusMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service TransactionService.
   * <pre>
   * Transaction Service Definition
   * </pre>
   */
  public static abstract class TransactionServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return TransactionServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service TransactionService.
   * <pre>
   * Transaction Service Definition
   * </pre>
   */
  public static final class TransactionServiceStub
      extends io.grpc.stub.AbstractAsyncStub<TransactionServiceStub> {
    private TransactionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sign and broadcast a transaction
     * </pre>
     */
    public void sendTransaction(com.blockchain.transaction.grpc.SendTransactionRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get transaction by hash
     * </pre>
     */
    public void getTransaction(com.blockchain.transaction.grpc.GetTransactionRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get transaction history for a wallet
     * </pre>
     */
    public void getTransactionHistory(com.blockchain.transaction.grpc.GetHistoryRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionHistoryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTransactionHistoryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Estimate gas for a transaction
     * </pre>
     */
    public void estimateGas(com.blockchain.transaction.grpc.EstimateGasRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.GasEstimateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEstimateGasMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Create pending multi-sig transaction
     * </pre>
     */
    public void createMultiSigTransaction(com.blockchain.transaction.grpc.CreateMultiSigTxRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.MultiSigTxResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateMultiSigTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Sign a pending multi-sig transaction
     * </pre>
     */
    public void signMultiSigTransaction(com.blockchain.transaction.grpc.SignMultiSigTxRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.MultiSigTxResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSignMultiSigTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get pending multi-sig transactions
     * </pre>
     */
    public void getPendingMultiSigTransactions(com.blockchain.transaction.grpc.GetPendingMultiSigTxRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.PendingMultiSigTxResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPendingMultiSigTransactionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Execute multi-sig transaction (when threshold reached)
     * </pre>
     */
    public void executeMultiSigTransaction(com.blockchain.transaction.grpc.ExecuteMultiSigTxRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExecuteMultiSigTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Stream transaction status updates
     * </pre>
     */
    public void streamTransactionStatus(com.blockchain.transaction.grpc.StreamTxStatusRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionStatusUpdate> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamTransactionStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service TransactionService.
   * <pre>
   * Transaction Service Definition
   * </pre>
   */
  public static final class TransactionServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TransactionServiceBlockingStub> {
    private TransactionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sign and broadcast a transaction
     * </pre>
     */
    public com.blockchain.transaction.grpc.TransactionResponse sendTransaction(com.blockchain.transaction.grpc.SendTransactionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get transaction by hash
     * </pre>
     */
    public com.blockchain.transaction.grpc.TransactionResponse getTransaction(com.blockchain.transaction.grpc.GetTransactionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get transaction history for a wallet
     * </pre>
     */
    public com.blockchain.transaction.grpc.TransactionHistoryResponse getTransactionHistory(com.blockchain.transaction.grpc.GetHistoryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTransactionHistoryMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Estimate gas for a transaction
     * </pre>
     */
    public com.blockchain.transaction.grpc.GasEstimateResponse estimateGas(com.blockchain.transaction.grpc.EstimateGasRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEstimateGasMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Create pending multi-sig transaction
     * </pre>
     */
    public com.blockchain.transaction.grpc.MultiSigTxResponse createMultiSigTransaction(com.blockchain.transaction.grpc.CreateMultiSigTxRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateMultiSigTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Sign a pending multi-sig transaction
     * </pre>
     */
    public com.blockchain.transaction.grpc.MultiSigTxResponse signMultiSigTransaction(com.blockchain.transaction.grpc.SignMultiSigTxRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSignMultiSigTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get pending multi-sig transactions
     * </pre>
     */
    public com.blockchain.transaction.grpc.PendingMultiSigTxResponse getPendingMultiSigTransactions(com.blockchain.transaction.grpc.GetPendingMultiSigTxRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPendingMultiSigTransactionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Execute multi-sig transaction (when threshold reached)
     * </pre>
     */
    public com.blockchain.transaction.grpc.TransactionResponse executeMultiSigTransaction(com.blockchain.transaction.grpc.ExecuteMultiSigTxRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExecuteMultiSigTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Stream transaction status updates
     * </pre>
     */
    public java.util.Iterator<com.blockchain.transaction.grpc.TransactionStatusUpdate> streamTransactionStatus(
        com.blockchain.transaction.grpc.StreamTxStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamTransactionStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service TransactionService.
   * <pre>
   * Transaction Service Definition
   * </pre>
   */
  public static final class TransactionServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<TransactionServiceFutureStub> {
    private TransactionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sign and broadcast a transaction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.transaction.grpc.TransactionResponse> sendTransaction(
        com.blockchain.transaction.grpc.SendTransactionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendTransactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get transaction by hash
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.transaction.grpc.TransactionResponse> getTransaction(
        com.blockchain.transaction.grpc.GetTransactionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTransactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get transaction history for a wallet
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.transaction.grpc.TransactionHistoryResponse> getTransactionHistory(
        com.blockchain.transaction.grpc.GetHistoryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTransactionHistoryMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Estimate gas for a transaction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.transaction.grpc.GasEstimateResponse> estimateGas(
        com.blockchain.transaction.grpc.EstimateGasRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEstimateGasMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Create pending multi-sig transaction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.transaction.grpc.MultiSigTxResponse> createMultiSigTransaction(
        com.blockchain.transaction.grpc.CreateMultiSigTxRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateMultiSigTransactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Sign a pending multi-sig transaction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.transaction.grpc.MultiSigTxResponse> signMultiSigTransaction(
        com.blockchain.transaction.grpc.SignMultiSigTxRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSignMultiSigTransactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get pending multi-sig transactions
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.transaction.grpc.PendingMultiSigTxResponse> getPendingMultiSigTransactions(
        com.blockchain.transaction.grpc.GetPendingMultiSigTxRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPendingMultiSigTransactionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Execute multi-sig transaction (when threshold reached)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.transaction.grpc.TransactionResponse> executeMultiSigTransaction(
        com.blockchain.transaction.grpc.ExecuteMultiSigTxRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExecuteMultiSigTransactionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_TRANSACTION = 0;
  private static final int METHODID_GET_TRANSACTION = 1;
  private static final int METHODID_GET_TRANSACTION_HISTORY = 2;
  private static final int METHODID_ESTIMATE_GAS = 3;
  private static final int METHODID_CREATE_MULTI_SIG_TRANSACTION = 4;
  private static final int METHODID_SIGN_MULTI_SIG_TRANSACTION = 5;
  private static final int METHODID_GET_PENDING_MULTI_SIG_TRANSACTIONS = 6;
  private static final int METHODID_EXECUTE_MULTI_SIG_TRANSACTION = 7;
  private static final int METHODID_STREAM_TRANSACTION_STATUS = 8;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_TRANSACTION:
          serviceImpl.sendTransaction((com.blockchain.transaction.grpc.SendTransactionRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionResponse>) responseObserver);
          break;
        case METHODID_GET_TRANSACTION:
          serviceImpl.getTransaction((com.blockchain.transaction.grpc.GetTransactionRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionResponse>) responseObserver);
          break;
        case METHODID_GET_TRANSACTION_HISTORY:
          serviceImpl.getTransactionHistory((com.blockchain.transaction.grpc.GetHistoryRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionHistoryResponse>) responseObserver);
          break;
        case METHODID_ESTIMATE_GAS:
          serviceImpl.estimateGas((com.blockchain.transaction.grpc.EstimateGasRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.GasEstimateResponse>) responseObserver);
          break;
        case METHODID_CREATE_MULTI_SIG_TRANSACTION:
          serviceImpl.createMultiSigTransaction((com.blockchain.transaction.grpc.CreateMultiSigTxRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.MultiSigTxResponse>) responseObserver);
          break;
        case METHODID_SIGN_MULTI_SIG_TRANSACTION:
          serviceImpl.signMultiSigTransaction((com.blockchain.transaction.grpc.SignMultiSigTxRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.MultiSigTxResponse>) responseObserver);
          break;
        case METHODID_GET_PENDING_MULTI_SIG_TRANSACTIONS:
          serviceImpl.getPendingMultiSigTransactions((com.blockchain.transaction.grpc.GetPendingMultiSigTxRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.PendingMultiSigTxResponse>) responseObserver);
          break;
        case METHODID_EXECUTE_MULTI_SIG_TRANSACTION:
          serviceImpl.executeMultiSigTransaction((com.blockchain.transaction.grpc.ExecuteMultiSigTxRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionResponse>) responseObserver);
          break;
        case METHODID_STREAM_TRANSACTION_STATUS:
          serviceImpl.streamTransactionStatus((com.blockchain.transaction.grpc.StreamTxStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.transaction.grpc.TransactionStatusUpdate>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSendTransactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.transaction.grpc.SendTransactionRequest,
              com.blockchain.transaction.grpc.TransactionResponse>(
                service, METHODID_SEND_TRANSACTION)))
        .addMethod(
          getGetTransactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.transaction.grpc.GetTransactionRequest,
              com.blockchain.transaction.grpc.TransactionResponse>(
                service, METHODID_GET_TRANSACTION)))
        .addMethod(
          getGetTransactionHistoryMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.transaction.grpc.GetHistoryRequest,
              com.blockchain.transaction.grpc.TransactionHistoryResponse>(
                service, METHODID_GET_TRANSACTION_HISTORY)))
        .addMethod(
          getEstimateGasMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.transaction.grpc.EstimateGasRequest,
              com.blockchain.transaction.grpc.GasEstimateResponse>(
                service, METHODID_ESTIMATE_GAS)))
        .addMethod(
          getCreateMultiSigTransactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.transaction.grpc.CreateMultiSigTxRequest,
              com.blockchain.transaction.grpc.MultiSigTxResponse>(
                service, METHODID_CREATE_MULTI_SIG_TRANSACTION)))
        .addMethod(
          getSignMultiSigTransactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.transaction.grpc.SignMultiSigTxRequest,
              com.blockchain.transaction.grpc.MultiSigTxResponse>(
                service, METHODID_SIGN_MULTI_SIG_TRANSACTION)))
        .addMethod(
          getGetPendingMultiSigTransactionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.transaction.grpc.GetPendingMultiSigTxRequest,
              com.blockchain.transaction.grpc.PendingMultiSigTxResponse>(
                service, METHODID_GET_PENDING_MULTI_SIG_TRANSACTIONS)))
        .addMethod(
          getExecuteMultiSigTransactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.transaction.grpc.ExecuteMultiSigTxRequest,
              com.blockchain.transaction.grpc.TransactionResponse>(
                service, METHODID_EXECUTE_MULTI_SIG_TRANSACTION)))
        .addMethod(
          getStreamTransactionStatusMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.blockchain.transaction.grpc.StreamTxStatusRequest,
              com.blockchain.transaction.grpc.TransactionStatusUpdate>(
                service, METHODID_STREAM_TRANSACTION_STATUS)))
        .build();
  }

  private static abstract class TransactionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TransactionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.blockchain.transaction.grpc.TransactionProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TransactionService");
    }
  }

  private static final class TransactionServiceFileDescriptorSupplier
      extends TransactionServiceBaseDescriptorSupplier {
    TransactionServiceFileDescriptorSupplier() {}
  }

  private static final class TransactionServiceMethodDescriptorSupplier
      extends TransactionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    TransactionServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TransactionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TransactionServiceFileDescriptorSupplier())
              .addMethod(getSendTransactionMethod())
              .addMethod(getGetTransactionMethod())
              .addMethod(getGetTransactionHistoryMethod())
              .addMethod(getEstimateGasMethod())
              .addMethod(getCreateMultiSigTransactionMethod())
              .addMethod(getSignMultiSigTransactionMethod())
              .addMethod(getGetPendingMultiSigTransactionsMethod())
              .addMethod(getExecuteMultiSigTransactionMethod())
              .addMethod(getStreamTransactionStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
