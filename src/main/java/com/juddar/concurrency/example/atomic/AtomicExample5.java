package com.juddar.concurrency.example.atomic;

import com.juddar.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLong;

/**
 * AtomicBoolean 可以保证标志只被修改一次，可适用与系统中仅需做一遍的操作，加上这个判断，不会产生并发问题
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicBoolean isHappened = new AtomicBoolean(false);
    public static int clientTotal = 5000;

    public static int threadTotal = 200;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.err.println("count : " + isHappened.get());

    }

    private static void test() {
        if (isHappened.compareAndSet(false, true)){
            System.err.println("success change");
        }
    }

}
