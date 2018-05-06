package com.juddar.concurrency.example.atomic;

import com.juddar.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * LongAdder 是1.8中新增的类，适合用于竞争激烈的情况
 * 核心是将热点数据分离，将long的value分为多个cell，并发情况下操作不同的cell，将热点数据分离，和concurrentHashmap的锁分段
 * 技术详细。
 * 缺点是：统计时，如果有并发修改，统计的数据可能有偏差
 */
@Slf4j
@ThreadSafe
public class AtomicExample2 {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static LongAdder count = new LongAdder();

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

        count.increment();
    }
}
