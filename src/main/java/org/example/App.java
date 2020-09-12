package org.example;

import static java.util.concurrent.ForkJoinTask.invokeAll;

public class App {
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        double avg = 0;
        long[] array = generateMas();

        // ОДНИМ ПОТОКОМ
        for (double value : array) {
            avg += value;
        }
        System.out.println("Среднее арифметическое однопоточно: " + avg / array.length);
        long end = System.currentTimeMillis();
        System.out.println("Время выполнения однопоточно:" + (end - begin) + "мс");

        // ForkJoinPool
        begin = System.currentTimeMillis();
        final int middle = (array.length) / 2;
        ArraySumTask task1 = new ArraySumTask(array, 0, middle);
        ArraySumTask task2 = new ArraySumTask(array, middle, array.length);
        invokeAll(task1, task2);

        System.out.println("Среднее арифметическое многопоточно: " + (task1.join() + task2.join()));
        end = System.currentTimeMillis();
        System.out.println("Время выполнения многопоточно:" + (end - begin) + "мс");

    }

    public static long[] generateMas() {
        long[] array = new long[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.round((Math.random() * 100) - 0);
        }
        return array;
    }
}
