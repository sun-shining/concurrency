package com.juddar.concurrency.example.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 ①基本性质：读锁是一个共享锁，写锁是一个独占锁。读锁能同时被多个线程获取，写锁只能被一个线程获取。读锁和写锁不能同时存在。

 ①重入性：一个线程可以多次重复获取读锁和写锁。

 ③锁降级：一个线程在已经获取写锁的情况下，可以再次获取读锁，如果线程又释放了写锁，就完成了一次锁降级。

 ④锁升级：ReentrantReadWriteLock不支持锁升级。一个线程在获取读锁的情况下，如果试图去获取写锁，将会导致死锁（后面会详细说明）。

 ⑤获取锁中断：提供了可中断的lock方法。

 ⑥重入数：读锁和写锁的重入上限为65535（所有线程获取的锁的总数，为什么是这个值后面会详细说明）。

 ⑦公平性：ReentrantReadWriteLock提供了公平&非公平两种工作模式。
 */
public class ReentrantReadWriteLockExample2 {

    Object data;
    volatile boolean cacheValid;
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    void processCachedData() {
        rwl.readLock().lock();
        if (!cacheValid) {
            // Must release read lock before acquiring write lock
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            // Recheck state because another thread might have acquired
            //   write lock and changed state before we did.
            if (!cacheValid) {
//                data = ... 有效伪代码，代表缓存数据
                cacheValid = true;
            }
            // Downgrade by acquiring read lock before releasing write lock
            //锁降级，写锁释放变读锁，释放独锁的动作在获取读锁之后
            rwl.readLock().lock();
            rwl.writeLock().unlock(); // Unlock write, still hold read
        }
//        use(data);    使用数据。伪代码，为了不报错给注释了，是有用的
        rwl.readLock().unlock();
    }

}
