package com.company.synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SynchronizeTest {
    public static void main(String[] args) {
        count = 0;
        runManyThread();
    }

    static int count = 0;

    synchronized static void increment() {
        count = count + 1;
    }

    public static void runManyThread() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(SynchronizeTest::increment));

        ConcurrentUtils.stop(executor);

        System.out.println(count);  // 9965
    }
}
