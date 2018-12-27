package com.juddar.concurrency.example.factory.abstractfactory;

/**
 * @Description
 * @Author dasongju
 * @Date 2018/12/27 上午8:40
 */
public class H2Dept extends DeptHandler {

    @Override
    public void insertDept(Department dept) {
        System.err.println("Insert Dept into H2:" + dept.toString());
    }

    @Override
    public User getDept(int id) {
        System.err.println("Get Dept from H2 by id");
        return null;
    }
}
