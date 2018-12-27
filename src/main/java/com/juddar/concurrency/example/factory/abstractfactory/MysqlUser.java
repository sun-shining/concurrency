package com.juddar.concurrency.example.factory.abstractfactory;

/**
 * @Description
 * @Author dasongju
 * @Date 2018/12/27 上午8:40
 */
public class MysqlUser extends UserHandler {

    public void insertUser(User user) {
        System.err.println("Insert User into mysql:" + user.toString());
    }

    public User getUser(int id) {
        System.err.println("Get User from mysql by id");
        return null;
    }


}
