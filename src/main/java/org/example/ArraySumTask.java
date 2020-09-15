package org.example;

import java.util.concurrent.RecursiveTask;

public class ArraySumTask extends RecursiveTask<Integer> {
    private final long[] mas;
    private final int start;
    private final int end;

    public ArraySumTask(long[] mas, int start, int end) {
        this.mas = mas;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if ((end - start) < mas.length) {
            for (int i = start; i < end; i++) {
                sum += mas[i];
            }
        } else {
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
