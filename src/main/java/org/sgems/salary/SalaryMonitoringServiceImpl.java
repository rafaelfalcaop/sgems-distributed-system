package org.sgems.salary;

import io.grpc.stub.StreamObserver;

public class SalaryMonitoringServiceImpl extends SalaryMonitoringServiceGrpc.SalaryMonitoringServiceImplBase {

    @Override
    public void calculatePayGap(PayGapRequest request, StreamObserver<PayGapResponse> responseObserver) {

        String department = request.getDepartmentId();
        int year = request.getYear();

        double maleAvg = 50000;
        double femaleAvg = 45000;
        double gap = ((maleAvg - femaleAvg) / maleAvg) * 100;

        PayGapResponse response = PayGapResponse.newBuilder()
                .setAverageMaleSalary(maleAvg)
                .setAverageFemaleSalary(femaleAvg)
                .setPayGapPercentage(gap)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    } 
    
    @Override
public void streamSalaryStatistics(SalaryStatsRequest request,
        StreamObserver<SalaryStatsResponse> responseObserver) {

    String[] months = {"Jan", "Feb", "Mar"};

    for (String month : months) {
        SalaryStatsResponse response = SalaryStatsResponse.newBuilder()
                .setMonth(month)
                .setAverageSalary(4000 + Math.random() * 1000)
                .setGender("Mixed")
                .build();

        responseObserver.onNext(response);
    }

    responseObserver.onCompleted();
}
}