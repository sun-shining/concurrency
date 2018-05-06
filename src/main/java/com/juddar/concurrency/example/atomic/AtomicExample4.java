package com.juddar.concurrency.example.atomic;

import com.juddar.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 *原子性的去更新类的某个字段，该字段必须是volatile修改的非static字段
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {

    private static AtomicIntegerFieldUpdater<AtomicExample4> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample4.class,
            "count");
    public volatile int count = 100;

    public static AtomicExample4 atomicExample4 = new AtomicExample4();

    public static void main(String[] args) {
        if (updater.compareAndSet(atomicExample4, 100 , 120)) {
            System.err.println(atomicExample4.getCount());
        }

        if (updater.compareAndSet(atomicExample4, 100 , 120)) {
            System.err.println(atomicExample4.getCount());
        } else {
            System.err.println("no zhixing");
        }
    }

    public int getCount() {
        return count;
    }
}
