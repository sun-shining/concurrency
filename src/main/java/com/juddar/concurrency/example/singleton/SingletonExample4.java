package com.juddar.concurrency.example.singleton;

import com.juddar.concurrency.annoations.ThreadSafe;

/**
 * 懒汉式：--> 双重同步锁
 *
 */
@ThreadSafe
public class SingletonExample4 {

    //私有化构造器
    private SingletonExample4(){}

    private volatile static SingletonExample4 singletonExample = null;


    //不安全的原因（指令重排）
    //1。分配对象内存空间
    //2。 初始化对象
    //3。设置instance指向刚分配的内存

    public  static SingletonExample4 getSingletonExample(){
        if (singletonExample == null){//双重检测机制
            synchronized (SingletonExample4.class){
                if (singletonExample == null){
                    singletonExample = new SingletonExample4();
                }
            }
        }
        return singletonExample;
    }
}
