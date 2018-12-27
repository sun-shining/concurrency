package com.juddar.concurrency.example.factory.abstratfactory;

/**
 * @Description 加法运算
 * @Author dasongju
 * @Date 2018/12/27 上午7:41
 */
public class OperatorAdd extends Operator {

    @Override
    int calculate(int a, int b) {
        return a + b;
    }
}
