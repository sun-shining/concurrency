package com.juddar.concurrency.example.lock;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 ①基本性质：读锁是一个共享锁，写锁是一个独占锁。读锁能同时被多个线程获取，写锁只能被一个线程获取。读锁和写锁不能同时存在。

 ①重入性：一个线程可以多次重复获取读锁和写锁。

 ③锁降级：一个线程在已经获取写锁的情况下，可以再次获取读锁，如果线程又释放了写锁，就完成了一次锁降级。

 ④锁升级：ReentrantReadWriteLock不支持锁升级。一个线程在获取读锁的情况下，如果试图去获取写锁，将会导致死锁（后面会详细说明）。

 ⑤获取锁中断：提供了可中断的lock方法。

 ⑥重入数：读锁和写锁的重入上限为65535（所有线程获取的锁的总数，为什么是这个值后面会详细说明）。

 ⑦公平性：ReentrantReadWriteLock提供了公平&非公平两种工作模式。

 当读多写少时，容易发生写饥饿，即一直有读线程阻塞写线程
 */
public class ReentrantReadWriteLockExample1 {

    public final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();

    //treeMap当数据操作较大或者read操作明显多于write操作时，由于readLock的不阻塞性质使得ReentrantReadWriteLock效率明显高于synchronized
    public static Map<String, data> map = new TreeMap<>();

    class data {

    }

    public data getByKey(String key) {
        try {

            readLock.lock();
            return map.get(key);
        } finally {

            readLock.unlock();
        }
    }

    public Set<String> getKeys() {
        try {

            readLock.lock();
            return map.keySet();
        } finally {

            readLock.unlock();
        }
    }

    public void put(String str, data data) {
        writeLock.lock();
        try {
            map.put(str, data);
        }finally {
            writeLock.unlock();
        }
    }

}
