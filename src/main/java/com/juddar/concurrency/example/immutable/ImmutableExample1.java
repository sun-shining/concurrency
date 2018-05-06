package com.juddar.concurrency.example.immutable;

import com.juddar.concurrency.annoations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * final 可以通过关键字，Collections集合，还有Guava包里的ImmutableList ImmutableSet ImmutableMap 三种方法生成
 */
@ThreadSafe
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "2";
    private  static Map map = new HashMap();

    static {
        map.put(1, 2);
        map.put(2, 3);
    }

    public static void main(String[] args) {
        map = Collections.unmodifiableMap(ImmutableExample1.map);
        map.put(1,3);
        System.err.println(map.get(1));
    }
}
