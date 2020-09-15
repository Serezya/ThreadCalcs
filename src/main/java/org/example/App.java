package org.example;

import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {
        long[] array = generateMas();

        long begin = System.currentTimeMillis();
        double sum = 0;

        // ОДНИМ ПОТОКОМ
        for (double value : array) {
            sum += value;
        }

        long end = System.currentTimeMillis();
        System.out.println("Среднее арифметическое однопоточно: " + sum / array.length);
        System.out.println("Время выполнения однопоточно:" + (end - begin) + "мс");

        // ForkJoinPool
        begin = System.currentTimeMillis();
        sum = sumArray(array);
        end = System.currentTimeMillis();

        System.out.println("Среднее арифметическое многопоточно: " + sum / array.length);
        System.out.println("Время выполнения многопоточно:" + (end - begin) + "мс");

    }

    private static long sumArray(long[] array) {
        ForkJoinPool fjp = new ForkJoinPool();
        ArraySumTask task = new ArraySumTask(array, 0, array.length);
        return fjp.invoke(task);
    }

    public static long[] generateMas() {
        long[] array = new long[10000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.round((Math.random() * 100) - 0);
        }
        return array;
    }
}
