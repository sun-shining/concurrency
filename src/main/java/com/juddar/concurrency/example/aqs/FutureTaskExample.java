package com.juddar.concurrency.example.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {

    private static FutureTask<String> task = new FutureTask<String>(()->{
        System.err.println("start do sth in callable");
        Thread.sleep(5000);

        return "Callable is done!!!";
    });

    //两种方式都可以，
    private static FutureTask<String> task2 = new FutureTask<>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            return null;
        }
    });

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new Thread(task).start();
        System.err.println("do sth in main!!");
        Thread.sleep(1000);
        System.err.println(task
                .get());
    }
}
