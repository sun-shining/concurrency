package com.juddar.concurrency.example.singleton;

import com.juddar.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉式：
 */
@NotThreadSafe
public class SingletonExample {

    //私有化构造器
    private SingletonExample(){}

    private static SingletonExample singletonExample = null;

    public static SingletonExample getSingletonExample(){
        if (singletonExample == null){
            singletonExample = new SingletonExample();
        }
        return singletonExample;
    }
}
