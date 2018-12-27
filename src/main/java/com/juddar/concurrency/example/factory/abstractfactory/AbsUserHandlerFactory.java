package com.juddar.concurrency.example.factory.abstractfactory;

/**
 * @Description  工厂方法面对的是一个产品接口,而抽象工厂面对的是多个产品
 *  这种写法写不下去的原因就是角度不对,应该是一个产品线一个AbstractHandlerFactory的实现类
 * @Author dasongju
 * @Date 2018/12/27 上午10:22
 */
public class AbsUserHandlerFactory implements AbstractHandlerFactory {


    @Override
    public UserHandler createUserHandler() throws Exception{
        return null;

    }

    @Override
    public DeptHandler createDeptHandler()
        throws Exception {
        return new DeptHandlerFactory().getDeptHandler(null, null);
    }
}
