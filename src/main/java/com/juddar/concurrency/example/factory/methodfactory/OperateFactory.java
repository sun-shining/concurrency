package com.juddar.concurrency.example.factory.methodfactory;

/**
 * @Description 工厂方法将创建具体操作的能力抽象,如果需要拓展,只需增加相应的工厂和Operator实现类即可,符合开放封闭原则
 * 通过将实例的创建延迟到客户端,让使用的地方决定到底要使用哪个子类
 * @Author dasongju
 * @Date 2018/12/27 上午7:54
 */
public interface OperateFactory {

    Operator CreateOperate();
}
