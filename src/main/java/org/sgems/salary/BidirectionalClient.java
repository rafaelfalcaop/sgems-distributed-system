package org.sgems.salary;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class BidirectionalClient {

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        SalaryMonitoringServiceGrpc.SalaryMonitoringServiceStub stub =
                SalaryMonitoringServiceGrpc.newStub(channel);

        StreamObserver<ChatMessage> requestObserver =
                stub.liveSalaryChat(new StreamObserver<ChatMessage>() {

                    @Override
                    public void onNext(ChatMessage value) {
                        System.out.println("Server: " + value.getMessage());
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("Error: " + t.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Chat ended");
                    }
                });

        // enviando mensagens
        requestObserver.onNext(ChatMessage.newBuilder().setMessage("Hello").build());
        requestObserver.onNext(ChatMessage.newBuilder().setMessage("How are you?").build());

        requestObserver.onCompleted();

        Thread.sleep(1000);

        channel.shutdown();
    }
}