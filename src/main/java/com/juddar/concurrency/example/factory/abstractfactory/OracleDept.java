package com.juddar.concurrency.example.factory.abstractfactory;

/**
 * @Description
 * @Author dasongju
 * @Date 2018/12/27 上午8:40
 */
public class OracleDept extends DeptHandler {

    @Override
    public void insertDept(Department dept) {
        System.err.println("Insert Dept into oracle:" + dept.toString());

    }

    @Override
    public User getDept(int id) {
        System.err.println("Get Dept from oracle by id");
        return null;
    }
}
