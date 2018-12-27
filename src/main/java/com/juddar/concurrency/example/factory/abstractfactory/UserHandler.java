package com.juddar.concurrency.example.factory.abstractfactory;

/**
 * @Description 用户处理器
 * @Author dasongju
 * @Date 2018/12/27 上午10:16
 */
public abstract class UserHandler {

    public abstract void insertUser(User user);

    public abstract User getUser(int id);

}
