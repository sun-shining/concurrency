package com.juddar.concurrency.example.factory.abstratfactory;

/**
 * @Description
 * @Author dasongju
 * @Date 2018/12/27 上午7:56
 */
public class OperateAddFactory implements OperateFactory{

    @Override
    public Operator CreateOperate() {
        return new OperatorAdd();
    }
}
