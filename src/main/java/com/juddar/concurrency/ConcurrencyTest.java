package com.juddar.concurrency;

import com.juddar.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
@NotThreadSafe
public class ConcurrencyTest {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static  int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
//                    log.error("exception", e);
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.err.println("count : " + count);

    }

    public static synchronized void add(){
        count ++;
    }
}
