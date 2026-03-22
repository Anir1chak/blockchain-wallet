package com.blockchain.blockchain.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Blockchain Service Definition
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.59.0)",
    comments = "Source: blockchain.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BlockchainServiceGrpc {

  private BlockchainServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "blockchain.BlockchainService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetBalanceRequest,
      com.blockchain.blockchain.grpc.BalanceResponse> getGetBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBalance",
      requestType = com.blockchain.blockchain.grpc.GetBalanceRequest.class,
      responseType = com.blockchain.blockchain.grpc.BalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetBalanceRequest,
      com.blockchain.blockchain.grpc.BalanceResponse> getGetBalanceMethod() {
    io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetBalanceRequest, com.blockchain.blockchain.grpc.BalanceResponse> getGetBalanceMethod;
    if ((getGetBalanceMethod = BlockchainServiceGrpc.getGetBalanceMethod) == null) {
      synchronized (BlockchainServiceGrpc.class) {
        if ((getGetBalanceMethod = BlockchainServiceGrpc.getGetBalanceMethod) == null) {
          BlockchainServiceGrpc.getGetBalanceMethod = getGetBalanceMethod =
              io.grpc.MethodDescriptor.<com.blockchain.blockchain.grpc.GetBalanceRequest, com.blockchain.blockchain.grpc.BalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.GetBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.BalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlockchainServiceMethodDescriptorSupplier("GetBalance"))
              .build();
        }
      }
    }
    return getGetBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetBalancesRequest,
      com.blockchain.blockchain.grpc.GetBalancesResponse> getGetBalancesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBalances",
      requestType = com.blockchain.blockchain.grpc.GetBalancesRequest.class,
      responseType = com.blockchain.blockchain.grpc.GetBalancesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetBalancesRequest,
      com.blockchain.blockchain.grpc.GetBalancesResponse> getGetBalancesMethod() {
    io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetBalancesRequest, com.blockchain.blockchain.grpc.GetBalancesResponse> getGetBalancesMethod;
    if ((getGetBalancesMethod = BlockchainServiceGrpc.getGetBalancesMethod) == null) {
      synchronized (BlockchainServiceGrpc.class) {
        if ((getGetBalancesMethod = BlockchainServiceGrpc.getGetBalancesMethod) == null) {
          BlockchainServiceGrpc.getGetBalancesMethod = getGetBalancesMethod =
              io.grpc.MethodDescriptor.<com.blockchain.blockchain.grpc.GetBalancesRequest, com.blockchain.blockchain.grpc.GetBalancesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBalances"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.GetBalancesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.GetBalancesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlockchainServiceMethodDescriptorSupplier("GetBalances"))
              .build();
        }
      }
    }
    return getGetBalancesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetGasPricesRequest,
      com.blockchain.blockchain.grpc.GasPricesResponse> getGetGasPricesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetGasPrices",
      requestType = com.blockchain.blockchain.grpc.GetGasPricesRequest.class,
      responseType = com.blockchain.blockchain.grpc.GasPricesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetGasPricesRequest,
      com.blockchain.blockchain.grpc.GasPricesResponse> getGetGasPricesMethod() {
    io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetGasPricesRequest, com.blockchain.blockchain.grpc.GasPricesResponse> getGetGasPricesMethod;
    if ((getGetGasPricesMethod = BlockchainServiceGrpc.getGetGasPricesMethod) == null) {
      synchronized (BlockchainServiceGrpc.class) {
        if ((getGetGasPricesMethod = BlockchainServiceGrpc.getGetGasPricesMethod) == null) {
          BlockchainServiceGrpc.getGetGasPricesMethod = getGetGasPricesMethod =
              io.grpc.MethodDescriptor.<com.blockchain.blockchain.grpc.GetGasPricesRequest, com.blockchain.blockchain.grpc.GasPricesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetGasPrices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.GetGasPricesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.GasPricesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlockchainServiceMethodDescriptorSupplier("GetGasPrices"))
              .build();
        }
      }
    }
    return getGetGasPricesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetBlockNumberRequest,
      com.blockchain.blockchain.grpc.BlockNumberResponse> getGetBlockNumberMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBlockNumber",
      requestType = com.blockchain.blockchain.grpc.GetBlockNumberRequest.class,
      responseType = com.blockchain.blockchain.grpc.BlockNumberResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetBlockNumberRequest,
      com.blockchain.blockchain.grpc.BlockNumberResponse> getGetBlockNumberMethod() {
    io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetBlockNumberRequest, com.blockchain.blockchain.grpc.BlockNumberResponse> getGetBlockNumberMethod;
    if ((getGetBlockNumberMethod = BlockchainServiceGrpc.getGetBlockNumberMethod) == null) {
      synchronized (BlockchainServiceGrpc.class) {
        if ((getGetBlockNumberMethod = BlockchainServiceGrpc.getGetBlockNumberMethod) == null) {
          BlockchainServiceGrpc.getGetBlockNumberMethod = getGetBlockNumberMethod =
              io.grpc.MethodDescriptor.<com.blockchain.blockchain.grpc.GetBlockNumberRequest, com.blockchain.blockchain.grpc.BlockNumberResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlockNumber"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.GetBlockNumberRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.BlockNumberResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlockchainServiceMethodDescriptorSupplier("GetBlockNumber"))
              .build();
        }
      }
    }
    return getGetBlockNumberMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.ValidateAddressRequest,
      com.blockchain.blockchain.grpc.ValidateAddressResponse> getValidateAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidateAddress",
      requestType = com.blockchain.blockchain.grpc.ValidateAddressRequest.class,
      responseType = com.blockchain.blockchain.grpc.ValidateAddressResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.ValidateAddressRequest,
      com.blockchain.blockchain.grpc.ValidateAddressResponse> getValidateAddressMethod() {
    io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.ValidateAddressRequest, com.blockchain.blockchain.grpc.ValidateAddressResponse> getValidateAddressMethod;
    if ((getValidateAddressMethod = BlockchainServiceGrpc.getValidateAddressMethod) == null) {
      synchronized (BlockchainServiceGrpc.class) {
        if ((getValidateAddressMethod = BlockchainServiceGrpc.getValidateAddressMethod) == null) {
          BlockchainServiceGrpc.getValidateAddressMethod = getValidateAddressMethod =
              io.grpc.MethodDescriptor.<com.blockchain.blockchain.grpc.ValidateAddressRequest, com.blockchain.blockchain.grpc.ValidateAddressResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidateAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.ValidateAddressRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.ValidateAddressResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlockchainServiceMethodDescriptorSupplier("ValidateAddress"))
              .build();
        }
      }
    }
    return getValidateAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetTokenBalanceRequest,
      com.blockchain.blockchain.grpc.TokenBalanceResponse> getGetTokenBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTokenBalance",
      requestType = com.blockchain.blockchain.grpc.GetTokenBalanceRequest.class,
      responseType = com.blockchain.blockchain.grpc.TokenBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetTokenBalanceRequest,
      com.blockchain.blockchain.grpc.TokenBalanceResponse> getGetTokenBalanceMethod() {
    io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetTokenBalanceRequest, com.blockchain.blockchain.grpc.TokenBalanceResponse> getGetTokenBalanceMethod;
    if ((getGetTokenBalanceMethod = BlockchainServiceGrpc.getGetTokenBalanceMethod) == null) {
      synchronized (BlockchainServiceGrpc.class) {
        if ((getGetTokenBalanceMethod = BlockchainServiceGrpc.getGetTokenBalanceMethod) == null) {
          BlockchainServiceGrpc.getGetTokenBalanceMethod = getGetTokenBalanceMethod =
              io.grpc.MethodDescriptor.<com.blockchain.blockchain.grpc.GetTokenBalanceRequest, com.blockchain.blockchain.grpc.TokenBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTokenBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.GetTokenBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.TokenBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlockchainServiceMethodDescriptorSupplier("GetTokenBalance"))
              .build();
        }
      }
    }
    return getGetTokenBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.StreamBlocksRequest,
      com.blockchain.blockchain.grpc.BlockUpdate> getStreamBlocksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamBlocks",
      requestType = com.blockchain.blockchain.grpc.StreamBlocksRequest.class,
      responseType = com.blockchain.blockchain.grpc.BlockUpdate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.StreamBlocksRequest,
      com.blockchain.blockchain.grpc.BlockUpdate> getStreamBlocksMethod() {
    io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.StreamBlocksRequest, com.blockchain.blockchain.grpc.BlockUpdate> getStreamBlocksMethod;
    if ((getStreamBlocksMethod = BlockchainServiceGrpc.getStreamBlocksMethod) == null) {
      synchronized (BlockchainServiceGrpc.class) {
        if ((getStreamBlocksMethod = BlockchainServiceGrpc.getStreamBlocksMethod) == null) {
          BlockchainServiceGrpc.getStreamBlocksMethod = getStreamBlocksMethod =
              io.grpc.MethodDescriptor.<com.blockchain.blockchain.grpc.StreamBlocksRequest, com.blockchain.blockchain.grpc.BlockUpdate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamBlocks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.StreamBlocksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.BlockUpdate.getDefaultInstance()))
              .setSchemaDescriptor(new BlockchainServiceMethodDescriptorSupplier("StreamBlocks"))
              .build();
        }
      }
    }
    return getStreamBlocksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.StreamBalanceRequest,
      com.blockchain.blockchain.grpc.BalanceUpdate> getStreamBalanceUpdatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamBalanceUpdates",
      requestType = com.blockchain.blockchain.grpc.StreamBalanceRequest.class,
      responseType = com.blockchain.blockchain.grpc.BalanceUpdate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.StreamBalanceRequest,
      com.blockchain.blockchain.grpc.BalanceUpdate> getStreamBalanceUpdatesMethod() {
    io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.StreamBalanceRequest, com.blockchain.blockchain.grpc.BalanceUpdate> getStreamBalanceUpdatesMethod;
    if ((getStreamBalanceUpdatesMethod = BlockchainServiceGrpc.getStreamBalanceUpdatesMethod) == null) {
      synchronized (BlockchainServiceGrpc.class) {
        if ((getStreamBalanceUpdatesMethod = BlockchainServiceGrpc.getStreamBalanceUpdatesMethod) == null) {
          BlockchainServiceGrpc.getStreamBalanceUpdatesMethod = getStreamBalanceUpdatesMethod =
              io.grpc.MethodDescriptor.<com.blockchain.blockchain.grpc.StreamBalanceRequest, com.blockchain.blockchain.grpc.BalanceUpdate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamBalanceUpdates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.StreamBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.BalanceUpdate.getDefaultInstance()))
              .setSchemaDescriptor(new BlockchainServiceMethodDescriptorSupplier("StreamBalanceUpdates"))
              .build();
        }
      }
    }
    return getStreamBalanceUpdatesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetNetworkStatusRequest,
      com.blockchain.blockchain.grpc.NetworkStatusResponse> getGetNetworkStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetNetworkStatus",
      requestType = com.blockchain.blockchain.grpc.GetNetworkStatusRequest.class,
      responseType = com.blockchain.blockchain.grpc.NetworkStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetNetworkStatusRequest,
      com.blockchain.blockchain.grpc.NetworkStatusResponse> getGetNetworkStatusMethod() {
    io.grpc.MethodDescriptor<com.blockchain.blockchain.grpc.GetNetworkStatusRequest, com.blockchain.blockchain.grpc.NetworkStatusResponse> getGetNetworkStatusMethod;
    if ((getGetNetworkStatusMethod = BlockchainServiceGrpc.getGetNetworkStatusMethod) == null) {
      synchronized (BlockchainServiceGrpc.class) {
        if ((getGetNetworkStatusMethod = BlockchainServiceGrpc.getGetNetworkStatusMethod) == null) {
          BlockchainServiceGrpc.getGetNetworkStatusMethod = getGetNetworkStatusMethod =
              io.grpc.MethodDescriptor.<com.blockchain.blockchain.grpc.GetNetworkStatusRequest, com.blockchain.blockchain.grpc.NetworkStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetNetworkStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.GetNetworkStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.blockchain.grpc.NetworkStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BlockchainServiceMethodDescriptorSupplier("GetNetworkStatus"))
              .build();
        }
      }
    }
    return getGetNetworkStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BlockchainServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BlockchainServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BlockchainServiceStub>() {
        @java.lang.Override
        public BlockchainServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BlockchainServiceStub(channel, callOptions);
        }
      };
    return BlockchainServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BlockchainServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BlockchainServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BlockchainServiceBlockingStub>() {
        @java.lang.Override
        public BlockchainServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BlockchainServiceBlockingStub(channel, callOptions);
        }
      };
    return BlockchainServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BlockchainServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BlockchainServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BlockchainServiceFutureStub>() {
        @java.lang.Override
        public BlockchainServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BlockchainServiceFutureStub(channel, callOptions);
        }
      };
    return BlockchainServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Blockchain Service Definition
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Get balance for an address
     * </pre>
     */
    default void getBalance(com.blockchain.blockchain.grpc.GetBalanceRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.BalanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get multiple balances
     * </pre>
     */
    default void getBalances(com.blockchain.blockchain.grpc.GetBalancesRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.GetBalancesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBalancesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get current gas prices
     * </pre>
     */
    default void getGasPrices(com.blockchain.blockchain.grpc.GetGasPricesRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.GasPricesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetGasPricesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get current block number
     * </pre>
     */
    default void getBlockNumber(com.blockchain.blockchain.grpc.GetBlockNumberRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.BlockNumberResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBlockNumberMethod(), responseObserver);
    }

    /**
     * <pre>
     * Validate an address
     * </pre>
     */
    default void validateAddress(com.blockchain.blockchain.grpc.ValidateAddressRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.ValidateAddressResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidateAddressMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get token balance (ERC20)
     * </pre>
     */
    default void getTokenBalance(com.blockchain.blockchain.grpc.GetTokenBalanceRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.TokenBalanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTokenBalanceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Stream new blocks
     * </pre>
     */
    default void streamBlocks(com.blockchain.blockchain.grpc.StreamBlocksRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.BlockUpdate> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamBlocksMethod(), responseObserver);
    }

    /**
     * <pre>
     * Stream balance updates for addresses
     * </pre>
     */
    default void streamBalanceUpdates(com.blockchain.blockchain.grpc.StreamBalanceRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.BalanceUpdate> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamBalanceUpdatesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get network status
     * </pre>
     */
    default void getNetworkStatus(com.blockchain.blockchain.grpc.GetNetworkStatusRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.NetworkStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetNetworkStatusMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service BlockchainService.
   * <pre>
   * Blockchain Service Definition
   * </pre>
   */
  public static abstract class BlockchainServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return BlockchainServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service BlockchainService.
   * <pre>
   * Blockchain Service Definition
   * </pre>
   */
  public static final class BlockchainServiceStub
      extends io.grpc.stub.AbstractAsyncStub<BlockchainServiceStub> {
    private BlockchainServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlockchainServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BlockchainServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get balance for an address
     * </pre>
     */
    public void getBalance(com.blockchain.blockchain.grpc.GetBalanceRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.BalanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get multiple balances
     * </pre>
     */
    public void getBalances(com.blockchain.blockchain.grpc.GetBalancesRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.GetBalancesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBalancesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get current gas prices
     * </pre>
     */
    public void getGasPrices(com.blockchain.blockchain.grpc.GetGasPricesRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.GasPricesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetGasPricesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get current block number
     * </pre>
     */
    public void getBlockNumber(com.blockchain.blockchain.grpc.GetBlockNumberRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.BlockNumberResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBlockNumberMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Validate an address
     * </pre>
     */
    public void validateAddress(com.blockchain.blockchain.grpc.ValidateAddressRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.ValidateAddressResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidateAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get token balance (ERC20)
     * </pre>
     */
    public void getTokenBalance(com.blockchain.blockchain.grpc.GetTokenBalanceRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.TokenBalanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTokenBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Stream new blocks
     * </pre>
     */
    public void streamBlocks(com.blockchain.blockchain.grpc.StreamBlocksRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.BlockUpdate> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamBlocksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Stream balance updates for addresses
     * </pre>
     */
    public void streamBalanceUpdates(com.blockchain.blockchain.grpc.StreamBalanceRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.BalanceUpdate> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamBalanceUpdatesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get network status
     * </pre>
     */
    public void getNetworkStatus(com.blockchain.blockchain.grpc.GetNetworkStatusRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.NetworkStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetNetworkStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service BlockchainService.
   * <pre>
   * Blockchain Service Definition
   * </pre>
   */
  public static final class BlockchainServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BlockchainServiceBlockingStub> {
    private BlockchainServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlockchainServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BlockchainServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get balance for an address
     * </pre>
     */
    public com.blockchain.blockchain.grpc.BalanceResponse getBalance(com.blockchain.blockchain.grpc.GetBalanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get multiple balances
     * </pre>
     */
    public com.blockchain.blockchain.grpc.GetBalancesResponse getBalances(com.blockchain.blockchain.grpc.GetBalancesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBalancesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get current gas prices
     * </pre>
     */
    public com.blockchain.blockchain.grpc.GasPricesResponse getGasPrices(com.blockchain.blockchain.grpc.GetGasPricesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetGasPricesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get current block number
     * </pre>
     */
    public com.blockchain.blockchain.grpc.BlockNumberResponse getBlockNumber(com.blockchain.blockchain.grpc.GetBlockNumberRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBlockNumberMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Validate an address
     * </pre>
     */
    public com.blockchain.blockchain.grpc.ValidateAddressResponse validateAddress(com.blockchain.blockchain.grpc.ValidateAddressRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidateAddressMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get token balance (ERC20)
     * </pre>
     */
    public com.blockchain.blockchain.grpc.TokenBalanceResponse getTokenBalance(com.blockchain.blockchain.grpc.GetTokenBalanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTokenBalanceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Stream new blocks
     * </pre>
     */
    public java.util.Iterator<com.blockchain.blockchain.grpc.BlockUpdate> streamBlocks(
        com.blockchain.blockchain.grpc.StreamBlocksRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamBlocksMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Stream balance updates for addresses
     * </pre>
     */
    public java.util.Iterator<com.blockchain.blockchain.grpc.BalanceUpdate> streamBalanceUpdates(
        com.blockchain.blockchain.grpc.StreamBalanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamBalanceUpdatesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get network status
     * </pre>
     */
    public com.blockchain.blockchain.grpc.NetworkStatusResponse getNetworkStatus(com.blockchain.blockchain.grpc.GetNetworkStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetNetworkStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service BlockchainService.
   * <pre>
   * Blockchain Service Definition
   * </pre>
   */
  public static final class BlockchainServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<BlockchainServiceFutureStub> {
    private BlockchainServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlockchainServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BlockchainServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get balance for an address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.blockchain.grpc.BalanceResponse> getBalance(
        com.blockchain.blockchain.grpc.GetBalanceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get multiple balances
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.blockchain.grpc.GetBalancesResponse> getBalances(
        com.blockchain.blockchain.grpc.GetBalancesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBalancesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get current gas prices
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.blockchain.grpc.GasPricesResponse> getGasPrices(
        com.blockchain.blockchain.grpc.GetGasPricesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetGasPricesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get current block number
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.blockchain.grpc.BlockNumberResponse> getBlockNumber(
        com.blockchain.blockchain.grpc.GetBlockNumberRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBlockNumberMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Validate an address
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.blockchain.grpc.ValidateAddressResponse> validateAddress(
        com.blockchain.blockchain.grpc.ValidateAddressRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidateAddressMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get token balance (ERC20)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.blockchain.grpc.TokenBalanceResponse> getTokenBalance(
        com.blockchain.blockchain.grpc.GetTokenBalanceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTokenBalanceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get network status
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.blockchain.grpc.NetworkStatusResponse> getNetworkStatus(
        com.blockchain.blockchain.grpc.GetNetworkStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetNetworkStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BALANCE = 0;
  private static final int METHODID_GET_BALANCES = 1;
  private static final int METHODID_GET_GAS_PRICES = 2;
  private static final int METHODID_GET_BLOCK_NUMBER = 3;
  private static final int METHODID_VALIDATE_ADDRESS = 4;
  private static final int METHODID_GET_TOKEN_BALANCE = 5;
  private static final int METHODID_STREAM_BLOCKS = 6;
  private static final int METHODID_STREAM_BALANCE_UPDATES = 7;
  private static final int METHODID_GET_NETWORK_STATUS = 8;

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
        case METHODID_GET_BALANCE:
          serviceImpl.getBalance((com.blockchain.blockchain.grpc.GetBalanceRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.BalanceResponse>) responseObserver);
          break;
        case METHODID_GET_BALANCES:
          serviceImpl.getBalances((com.blockchain.blockchain.grpc.GetBalancesRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.GetBalancesResponse>) responseObserver);
          break;
        case METHODID_GET_GAS_PRICES:
          serviceImpl.getGasPrices((com.blockchain.blockchain.grpc.GetGasPricesRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.GasPricesResponse>) responseObserver);
          break;
        case METHODID_GET_BLOCK_NUMBER:
          serviceImpl.getBlockNumber((com.blockchain.blockchain.grpc.GetBlockNumberRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.BlockNumberResponse>) responseObserver);
          break;
        case METHODID_VALIDATE_ADDRESS:
          serviceImpl.validateAddress((com.blockchain.blockchain.grpc.ValidateAddressRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.ValidateAddressResponse>) responseObserver);
          break;
        case METHODID_GET_TOKEN_BALANCE:
          serviceImpl.getTokenBalance((com.blockchain.blockchain.grpc.GetTokenBalanceRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.TokenBalanceResponse>) responseObserver);
          break;
        case METHODID_STREAM_BLOCKS:
          serviceImpl.streamBlocks((com.blockchain.blockchain.grpc.StreamBlocksRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.BlockUpdate>) responseObserver);
          break;
        case METHODID_STREAM_BALANCE_UPDATES:
          serviceImpl.streamBalanceUpdates((com.blockchain.blockchain.grpc.StreamBalanceRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.BalanceUpdate>) responseObserver);
          break;
        case METHODID_GET_NETWORK_STATUS:
          serviceImpl.getNetworkStatus((com.blockchain.blockchain.grpc.GetNetworkStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.blockchain.grpc.NetworkStatusResponse>) responseObserver);
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
          getGetBalanceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.blockchain.grpc.GetBalanceRequest,
              com.blockchain.blockchain.grpc.BalanceResponse>(
                service, METHODID_GET_BALANCE)))
        .addMethod(
          getGetBalancesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.blockchain.grpc.GetBalancesRequest,
              com.blockchain.blockchain.grpc.GetBalancesResponse>(
                service, METHODID_GET_BALANCES)))
        .addMethod(
          getGetGasPricesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.blockchain.grpc.GetGasPricesRequest,
              com.blockchain.blockchain.grpc.GasPricesResponse>(
                service, METHODID_GET_GAS_PRICES)))
        .addMethod(
          getGetBlockNumberMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.blockchain.grpc.GetBlockNumberRequest,
              com.blockchain.blockchain.grpc.BlockNumberResponse>(
                service, METHODID_GET_BLOCK_NUMBER)))
        .addMethod(
          getValidateAddressMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.blockchain.grpc.ValidateAddressRequest,
              com.blockchain.blockchain.grpc.ValidateAddressResponse>(
                service, METHODID_VALIDATE_ADDRESS)))
        .addMethod(
          getGetTokenBalanceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.blockchain.grpc.GetTokenBalanceRequest,
              com.blockchain.blockchain.grpc.TokenBalanceResponse>(
                service, METHODID_GET_TOKEN_BALANCE)))
        .addMethod(
          getStreamBlocksMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.blockchain.blockchain.grpc.StreamBlocksRequest,
              com.blockchain.blockchain.grpc.BlockUpdate>(
                service, METHODID_STREAM_BLOCKS)))
        .addMethod(
          getStreamBalanceUpdatesMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.blockchain.blockchain.grpc.StreamBalanceRequest,
              com.blockchain.blockchain.grpc.BalanceUpdate>(
                service, METHODID_STREAM_BALANCE_UPDATES)))
        .addMethod(
          getGetNetworkStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.blockchain.grpc.GetNetworkStatusRequest,
              com.blockchain.blockchain.grpc.NetworkStatusResponse>(
                service, METHODID_GET_NETWORK_STATUS)))
        .build();
  }

  private static abstract class BlockchainServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BlockchainServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.blockchain.blockchain.grpc.BlockchainProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BlockchainService");
    }
  }

  private static final class BlockchainServiceFileDescriptorSupplier
      extends BlockchainServiceBaseDescriptorSupplier {
    BlockchainServiceFileDescriptorSupplier() {}
  }

  private static final class BlockchainServiceMethodDescriptorSupplier
      extends BlockchainServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    BlockchainServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (BlockchainServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BlockchainServiceFileDescriptorSupplier())
              .addMethod(getGetBalanceMethod())
              .addMethod(getGetBalancesMethod())
              .addMethod(getGetGasPricesMethod())
              .addMethod(getGetBlockNumberMethod())
              .addMethod(getValidateAddressMethod())
              .addMethod(getGetTokenBalanceMethod())
              .addMethod(getStreamBlocksMethod())
              .addMethod(getStreamBalanceUpdatesMethod())
              .addMethod(getGetNetworkStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
