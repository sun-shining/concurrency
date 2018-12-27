package com.juddar.concurrency.example.factory.abstractfactory;

/**
 * @Description 部门处理器
 * @Author dasongju
 * @Date 2018/12/27 上午10:16
 */
public abstract class DeptHandler {

    public abstract void insertDept(Department dept);

    public abstract User getDept(int id);

}
