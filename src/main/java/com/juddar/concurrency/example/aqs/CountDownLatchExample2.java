package com.juddar.concurrency.example.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample2 {

    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final  int threadNum = i;
//            Thread.sleep(1); 放在这儿不能起到计时的作用
            executorService.execute(()->{
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });

        }
        //时间统计的是20行test的执行时间
        //另外如果忘了使用countDown时，等待一定时间也可以继续往下执行
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        System.err.println("finished");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(10);
        System.err.println("%s"+threadNum);
    }
}
