package com.juddar.concurrency.example.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample2 {

    private static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadCount; i++) {
            final  int threadNum = i;
            executorService.execute(()->{

                try {
                    semaphore.acquire(3);//获取多个许可，那和
                    test(threadNum);
                    semaphore.release(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

        }
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        System.err.println("%s"+threadNum);
        Thread.sleep(100);
    }
}
