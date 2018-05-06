package com.juddar.concurrency.example.commonunsafe;

import com.juddar.concurrency.annoations.ThreadSafe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 利用堆栈封闭，将线程不安全的类封闭起来，就可以安全使用了
 */
@ThreadSafe
public class DateFormatExample2 {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update3();
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

    private static  void update3() {
        SimpleDateFormat smt = new SimpleDateFormat("yyyyMMdd");
        try {
            smt.parse("20180508");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
