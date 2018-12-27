package com.juddar.concurrency.example.factory.abstractfactory;

/**
 * @Description
 * @Author dasongju
 * @Date 2018/12/27 下午11:02
 */
public class H2HandlerFactory implements AbstractHandlerFactory{

    @Override
    public UserHandler createUserHandler() throws Exception {
        return new H2User();
    }

    @Override
    public DeptHandler createDeptHandler() throws Exception {
        return new H2Dept();
    }
}
