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
        long length;
        if (start == mas.length / 2) {
            length = mas.length;
        } else {
            length = end - start;
        }
        for (int x = start; x < length; x++) {
            sum += mas[x];
        }
        return sum;
    }
}
