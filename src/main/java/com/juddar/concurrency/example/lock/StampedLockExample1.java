package com.juddar.concurrency.example.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

/**
 该类是一个读写锁的改进，它的思想是读写锁中读不仅不阻塞读，同时也不应该阻塞写。

 读不阻塞写的实现思路：

 在读的时候如果发生了写，则应当重读而不是在读的时候直接阻塞写！
 因为在读线程非常多而写线程比较少的情况下，写线程可能发生饥饿现象，也就是因为大量的读线程存在并且读线程都阻塞写线程，

 因此写线程可能几乎很少被调度成功！当读执行的时候另一个线程执行了写，则读线程发现数据不一致则执行重读即可。所以读写都存在的情况下，

 使用StampedLock就可以实现一种无障碍操作，即读写之间不会阻塞对方，但是写和写之间还是阻塞的！
 */
public class StampedLockExample1 {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static int count = 0;
    //区别锁writeLock有返回值
    public static StampedLock lock = new StampedLock();

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
        long l = lock.writeLock();
        try {
            count++;
        } finally {
            lock.unlock(l);
        }
    }

    public int read(){
        //尝试乐观读
        long stamp = lock.tryOptimisticRead();
        //获取值
        int temp = count;

        //验证票据是否发生变化，如果变化，获取读锁，再读。读锁和写锁锁互斥的，所以这一步会阻塞写锁
        if (!lock.validate(stamp)) {
            long l = lock.readLock();
            temp = count;
            lock.unlockRead(l);
        }
        return temp;
    }
}
