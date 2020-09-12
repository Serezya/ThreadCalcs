package org.example;

import java.util.concurrent.RecursiveTask;

public class ArraySumTask extends RecursiveTask<Double> {
    private final long[] mas;
    private final long start;
    private final long end;

    public ArraySumTask(long[] mas, long start, long end) {
        this.mas = mas;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        double sum = 0;
        double avg;
        long length = end - start;
        for (long x = start; x < length; x++) {
            sum += mas[(int) x];
        }
        avg = sum / length;
        return avg;
    }
}
