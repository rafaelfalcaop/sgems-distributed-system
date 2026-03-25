package org.sgems.salary;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClientApp {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        SalaryMonitoringServiceGrpc.SalaryMonitoringServiceBlockingStub stub =
                SalaryMonitoringServiceGrpc.newBlockingStub(channel);

        PayGapRequest request = PayGapRequest.newBuilder()
                .setDepartmentId("IT")
                .setYear(2025)
                .build();

        PayGapResponse response = stub.calculatePayGap(request);

        System.out.println("Male Avg: " + response.getAverageMaleSalary());
        System.out.println("Female Avg: " + response.getAverageFemaleSalary());
        System.out.println("Gap: " + response.getPayGapPercentage());

        channel.shutdown();
    }
}