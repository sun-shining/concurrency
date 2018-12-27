package com.juddar.concurrency.example.factory.methodfactory;

/**
 * @Description
 * @Author dasongju
 * @Date 2018/12/27 上午7:57
 */
public class OperateSubFactory  implements OperateFactory{

    @Override
    public Operator CreateOperate() {
        return new OperatorSub();
    }
}
