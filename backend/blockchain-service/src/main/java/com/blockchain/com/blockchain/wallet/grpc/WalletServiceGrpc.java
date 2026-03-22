package com.blockchain.wallet.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Wallet Service Definition
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.59.0)",
    comments = "Source: wallet.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class WalletServiceGrpc {

  private WalletServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "wallet.WalletService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.CreateWalletRequest,
      com.blockchain.wallet.grpc.CreateWalletResponse> getCreateWalletMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateWallet",
      requestType = com.blockchain.wallet.grpc.CreateWalletRequest.class,
      responseType = com.blockchain.wallet.grpc.CreateWalletResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.CreateWalletRequest,
      com.blockchain.wallet.grpc.CreateWalletResponse> getCreateWalletMethod() {
    io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.CreateWalletRequest, com.blockchain.wallet.grpc.CreateWalletResponse> getCreateWalletMethod;
    if ((getCreateWalletMethod = WalletServiceGrpc.getCreateWalletMethod) == null) {
      synchronized (WalletServiceGrpc.class) {
        if ((getCreateWalletMethod = WalletServiceGrpc.getCreateWalletMethod) == null) {
          WalletServiceGrpc.getCreateWalletMethod = getCreateWalletMethod =
              io.grpc.MethodDescriptor.<com.blockchain.wallet.grpc.CreateWalletRequest, com.blockchain.wallet.grpc.CreateWalletResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateWallet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.CreateWalletRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.CreateWalletResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WalletServiceMethodDescriptorSupplier("CreateWallet"))
              .build();
        }
      }
    }
    return getCreateWalletMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.GetWalletRequest,
      com.blockchain.wallet.grpc.WalletResponse> getGetWalletMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetWallet",
      requestType = com.blockchain.wallet.grpc.GetWalletRequest.class,
      responseType = com.blockchain.wallet.grpc.WalletResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.GetWalletRequest,
      com.blockchain.wallet.grpc.WalletResponse> getGetWalletMethod() {
    io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.GetWalletRequest, com.blockchain.wallet.grpc.WalletResponse> getGetWalletMethod;
    if ((getGetWalletMethod = WalletServiceGrpc.getGetWalletMethod) == null) {
      synchronized (WalletServiceGrpc.class) {
        if ((getGetWalletMethod = WalletServiceGrpc.getGetWalletMethod) == null) {
          WalletServiceGrpc.getGetWalletMethod = getGetWalletMethod =
              io.grpc.MethodDescriptor.<com.blockchain.wallet.grpc.GetWalletRequest, com.blockchain.wallet.grpc.WalletResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetWallet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.GetWalletRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.WalletResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WalletServiceMethodDescriptorSupplier("GetWallet"))
              .build();
        }
      }
    }
    return getGetWalletMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.GetUserWalletsRequest,
      com.blockchain.wallet.grpc.GetUserWalletsResponse> getGetUserWalletsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUserWallets",
      requestType = com.blockchain.wallet.grpc.GetUserWalletsRequest.class,
      responseType = com.blockchain.wallet.grpc.GetUserWalletsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.GetUserWalletsRequest,
      com.blockchain.wallet.grpc.GetUserWalletsResponse> getGetUserWalletsMethod() {
    io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.GetUserWalletsRequest, com.blockchain.wallet.grpc.GetUserWalletsResponse> getGetUserWalletsMethod;
    if ((getGetUserWalletsMethod = WalletServiceGrpc.getGetUserWalletsMethod) == null) {
      synchronized (WalletServiceGrpc.class) {
        if ((getGetUserWalletsMethod = WalletServiceGrpc.getGetUserWalletsMethod) == null) {
          WalletServiceGrpc.getGetUserWalletsMethod = getGetUserWalletsMethod =
              io.grpc.MethodDescriptor.<com.blockchain.wallet.grpc.GetUserWalletsRequest, com.blockchain.wallet.grpc.GetUserWalletsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUserWallets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.GetUserWalletsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.GetUserWalletsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WalletServiceMethodDescriptorSupplier("GetUserWallets"))
              .build();
        }
      }
    }
    return getGetUserWalletsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.ImportWalletRequest,
      com.blockchain.wallet.grpc.CreateWalletResponse> getImportWalletMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ImportWallet",
      requestType = com.blockchain.wallet.grpc.ImportWalletRequest.class,
      responseType = com.blockchain.wallet.grpc.CreateWalletResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.ImportWalletRequest,
      com.blockchain.wallet.grpc.CreateWalletResponse> getImportWalletMethod() {
    io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.ImportWalletRequest, com.blockchain.wallet.grpc.CreateWalletResponse> getImportWalletMethod;
    if ((getImportWalletMethod = WalletServiceGrpc.getImportWalletMethod) == null) {
      synchronized (WalletServiceGrpc.class) {
        if ((getImportWalletMethod = WalletServiceGrpc.getImportWalletMethod) == null) {
          WalletServiceGrpc.getImportWalletMethod = getImportWalletMethod =
              io.grpc.MethodDescriptor.<com.blockchain.wallet.grpc.ImportWalletRequest, com.blockchain.wallet.grpc.CreateWalletResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ImportWallet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.ImportWalletRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.CreateWalletResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WalletServiceMethodDescriptorSupplier("ImportWallet"))
              .build();
        }
      }
    }
    return getImportWalletMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.CreateMultiSigRequest,
      com.blockchain.wallet.grpc.MultiSigWalletResponse> getCreateMultiSigWalletMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateMultiSigWallet",
      requestType = com.blockchain.wallet.grpc.CreateMultiSigRequest.class,
      responseType = com.blockchain.wallet.grpc.MultiSigWalletResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.CreateMultiSigRequest,
      com.blockchain.wallet.grpc.MultiSigWalletResponse> getCreateMultiSigWalletMethod() {
    io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.CreateMultiSigRequest, com.blockchain.wallet.grpc.MultiSigWalletResponse> getCreateMultiSigWalletMethod;
    if ((getCreateMultiSigWalletMethod = WalletServiceGrpc.getCreateMultiSigWalletMethod) == null) {
      synchronized (WalletServiceGrpc.class) {
        if ((getCreateMultiSigWalletMethod = WalletServiceGrpc.getCreateMultiSigWalletMethod) == null) {
          WalletServiceGrpc.getCreateMultiSigWalletMethod = getCreateMultiSigWalletMethod =
              io.grpc.MethodDescriptor.<com.blockchain.wallet.grpc.CreateMultiSigRequest, com.blockchain.wallet.grpc.MultiSigWalletResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateMultiSigWallet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.CreateMultiSigRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.MultiSigWalletResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WalletServiceMethodDescriptorSupplier("CreateMultiSigWallet"))
              .build();
        }
      }
    }
    return getCreateMultiSigWalletMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.GetMultiSigRequest,
      com.blockchain.wallet.grpc.MultiSigWalletResponse> getGetMultiSigWalletMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMultiSigWallet",
      requestType = com.blockchain.wallet.grpc.GetMultiSigRequest.class,
      responseType = com.blockchain.wallet.grpc.MultiSigWalletResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.GetMultiSigRequest,
      com.blockchain.wallet.grpc.MultiSigWalletResponse> getGetMultiSigWalletMethod() {
    io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.GetMultiSigRequest, com.blockchain.wallet.grpc.MultiSigWalletResponse> getGetMultiSigWalletMethod;
    if ((getGetMultiSigWalletMethod = WalletServiceGrpc.getGetMultiSigWalletMethod) == null) {
      synchronized (WalletServiceGrpc.class) {
        if ((getGetMultiSigWalletMethod = WalletServiceGrpc.getGetMultiSigWalletMethod) == null) {
          WalletServiceGrpc.getGetMultiSigWalletMethod = getGetMultiSigWalletMethod =
              io.grpc.MethodDescriptor.<com.blockchain.wallet.grpc.GetMultiSigRequest, com.blockchain.wallet.grpc.MultiSigWalletResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMultiSigWallet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.GetMultiSigRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.MultiSigWalletResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WalletServiceMethodDescriptorSupplier("GetMultiSigWallet"))
              .build();
        }
      }
    }
    return getGetMultiSigWalletMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.AddSignerRequest,
      com.blockchain.wallet.grpc.MultiSigWalletResponse> getAddSignerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddSigner",
      requestType = com.blockchain.wallet.grpc.AddSignerRequest.class,
      responseType = com.blockchain.wallet.grpc.MultiSigWalletResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.AddSignerRequest,
      com.blockchain.wallet.grpc.MultiSigWalletResponse> getAddSignerMethod() {
    io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.AddSignerRequest, com.blockchain.wallet.grpc.MultiSigWalletResponse> getAddSignerMethod;
    if ((getAddSignerMethod = WalletServiceGrpc.getAddSignerMethod) == null) {
      synchronized (WalletServiceGrpc.class) {
        if ((getAddSignerMethod = WalletServiceGrpc.getAddSignerMethod) == null) {
          WalletServiceGrpc.getAddSignerMethod = getAddSignerMethod =
              io.grpc.MethodDescriptor.<com.blockchain.wallet.grpc.AddSignerRequest, com.blockchain.wallet.grpc.MultiSigWalletResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddSigner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.AddSignerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.MultiSigWalletResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WalletServiceMethodDescriptorSupplier("AddSigner"))
              .build();
        }
      }
    }
    return getAddSignerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.RemoveSignerRequest,
      com.blockchain.wallet.grpc.MultiSigWalletResponse> getRemoveSignerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveSigner",
      requestType = com.blockchain.wallet.grpc.RemoveSignerRequest.class,
      responseType = com.blockchain.wallet.grpc.MultiSigWalletResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.RemoveSignerRequest,
      com.blockchain.wallet.grpc.MultiSigWalletResponse> getRemoveSignerMethod() {
    io.grpc.MethodDescriptor<com.blockchain.wallet.grpc.RemoveSignerRequest, com.blockchain.wallet.grpc.MultiSigWalletResponse> getRemoveSignerMethod;
    if ((getRemoveSignerMethod = WalletServiceGrpc.getRemoveSignerMethod) == null) {
      synchronized (WalletServiceGrpc.class) {
        if ((getRemoveSignerMethod = WalletServiceGrpc.getRemoveSignerMethod) == null) {
          WalletServiceGrpc.getRemoveSignerMethod = getRemoveSignerMethod =
              io.grpc.MethodDescriptor.<com.blockchain.wallet.grpc.RemoveSignerRequest, com.blockchain.wallet.grpc.MultiSigWalletResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveSigner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.RemoveSignerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.wallet.grpc.MultiSigWalletResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WalletServiceMethodDescriptorSupplier("RemoveSigner"))
              .build();
        }
      }
    }
    return getRemoveSignerMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WalletServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WalletServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WalletServiceStub>() {
        @java.lang.Override
        public WalletServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WalletServiceStub(channel, callOptions);
        }
      };
    return WalletServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WalletServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WalletServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WalletServiceBlockingStub>() {
        @java.lang.Override
        public WalletServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WalletServiceBlockingStub(channel, callOptions);
        }
      };
    return WalletServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WalletServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WalletServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WalletServiceFutureStub>() {
        @java.lang.Override
        public WalletServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WalletServiceFutureStub(channel, callOptions);
        }
      };
    return WalletServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Wallet Service Definition
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Create a new wallet
     * </pre>
     */
    default void createWallet(com.blockchain.wallet.grpc.CreateWalletRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.CreateWalletResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateWalletMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get wallet by ID
     * </pre>
     */
    default void getWallet(com.blockchain.wallet.grpc.GetWalletRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.WalletResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetWalletMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get all wallets for a user
     * </pre>
     */
    default void getUserWallets(com.blockchain.wallet.grpc.GetUserWalletsRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.GetUserWalletsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUserWalletsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Import wallet from private key or mnemonic
     * </pre>
     */
    default void importWallet(com.blockchain.wallet.grpc.ImportWalletRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.CreateWalletResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getImportWalletMethod(), responseObserver);
    }

    /**
     * <pre>
     * Create multi-sig wallet
     * </pre>
     */
    default void createMultiSigWallet(com.blockchain.wallet.grpc.CreateMultiSigRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.MultiSigWalletResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateMultiSigWalletMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get multi-sig wallet details
     * </pre>
     */
    default void getMultiSigWallet(com.blockchain.wallet.grpc.GetMultiSigRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.MultiSigWalletResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMultiSigWalletMethod(), responseObserver);
    }

    /**
     * <pre>
     * Add signer to multi-sig
     * </pre>
     */
    default void addSigner(com.blockchain.wallet.grpc.AddSignerRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.MultiSigWalletResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddSignerMethod(), responseObserver);
    }

    /**
     * <pre>
     * Remove signer from multi-sig
     * </pre>
     */
    default void removeSigner(com.blockchain.wallet.grpc.RemoveSignerRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.MultiSigWalletResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveSignerMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service WalletService.
   * <pre>
   * Wallet Service Definition
   * </pre>
   */
  public static abstract class WalletServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return WalletServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service WalletService.
   * <pre>
   * Wallet Service Definition
   * </pre>
   */
  public static final class WalletServiceStub
      extends io.grpc.stub.AbstractAsyncStub<WalletServiceStub> {
    private WalletServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WalletServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Create a new wallet
     * </pre>
     */
    public void createWallet(com.blockchain.wallet.grpc.CreateWalletRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.CreateWalletResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateWalletMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get wallet by ID
     * </pre>
     */
    public void getWallet(com.blockchain.wallet.grpc.GetWalletRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.WalletResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetWalletMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get all wallets for a user
     * </pre>
     */
    public void getUserWallets(com.blockchain.wallet.grpc.GetUserWalletsRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.GetUserWalletsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUserWalletsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Import wallet from private key or mnemonic
     * </pre>
     */
    public void importWallet(com.blockchain.wallet.grpc.ImportWalletRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.CreateWalletResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getImportWalletMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Create multi-sig wallet
     * </pre>
     */
    public void createMultiSigWallet(com.blockchain.wallet.grpc.CreateMultiSigRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.MultiSigWalletResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateMultiSigWalletMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get multi-sig wallet details
     * </pre>
     */
    public void getMultiSigWallet(com.blockchain.wallet.grpc.GetMultiSigRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.MultiSigWalletResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMultiSigWalletMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Add signer to multi-sig
     * </pre>
     */
    public void addSigner(com.blockchain.wallet.grpc.AddSignerRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.MultiSigWalletResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddSignerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Remove signer from multi-sig
     * </pre>
     */
    public void removeSigner(com.blockchain.wallet.grpc.RemoveSignerRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.MultiSigWalletResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveSignerMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service WalletService.
   * <pre>
   * Wallet Service Definition
   * </pre>
   */
  public static final class WalletServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<WalletServiceBlockingStub> {
    private WalletServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WalletServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Create a new wallet
     * </pre>
     */
    public com.blockchain.wallet.grpc.CreateWalletResponse createWallet(com.blockchain.wallet.grpc.CreateWalletRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateWalletMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get wallet by ID
     * </pre>
     */
    public com.blockchain.wallet.grpc.WalletResponse getWallet(com.blockchain.wallet.grpc.GetWalletRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetWalletMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get all wallets for a user
     * </pre>
     */
    public com.blockchain.wallet.grpc.GetUserWalletsResponse getUserWallets(com.blockchain.wallet.grpc.GetUserWalletsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUserWalletsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Import wallet from private key or mnemonic
     * </pre>
     */
    public com.blockchain.wallet.grpc.CreateWalletResponse importWallet(com.blockchain.wallet.grpc.ImportWalletRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getImportWalletMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Create multi-sig wallet
     * </pre>
     */
    public com.blockchain.wallet.grpc.MultiSigWalletResponse createMultiSigWallet(com.blockchain.wallet.grpc.CreateMultiSigRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateMultiSigWalletMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get multi-sig wallet details
     * </pre>
     */
    public com.blockchain.wallet.grpc.MultiSigWalletResponse getMultiSigWallet(com.blockchain.wallet.grpc.GetMultiSigRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMultiSigWalletMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Add signer to multi-sig
     * </pre>
     */
    public com.blockchain.wallet.grpc.MultiSigWalletResponse addSigner(com.blockchain.wallet.grpc.AddSignerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddSignerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Remove signer from multi-sig
     * </pre>
     */
    public com.blockchain.wallet.grpc.MultiSigWalletResponse removeSigner(com.blockchain.wallet.grpc.RemoveSignerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveSignerMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service WalletService.
   * <pre>
   * Wallet Service Definition
   * </pre>
   */
  public static final class WalletServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<WalletServiceFutureStub> {
    private WalletServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WalletServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Create a new wallet
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.wallet.grpc.CreateWalletResponse> createWallet(
        com.blockchain.wallet.grpc.CreateWalletRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateWalletMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get wallet by ID
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.wallet.grpc.WalletResponse> getWallet(
        com.blockchain.wallet.grpc.GetWalletRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetWalletMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get all wallets for a user
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.wallet.grpc.GetUserWalletsResponse> getUserWallets(
        com.blockchain.wallet.grpc.GetUserWalletsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUserWalletsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Import wallet from private key or mnemonic
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.wallet.grpc.CreateWalletResponse> importWallet(
        com.blockchain.wallet.grpc.ImportWalletRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getImportWalletMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Create multi-sig wallet
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.wallet.grpc.MultiSigWalletResponse> createMultiSigWallet(
        com.blockchain.wallet.grpc.CreateMultiSigRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateMultiSigWalletMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get multi-sig wallet details
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.wallet.grpc.MultiSigWalletResponse> getMultiSigWallet(
        com.blockchain.wallet.grpc.GetMultiSigRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMultiSigWalletMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Add signer to multi-sig
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.wallet.grpc.MultiSigWalletResponse> addSigner(
        com.blockchain.wallet.grpc.AddSignerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddSignerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Remove signer from multi-sig
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.wallet.grpc.MultiSigWalletResponse> removeSigner(
        com.blockchain.wallet.grpc.RemoveSignerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveSignerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_WALLET = 0;
  private static final int METHODID_GET_WALLET = 1;
  private static final int METHODID_GET_USER_WALLETS = 2;
  private static final int METHODID_IMPORT_WALLET = 3;
  private static final int METHODID_CREATE_MULTI_SIG_WALLET = 4;
  private static final int METHODID_GET_MULTI_SIG_WALLET = 5;
  private static final int METHODID_ADD_SIGNER = 6;
  private static final int METHODID_REMOVE_SIGNER = 7;

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
        case METHODID_CREATE_WALLET:
          serviceImpl.createWallet((com.blockchain.wallet.grpc.CreateWalletRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.CreateWalletResponse>) responseObserver);
          break;
        case METHODID_GET_WALLET:
          serviceImpl.getWallet((com.blockchain.wallet.grpc.GetWalletRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.WalletResponse>) responseObserver);
          break;
        case METHODID_GET_USER_WALLETS:
          serviceImpl.getUserWallets((com.blockchain.wallet.grpc.GetUserWalletsRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.GetUserWalletsResponse>) responseObserver);
          break;
        case METHODID_IMPORT_WALLET:
          serviceImpl.importWallet((com.blockchain.wallet.grpc.ImportWalletRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.CreateWalletResponse>) responseObserver);
          break;
        case METHODID_CREATE_MULTI_SIG_WALLET:
          serviceImpl.createMultiSigWallet((com.blockchain.wallet.grpc.CreateMultiSigRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.MultiSigWalletResponse>) responseObserver);
          break;
        case METHODID_GET_MULTI_SIG_WALLET:
          serviceImpl.getMultiSigWallet((com.blockchain.wallet.grpc.GetMultiSigRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.MultiSigWalletResponse>) responseObserver);
          break;
        case METHODID_ADD_SIGNER:
          serviceImpl.addSigner((com.blockchain.wallet.grpc.AddSignerRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.MultiSigWalletResponse>) responseObserver);
          break;
        case METHODID_REMOVE_SIGNER:
          serviceImpl.removeSigner((com.blockchain.wallet.grpc.RemoveSignerRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.wallet.grpc.MultiSigWalletResponse>) responseObserver);
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
          getCreateWalletMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.wallet.grpc.CreateWalletRequest,
              com.blockchain.wallet.grpc.CreateWalletResponse>(
                service, METHODID_CREATE_WALLET)))
        .addMethod(
          getGetWalletMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.wallet.grpc.GetWalletRequest,
              com.blockchain.wallet.grpc.WalletResponse>(
                service, METHODID_GET_WALLET)))
        .addMethod(
          getGetUserWalletsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.wallet.grpc.GetUserWalletsRequest,
              com.blockchain.wallet.grpc.GetUserWalletsResponse>(
                service, METHODID_GET_USER_WALLETS)))
        .addMethod(
          getImportWalletMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.wallet.grpc.ImportWalletRequest,
              com.blockchain.wallet.grpc.CreateWalletResponse>(
                service, METHODID_IMPORT_WALLET)))
        .addMethod(
          getCreateMultiSigWalletMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.wallet.grpc.CreateMultiSigRequest,
              com.blockchain.wallet.grpc.MultiSigWalletResponse>(
                service, METHODID_CREATE_MULTI_SIG_WALLET)))
        .addMethod(
          getGetMultiSigWalletMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.wallet.grpc.GetMultiSigRequest,
              com.blockchain.wallet.grpc.MultiSigWalletResponse>(
                service, METHODID_GET_MULTI_SIG_WALLET)))
        .addMethod(
          getAddSignerMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.wallet.grpc.AddSignerRequest,
              com.blockchain.wallet.grpc.MultiSigWalletResponse>(
                service, METHODID_ADD_SIGNER)))
        .addMethod(
          getRemoveSignerMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.wallet.grpc.RemoveSignerRequest,
              com.blockchain.wallet.grpc.MultiSigWalletResponse>(
                service, METHODID_REMOVE_SIGNER)))
        .build();
  }

  private static abstract class WalletServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WalletServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.blockchain.wallet.grpc.WalletProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("WalletService");
    }
  }

  private static final class WalletServiceFileDescriptorSupplier
      extends WalletServiceBaseDescriptorSupplier {
    WalletServiceFileDescriptorSupplier() {}
  }

  private static final class WalletServiceMethodDescriptorSupplier
      extends WalletServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    WalletServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (WalletServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WalletServiceFileDescriptorSupplier())
              .addMethod(getCreateWalletMethod())
              .addMethod(getGetWalletMethod())
              .addMethod(getGetUserWalletsMethod())
              .addMethod(getImportWalletMethod())
              .addMethod(getCreateMultiSigWalletMethod())
              .addMethod(getGetMultiSigWalletMethod())
              .addMethod(getAddSignerMethod())
              .addMethod(getRemoveSignerMethod())
              .build();
        }
      }
    }
    return result;
  }
}
