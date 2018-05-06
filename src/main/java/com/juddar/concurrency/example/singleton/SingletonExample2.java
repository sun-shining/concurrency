package com.juddar.concurrency.example.singleton;

import com.juddar.concurrency.annoations.NotRecommend;
import com.juddar.concurrency.annoations.ThreadSafe;

/**
 * 懒汉式：
 * 缺点：性能开销
 */
@ThreadSafe
@NotRecommend
public class SingletonExample2 {

    //私有化构造器
    private SingletonExample2(){}

    private static SingletonExample2 singletonExample = null;

    public synchronized static SingletonExample2 getSingletonExample(){
        if (singletonExample == null){
            singletonExample = new SingletonExample2();
        }
        return singletonExample;
    }
}
