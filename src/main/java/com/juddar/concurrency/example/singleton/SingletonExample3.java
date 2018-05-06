package com.juddar.concurrency.example.singleton;

import com.juddar.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉式：--> 双重同步锁
 * 缺点：不是线程安全的！！！
 */
@NotThreadSafe
public class SingletonExample3 {

    //私有化构造器
    private SingletonExample3(){}

    private static SingletonExample3 singletonExample = null;


    //不安全的原因（指令重排）
    //1。分配对象内存空间
    //2。 初始化对象
    //3。设置instance指向刚分配的内存

    public  static SingletonExample3 getSingletonExample(){
        if (singletonExample == null){
            synchronized (SingletonExample3.class){
                if (singletonExample == null){
                    singletonExample = new SingletonExample3();
                }
            }
        }
        return singletonExample;
    }
}
