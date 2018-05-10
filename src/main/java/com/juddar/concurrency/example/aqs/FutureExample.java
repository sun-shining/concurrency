package com.juddar.concurrency.example.aqs;

import java.util.concurrent.*;

public class FutureExample {

    static class mycallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.err.println("start do sth in callable");
            Thread.sleep(5000);

            return "Callable is done!!!";
        }

        //Q:主线程和启动的线程应该谁先谁后？和谁有关？
        //A：和优先级有关系吧。
        public static void main(String[] args) throws InterruptedException, ExecutionException {
            ExecutorService service = Executors.newCachedThreadPool();
            Future<String> future = service.submit(new mycallable());
            Thread.sleep(1000);
            System.err.println("do sht in main!");
            System.err.println(future.get());
            service.shutdown();
        }
    }
}
