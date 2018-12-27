package com.juddar.concurrency.example.factory.abstractfactory;

/**
 * @Description
 * @Author dasongju
 * @Date 2018/12/27 上午11:44
 */
public interface AbstractHandlerFactory {

    UserHandler createUserHandler() throws Exception;

    DeptHandler createDeptHandler() throws Exception;
}
