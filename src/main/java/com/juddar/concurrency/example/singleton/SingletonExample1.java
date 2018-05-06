package com.juddar.concurrency.example.singleton;

import com.juddar.concurrency.annoations.NotThreadSafe;
import com.juddar.concurrency.annoations.ThreadSafe;

/**
 * e汉式：
 * 缺点：1 初始化后未被使用造成资源的浪费； 2，初始化过程中如果在构造器中需要做很多事情，可能存在性能问题
 */
@ThreadSafe
public class SingletonExample1 {

    //私有化构造器
    private SingletonExample1(){}

    private static SingletonExample1 singletonExample = new SingletonExample1();

    public static SingletonExample1 getSingletonExample(){
        return singletonExample;
    }
}
