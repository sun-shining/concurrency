package com.juddar.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 阻塞队列的的核心应该是当队列中没有元素时和队列满了时，均需要阻塞新来的请求
 */
@Slf4j
public class BlockingQueueExample1 {
    private List queue = new LinkedList<>();
    private int limit = 10;

    public BlockingQueueExample1(int limit) {
        this.limit = limit;
    }

    public synchronized int size(){
        return this.queue.size();
    }

    public synchronized void enterQueue(Object item) throws InterruptedException {

        //1 当队列已满，新对象加入，阻塞住
        while (queue.size() == limit) {
            wait();
        }

        //保障当队列中没有元素时谁都可以往里添加
        if (queue.size() == 0) {
            notifyAll();
        }

        this.queue.add(item);
    }

    public synchronized Object deQueue() throws InterruptedException {

        while (queue.size() == 0) {
            wait();
        }

        //Q:为什么等到limit才唤醒？
        //A：保障当队列满着，来了线程都可以往线程外移除
        if (queue.size() == limit) {
            notifyAll();
        }

        return this.queue.remove(0);
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        BlockingQueueExample1 queue = new BlockingQueueExample1(10);
        for (int i = 0; i < 13; i++) {
            final int threadNum = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.enterQueue(threadNum);
                        log.info("add item to Queue, the queue size: {}" + queue.size());
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
            });

            service.execute(()->{
                try {
                    queue.deQueue();
                    log.info("remove item from Queue, the queue size: {}" + queue.size());
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            });
        }

        service.shutdown();
    }

}
