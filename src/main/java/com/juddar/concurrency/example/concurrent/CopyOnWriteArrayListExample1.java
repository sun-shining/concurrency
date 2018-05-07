package com.juddar.concurrency.example.concurrent;

import com.juddar.concurrency.annoations.ThreadSafe;

import java.util.concurrent.*;

@ThreadSafe
public class CopyOnWriteArrayListExample1 {


    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    private static CopyOnWriteArrayList<Integer> count = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            service.execute(() -> {
                try {
                    semaphore.acquire();//获取令牌
                    add2();
                    semaphore.release();//释放令牌
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        service.shutdown();
        System.err.println("count : " + count.size());

    }

    public static void add2() {

        count.add(1);
    }
}
