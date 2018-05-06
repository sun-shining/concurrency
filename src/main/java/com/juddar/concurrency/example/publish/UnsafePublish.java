package com.juddar.concurrency.example.publish;

import com.juddar.concurrency.annoations.NotThreadSafe;

import java.util.Arrays;

/**
 * 不安全的对象发布，当任意线程都可以轻松访问到对象的私有变量并对其值就行操作时，即是不安全发布的对象
 */
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    public String[] get(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        System.err.println(Arrays.asList(unsafePublish.get()));

        unsafePublish.get()[0] = "d";
        System.err.println(Arrays.asList(unsafePublish.get()));

    }
}
