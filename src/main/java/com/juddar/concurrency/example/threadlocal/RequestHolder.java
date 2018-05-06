package com.juddar.concurrency.example.threadlocal;

public class RequestHolder {
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(long i){
        requestHolder.set(i);
    }

    public static long getId(){
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }
}
