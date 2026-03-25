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
}