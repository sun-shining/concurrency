package com.juddar.concurrency.example.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁不论写和读都锁住，故有了ReentrantReadWriteLock,只锁写，读可共享锁
 */
public class ReentrantLockExample1 {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static int count = 0;
    //默认是不公平锁，可以通过在构造器里添加true和false来决定锁的类型
    public static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < clientTotal; i++) {
            service.execute(() -> {
                add();
            });
        }
        service.shutdown();
        System.err.println("count : " + count);

    }

    public static void add() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

}
