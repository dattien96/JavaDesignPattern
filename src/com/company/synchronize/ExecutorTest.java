package com.company.synchronize;

import java.util.concurrent.*;

public class ExecutorTest {
    public static void main(String[] args) {
        try {
            callableWithTimeOutTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runSingleExecutor() {
        System.out.println("Current Thread:  " + Thread.currentThread().getName());

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            System.out.println("Current Thread In Pool:  " + Thread.currentThread().getName());
            stopExecutor(executorService);
        });
    }

    /**
     * Sau tối đa 5s mà k shutdown thì sẽ chạy vào shutdownNow
     * @param executor
     */
    private static void stopExecutor(ExecutorService executor) {
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
    /**
     * Tương tự Runnable. Nhưng có value return
     * Tuy nhiên k có đồng bộ ở đây. Nên result sẽ là Future, có thể get() để lấy ra value
     *
     * DÙng fun get() sẽ block thread (tuy nhiên sẽ là thread - pool)
     * Do đó result sẽ là
     * run callable -> block 5s -> out = 123
     *
     * Nếu gọi executorService.shutdownNow();. Task sẽ chạy vào exception -> catch
     */
    private static void callableTest() throws ExecutionException, InterruptedException {
        Callable<Integer> task = () -> {
            try {
                System.out.println("run callable");
                TimeUnit.SECONDS.sleep(5);
                return 123;
            } catch (InterruptedException e) {
                return 456;
            }
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(task);
        //executorService.shutdownNow();
        int x = future.get();
        //int x = executorService.submit(task).get();
        System.out.println("out = " + x);
    }

    /**
     * Ở fun trên, khi gọi get() của future, thì thread sẽ bị block cho đến khi get đc result, vậy nếu task chạy vô hạn ?
     * Thêm timeout để xử lý case này.
     * Task chạy trong 5s mới xong, nhưng timeout chỉ đc 4s -> exception
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void callableWithTimeOutTest() throws Exception {
        Callable<Integer> task = () -> {
            try {
                System.out.println("run callable");
                TimeUnit.SECONDS.sleep(5);
                return 123;
            } catch (InterruptedException e) {
                return 456;
            }
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(task);
        //executorService.shutdownNow();
        int x = future.get(4, TimeUnit.SECONDS);
        //int x = executorService.submit(task).get();
        System.out.println("out = " + x);
    }
}
