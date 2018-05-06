package com.juddar.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 *
 * synnchronized 不属于方法声明的一部分，即子类是无法通过继承获取父类同步方法的synchronized
 * 关键字的，如果子类也想用，自己需要单独声明
 */
public class SyncExample1 {
    public static void main(String[] args) {
        SyncExample1 syncExample1 = new SyncExample1();
        SyncExample1 syncExample2 = new SyncExample1();

        ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(() -> {
                syncExample1.test1(1);
            });
            executorService.execute(() -> {
                syncExample1.test1(2);
            });

            executorService.shutdown();

    }
    //修饰一个代码块
    public void test1(int j) {

        synchronized (this){

            for (int i = 0; i < 10; i++) {
                System.err.println("j" + j + "  " + i);
            }
        }
    }

    //
    public synchronized  void test2(){
        for (int i = 0; i < 10; i++) {
            System.err.println(i);
        }
    }
}
