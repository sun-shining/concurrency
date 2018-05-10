package com.juddar.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 *
 */
@Slf4j
public class SynchronousQueueExample1 {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue sync = new SynchronousQueue();

        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            service.execute(() -> {
                try {
                    sync.put(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }

        for (int i = 0; i < 10; i++) {
            log.info("queue size:" + sync.take());
        }
        service.shutdown();
    }
}
