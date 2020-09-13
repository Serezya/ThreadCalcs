package org.example;

import static java.util.concurrent.ForkJoinTask.invokeAll;

public class App {
    public static void main(String[] args) {
        long[] array = generateMas();

        long begin = System.currentTimeMillis();
        double avg = 0;

        // ОДНИМ ПОТОКОМ
        for (double value : array) {
            avg += value;
        }
        avg = avg / array.length;
        System.out.println("Среднее арифметическое однопоточно: " + avg);
        long end = System.currentTimeMillis();
        System.out.println("Время выполнения однопоточно:" + (end - begin) + "мс");

        // ForkJoinPool
        begin = System.currentTimeMillis();
        double sum = sumArray(array);
        end = System.currentTimeMillis();

        System.out.println("Среднее арифметическое многопоточно: " + sum/array.length);
        System.out.println("Время выполнения многопоточно:" + (end - begin) + "мс");

    }

    private static int sumArray(long[] array) {
        final int middle = (array.length) / 2;
        ArraySumTask task1 = new ArraySumTask(array, 0, middle);
        ArraySumTask task2 = new ArraySumTask(array, middle, array.length);

        invokeAll(task1,task2);
        return task1.join()+task2.join();
    }

    public static long[] generateMas() {
        long[] array = new long[10000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.round((Math.random() * 100) - 0);
        }
        return array;
    }
}
