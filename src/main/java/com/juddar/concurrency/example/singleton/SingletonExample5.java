package com.juddar.concurrency.example.singleton;

import com.juddar.concurrency.annoations.ThreadSafe;

/**
 * e汉式：
 * 缺点：1 初始化后未被使用造成资源的浪费； 2，初始化过程中如果在构造器中需要做很多事情，可能存在性能问题
 * 注意：静态代码块一定要放到声明的变量的后面，否则对象不能创建成功
 *
 * 因为如果先静态初始化对象后，再执行18行又给搞成null了
 */
@ThreadSafe
public class SingletonExample5 {

    //私有化构造器
    private SingletonExample5(){}

    private static SingletonExample5 singletonExample = null;

    static {
        singletonExample = new SingletonExample5();
    }

    public static SingletonExample5 getSingletonExample(){
        return singletonExample;
    }

    public static void main(String[] args) {
        System.err.println(getSingletonExample());
        System.err.println(getSingletonExample());
    }


}
