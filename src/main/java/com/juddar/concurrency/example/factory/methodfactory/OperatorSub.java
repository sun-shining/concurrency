package com.juddar.concurrency.example.factory.methodfactory;

/**
 * @Description 减法运算
 * @Author dasongju
 * @Date 2018/12/27 上午7:42
 */
public class OperatorSub extends Operator {

    @Override
    int calculate(int a, int b) {
        return a - b;
    }
}
