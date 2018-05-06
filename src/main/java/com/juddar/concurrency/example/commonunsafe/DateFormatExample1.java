package com.juddar.concurrency.example.commonunsafe;

import com.juddar.concurrency.annoations.NotThreadSafe;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
@NotThreadSafe
public class DateFormatExample1 {

    //不是线程安全的
    private static SimpleDateFormat smt = new SimpleDateFormat("yyyyMMdd");

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update2();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();

    }

    public static  void update2() {

        smt.format("20180203");
    }
}
