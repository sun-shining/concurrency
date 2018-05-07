package com.juddar.concurrency.example.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample3 {

    private static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadCount; i++) {
            final  int threadNum = i;
            executorService.execute(()->{

                try {
                    //尝试获取许可的方法，如果获取不到，立刻返回false
                    //还有重写的方法，可以加时间和单位，让它等待一定时间获取许可
                    if (semaphore.tryAcquire()) {
                        test(threadNum);
                        semaphore.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

        }
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        System.err.println("%s"+threadNum);
        Thread.sleep(1001);
    }
}
