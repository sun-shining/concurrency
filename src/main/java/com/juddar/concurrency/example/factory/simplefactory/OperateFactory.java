package com.juddar.concurrency.example.factory.simplefactory;

import com.juddar.concurrency.example.factory.abstratfactory.Operator;
import com.juddar.concurrency.example.factory.abstratfactory.OperatorAdd;
import com.juddar.concurrency.example.factory.abstratfactory.OperatorSub;

/**
 * @Description 简单工厂就是讲对象的部分创建逻辑封装进工厂,但是如果需要修改,可能会影响别的代码,比如加个*可能影响原来的加和减
 * 违反了开放-封闭原则,对修改开放了
 * @Author dasongju
 * @Date 2018/12/27 上午7:43
 */
public class OperateFactory {

    public Operator getOperate(String sign) {
        Operator operator = null;
        switch (sign) {
            case "+":
                operator = new OperatorAdd();
                break;
            case "-":
                operator = new OperatorSub();
                break;
            default:
                break;
        }
        return operator;
    }
}
