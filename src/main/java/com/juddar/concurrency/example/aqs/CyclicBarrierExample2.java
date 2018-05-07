package com.juddar.concurrency.example.aqs;

import java.util.concurrent.*;

public class CyclicBarrierExample2 {
    //表示几个线程等待，然后一起执行
    private static CyclicBarrier barrier = new CyclicBarrier(5);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                    race(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();

    }

    //TIPS 如果发生异常，一定要处理异常，以保证后续代码可以继续正常就行处理
    private static void race(int threadNum) throws InterruptedException, BrokenBarrierException, TimeoutException {
        //睡眠就是模拟每个线程处理自己的事情，需要一些时间，处理完等待其他线程，再继续执行
        Thread.sleep(1000);
        System.err.println("thread is ready" + threadNum);
        barrier.await(2000, TimeUnit.MILLISECONDS);
        System.err.println("thread is run...." );
    }
}
