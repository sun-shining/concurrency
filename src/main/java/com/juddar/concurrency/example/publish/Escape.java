package com.juddar.concurrency.example.publish;

import com.juddar.concurrency.annoations.NotRecommend;
import com.juddar.concurrency.annoations.NotThreadSafe;

/**
 * this逃逸:在外部对象Escape还没有创建完成时，该对象的引用this已经对外可见了
 */
@NotThreadSafe
@NotRecommend
public class Escape {

    private int initValue = 0 ;

    public Escape(){
        new InnerClass();
    }

    private class InnerClass{
        public InnerClass(){
            System.err.println(Escape.this.initValue);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
