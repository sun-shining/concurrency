package com.juddar.concurrency.example.threadlocal;

import sun.jvm.hotspot.debugger.ThreadAccess;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 代码中有如下的写法
 * 是为了让多线程过来处理时，每个线程getValue方法拿到的是不同的值，并发处理不同的行
 * Q：这样程序一直在跑，到底有多少个threadLocal对象？
 * A：只有一个，每个ThreadLocal对象的hashCode都一样
 */
public class ThreadLocalExample {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            int andAdd = atomicInteger.getAndAdd(1);
            System.err.println("static-->"+andAdd);
            return andAdd;
        }
    };



    public static int getValue(){
        return threadLocal.get();
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {

            service.execute(()-> {
                System.err.println("每个线程中的threadLocal的hash码："+threadLocal.hashCode());
                System.err.println("Thread Name is : " + Thread.currentThread().getName()+ "-->"+getValue());
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            });
        }

//            service.shutdown();


    }
}
