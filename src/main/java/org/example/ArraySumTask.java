package org.example;

import java.util.concurrent.RecursiveTask;

public class ArraySumTask extends RecursiveTask<Double> {
    private final long[] mas;
    private final int start;
    private final int end;

    public ArraySumTask(long[] mas, int start, int end) {
        this.mas = mas;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        double sum = 0;
        double avg;
        long length = end - start;
        for (int x = start; x < length; x++) {
            sum += mas[x];
        }
        avg = sum / length;
        return avg;
    }
}
