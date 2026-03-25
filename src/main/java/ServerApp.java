package org.sgems.salary;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class ServerApp {

    public static void main(String[] args) throws Exception {

        Server server = ServerBuilder
                .forPort(50051)
                .addService(new SalaryMonitoringServiceImpl())
                .build();

        server.start();
        System.out.println("Server running on port 50051");

        server.awaitTermination();
    }
}