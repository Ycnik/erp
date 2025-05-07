package com.avg.grpc;

import io.grpc.stub.AbstractStub;

public final class OrderServiceGrpc {

    // Erstelle den Stub für den Client, ohne MethodDescriptor
    public static final class OrderServiceBlockingStub extends AbstractStub<OrderServiceBlockingStub> {

        // Konstruktor für den Blocking Stub
        private OrderServiceBlockingStub(io.grpc.Channel channel) {
            super(channel);
        }

        // Standardmethode für den synchronen Aufruf der newOrder Methode
        public KaufauftragReply newOrder(KaufauftragRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(
                    getChannel(), null, getCallOptions(), request);
        }

        @Override
        protected OrderServiceBlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new OrderServiceBlockingStub(channel);
        }
    }

    // Factory-Methode für die Erstellung des Blocking Stubs
    public static OrderServiceBlockingStub newBlockingStub(io.grpc.Channel channel) {
        return new OrderServiceBlockingStub(channel);
    }

}
