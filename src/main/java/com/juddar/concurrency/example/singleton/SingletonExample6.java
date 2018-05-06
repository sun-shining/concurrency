package com.juddar.concurrency.example.singleton;

import com.juddar.concurrency.annoations.NotThreadSafe;
import com.juddar.concurrency.annoations.Recommend;
import com.juddar.concurrency.annoations.ThreadSafe;

/**
 * 枚举
 */
@ThreadSafe
@Recommend
public class SingletonExample6 {

    //私有化构造器
    private SingletonExample6(){}

    private static SingletonExample6 singletonExample = null;

    public static SingletonExample6 getSingletonExample(){

        return Singleton.INSTANCE.getInstance();
    }

    private enum  Singleton{
        INSTANCE;
        private SingletonExample6 singletonExample6;

        //JVM来保证该方法只被调用一次
        Singleton(){
            singletonExample6 = new SingletonExample6();
        }

        public SingletonExample6 getInstance(){
            return singletonExample6;
        }
    }
}
