package org.example;

import java.util.concurrent.RecursiveTask;

public class ArraySumTask extends RecursiveTask<Long> {
    private final long[] mas;
    private final int start;
    private final int end;

    public ArraySumTask(long[] mas, int start, int end) {
        this.mas = mas;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        switch (end-start) {
            case 0: return 0L;
            case 1: return mas[start];
            case 2: return mas[start] + mas[start+1];
            default:

            int middle = (start + end) / 2;
            ArraySumTask task1 = new ArraySumTask(mas, start, middle);
            ArraySumTask task2 = new ArraySumTask(mas, middle, end);

            task1.fork();
            task2.fork();
            sum += task1.join() + task2.join();
        }
        return sum;
    }
}
