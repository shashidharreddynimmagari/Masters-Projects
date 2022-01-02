package de.uniba.rz.backend;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.20.0)",
    comments = "Source: ticketManagement.proto")
public final class TicketManagementGrpc {

  private TicketManagementGrpc() {}

  public static final String SERVICE_NAME = "TicketManagement";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<de.uniba.rz.backend.TicketRequest,
      de.uniba.rz.backend.TicketResponse> getCreateTicketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createTicket",
      requestType = de.uniba.rz.backend.TicketRequest.class,
      responseType = de.uniba.rz.backend.TicketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<de.uniba.rz.backend.TicketRequest,
      de.uniba.rz.backend.TicketResponse> getCreateTicketMethod() {
    io.grpc.MethodDescriptor<de.uniba.rz.backend.TicketRequest, de.uniba.rz.backend.TicketResponse> getCreateTicketMethod;
    if ((getCreateTicketMethod = TicketManagementGrpc.getCreateTicketMethod) == null) {
      synchronized (TicketManagementGrpc.class) {
        if ((getCreateTicketMethod = TicketManagementGrpc.getCreateTicketMethod) == null) {
          TicketManagementGrpc.getCreateTicketMethod = getCreateTicketMethod = 
              io.grpc.MethodDescriptor.<de.uniba.rz.backend.TicketRequest, de.uniba.rz.backend.TicketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TicketManagement", "createTicket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  de.uniba.rz.backend.TicketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  de.uniba.rz.backend.TicketResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TicketManagementMethodDescriptorSupplier("createTicket"))
                  .build();
          }
        }
     }
     return getCreateTicketMethod;
  }

  private static volatile io.grpc.MethodDescriptor<de.uniba.rz.backend.SearchFilter,
      de.uniba.rz.backend.SearchResponse> getGetAllTicketsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAllTickets",
      requestType = de.uniba.rz.backend.SearchFilter.class,
      responseType = de.uniba.rz.backend.SearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<de.uniba.rz.backend.SearchFilter,
      de.uniba.rz.backend.SearchResponse> getGetAllTicketsMethod() {
    io.grpc.MethodDescriptor<de.uniba.rz.backend.SearchFilter, de.uniba.rz.backend.SearchResponse> getGetAllTicketsMethod;
    if ((getGetAllTicketsMethod = TicketManagementGrpc.getGetAllTicketsMethod) == null) {
      synchronized (TicketManagementGrpc.class) {
        if ((getGetAllTicketsMethod = TicketManagementGrpc.getGetAllTicketsMethod) == null) {
          TicketManagementGrpc.getGetAllTicketsMethod = getGetAllTicketsMethod = 
              io.grpc.MethodDescriptor.<de.uniba.rz.backend.SearchFilter, de.uniba.rz.backend.SearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TicketManagement", "getAllTickets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  de.uniba.rz.backend.SearchFilter.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  de.uniba.rz.backend.SearchResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TicketManagementMethodDescriptorSupplier("getAllTickets"))
                  .build();
          }
        }
     }
     return getGetAllTicketsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<de.uniba.rz.backend.GetTicketByIdRequest,
      de.uniba.rz.backend.GetTicketByIdResponse> getGetTicketByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTicketById",
      requestType = de.uniba.rz.backend.GetTicketByIdRequest.class,
      responseType = de.uniba.rz.backend.GetTicketByIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<de.uniba.rz.backend.GetTicketByIdRequest,
      de.uniba.rz.backend.GetTicketByIdResponse> getGetTicketByIdMethod() {
    io.grpc.MethodDescriptor<de.uniba.rz.backend.GetTicketByIdRequest, de.uniba.rz.backend.GetTicketByIdResponse> getGetTicketByIdMethod;
    if ((getGetTicketByIdMethod = TicketManagementGrpc.getGetTicketByIdMethod) == null) {
      synchronized (TicketManagementGrpc.class) {
        if ((getGetTicketByIdMethod = TicketManagementGrpc.getGetTicketByIdMethod) == null) {
          TicketManagementGrpc.getGetTicketByIdMethod = getGetTicketByIdMethod = 
              io.grpc.MethodDescriptor.<de.uniba.rz.backend.GetTicketByIdRequest, de.uniba.rz.backend.GetTicketByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TicketManagement", "GetTicketById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  de.uniba.rz.backend.GetTicketByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  de.uniba.rz.backend.GetTicketByIdResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TicketManagementMethodDescriptorSupplier("GetTicketById"))
                  .build();
          }
        }
     }
     return getGetTicketByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<de.uniba.rz.backend.UpdateTicketRequest,
      de.uniba.rz.backend.UpdateTicketResponse> getUpdateTicketMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateTicket",
      requestType = de.uniba.rz.backend.UpdateTicketRequest.class,
      responseType = de.uniba.rz.backend.UpdateTicketResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<de.uniba.rz.backend.UpdateTicketRequest,
      de.uniba.rz.backend.UpdateTicketResponse> getUpdateTicketMethod() {
    io.grpc.MethodDescriptor<de.uniba.rz.backend.UpdateTicketRequest, de.uniba.rz.backend.UpdateTicketResponse> getUpdateTicketMethod;
    if ((getUpdateTicketMethod = TicketManagementGrpc.getUpdateTicketMethod) == null) {
      synchronized (TicketManagementGrpc.class) {
        if ((getUpdateTicketMethod = TicketManagementGrpc.getUpdateTicketMethod) == null) {
          TicketManagementGrpc.getUpdateTicketMethod = getUpdateTicketMethod = 
              io.grpc.MethodDescriptor.<de.uniba.rz.backend.UpdateTicketRequest, de.uniba.rz.backend.UpdateTicketResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TicketManagement", "UpdateTicket"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  de.uniba.rz.backend.UpdateTicketRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  de.uniba.rz.backend.UpdateTicketResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TicketManagementMethodDescriptorSupplier("UpdateTicket"))
                  .build();
          }
        }
     }
     return getUpdateTicketMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TicketManagementStub newStub(io.grpc.Channel channel) {
    return new TicketManagementStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TicketManagementBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TicketManagementBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TicketManagementFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TicketManagementFutureStub(channel);
  }

  /**
   */
  public static abstract class TicketManagementImplBase implements io.grpc.BindableService {

    /**
     */
    public void createTicket(de.uniba.rz.backend.TicketRequest request,
        io.grpc.stub.StreamObserver<de.uniba.rz.backend.TicketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateTicketMethod(), responseObserver);
    }

    /**
     */
    public void getAllTickets(de.uniba.rz.backend.SearchFilter request,
        io.grpc.stub.StreamObserver<de.uniba.rz.backend.SearchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAllTicketsMethod(), responseObserver);
    }

    /**
     */
    public void getTicketById(de.uniba.rz.backend.GetTicketByIdRequest request,
        io.grpc.stub.StreamObserver<de.uniba.rz.backend.GetTicketByIdResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTicketByIdMethod(), responseObserver);
    }

    /**
     */
    public void updateTicket(de.uniba.rz.backend.UpdateTicketRequest request,
        io.grpc.stub.StreamObserver<de.uniba.rz.backend.UpdateTicketResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateTicketMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateTicketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                de.uniba.rz.backend.TicketRequest,
                de.uniba.rz.backend.TicketResponse>(
                  this, METHODID_CREATE_TICKET)))
          .addMethod(
            getGetAllTicketsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                de.uniba.rz.backend.SearchFilter,
                de.uniba.rz.backend.SearchResponse>(
                  this, METHODID_GET_ALL_TICKETS)))
          .addMethod(
            getGetTicketByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                de.uniba.rz.backend.GetTicketByIdRequest,
                de.uniba.rz.backend.GetTicketByIdResponse>(
                  this, METHODID_GET_TICKET_BY_ID)))
          .addMethod(
            getUpdateTicketMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                de.uniba.rz.backend.UpdateTicketRequest,
                de.uniba.rz.backend.UpdateTicketResponse>(
                  this, METHODID_UPDATE_TICKET)))
          .build();
    }
  }

  /**
   */
  public static final class TicketManagementStub extends io.grpc.stub.AbstractStub<TicketManagementStub> {
    private TicketManagementStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TicketManagementStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TicketManagementStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TicketManagementStub(channel, callOptions);
    }

    /**
     */
    public void createTicket(de.uniba.rz.backend.TicketRequest request,
        io.grpc.stub.StreamObserver<de.uniba.rz.backend.TicketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateTicketMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllTickets(de.uniba.rz.backend.SearchFilter request,
        io.grpc.stub.StreamObserver<de.uniba.rz.backend.SearchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAllTicketsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTicketById(de.uniba.rz.backend.GetTicketByIdRequest request,
        io.grpc.stub.StreamObserver<de.uniba.rz.backend.GetTicketByIdResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTicketByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateTicket(de.uniba.rz.backend.UpdateTicketRequest request,
        io.grpc.stub.StreamObserver<de.uniba.rz.backend.UpdateTicketResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateTicketMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TicketManagementBlockingStub extends io.grpc.stub.AbstractStub<TicketManagementBlockingStub> {
    private TicketManagementBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TicketManagementBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TicketManagementBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TicketManagementBlockingStub(channel, callOptions);
    }

    /**
     */
    public de.uniba.rz.backend.TicketResponse createTicket(de.uniba.rz.backend.TicketRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateTicketMethod(), getCallOptions(), request);
    }

    /**
     */
    public de.uniba.rz.backend.SearchResponse getAllTickets(de.uniba.rz.backend.SearchFilter request) {
      return blockingUnaryCall(
          getChannel(), getGetAllTicketsMethod(), getCallOptions(), request);
    }

    /**
     */
    public de.uniba.rz.backend.GetTicketByIdResponse getTicketById(de.uniba.rz.backend.GetTicketByIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetTicketByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public de.uniba.rz.backend.UpdateTicketResponse updateTicket(de.uniba.rz.backend.UpdateTicketRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpdateTicketMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TicketManagementFutureStub extends io.grpc.stub.AbstractStub<TicketManagementFutureStub> {
    private TicketManagementFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TicketManagementFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TicketManagementFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TicketManagementFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<de.uniba.rz.backend.TicketResponse> createTicket(
        de.uniba.rz.backend.TicketRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateTicketMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<de.uniba.rz.backend.SearchResponse> getAllTickets(
        de.uniba.rz.backend.SearchFilter request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAllTicketsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<de.uniba.rz.backend.GetTicketByIdResponse> getTicketById(
        de.uniba.rz.backend.GetTicketByIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTicketByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<de.uniba.rz.backend.UpdateTicketResponse> updateTicket(
        de.uniba.rz.backend.UpdateTicketRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateTicketMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_TICKET = 0;
  private static final int METHODID_GET_ALL_TICKETS = 1;
  private static final int METHODID_GET_TICKET_BY_ID = 2;
  private static final int METHODID_UPDATE_TICKET = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TicketManagementImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TicketManagementImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_TICKET:
          serviceImpl.createTicket((de.uniba.rz.backend.TicketRequest) request,
              (io.grpc.stub.StreamObserver<de.uniba.rz.backend.TicketResponse>) responseObserver);
          break;
        case METHODID_GET_ALL_TICKETS:
          serviceImpl.getAllTickets((de.uniba.rz.backend.SearchFilter) request,
              (io.grpc.stub.StreamObserver<de.uniba.rz.backend.SearchResponse>) responseObserver);
          break;
        case METHODID_GET_TICKET_BY_ID:
          serviceImpl.getTicketById((de.uniba.rz.backend.GetTicketByIdRequest) request,
              (io.grpc.stub.StreamObserver<de.uniba.rz.backend.GetTicketByIdResponse>) responseObserver);
          break;
        case METHODID_UPDATE_TICKET:
          serviceImpl.updateTicket((de.uniba.rz.backend.UpdateTicketRequest) request,
              (io.grpc.stub.StreamObserver<de.uniba.rz.backend.UpdateTicketResponse>) responseObserver);
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

  private static abstract class TicketManagementBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TicketManagementBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return de.uniba.rz.backend.TicketManagementProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TicketManagement");
    }
  }

  private static final class TicketManagementFileDescriptorSupplier
      extends TicketManagementBaseDescriptorSupplier {
    TicketManagementFileDescriptorSupplier() {}
  }

  private static final class TicketManagementMethodDescriptorSupplier
      extends TicketManagementBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TicketManagementMethodDescriptorSupplier(String methodName) {
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
      synchronized (TicketManagementGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TicketManagementFileDescriptorSupplier())
              .addMethod(getCreateTicketMethod())
              .addMethod(getGetAllTicketsMethod())
              .addMethod(getGetTicketByIdMethod())
              .addMethod(getUpdateTicketMethod())
              .build();
        }
      }
    }
    return result;
  }
}
