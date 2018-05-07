package com.juddar.concurrency.example.aqs;

import java.util.concurrent.*;

public class CyclicBarrierExample3 {
    //表示几个线程等待，然后一起执行
    //可以在初始化CyclicBarrier时附带一个Runnable接口，是一个回调函数，在需要等待的线程达到数目后优先执行Runnable
    private static CyclicBarrier barrier = new CyclicBarrier(5, ()->{
        System.err.println("callback is ready");
    });
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
                }
            });
        }
        executorService.shutdown();

    }


    private static void race(int threadNum) throws InterruptedException {
        //睡眠就是模拟每个线程处理自己的事情，需要一些时间，处理完等待其他线程，再继续执行
        Thread.sleep(1000);
        System.err.println("thread is ready" + threadNum);
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
//            e.printStackTrace();
        }
        System.err.println("thread is run...." + threadNum);
    }
}
