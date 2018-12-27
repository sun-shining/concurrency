package com.juddar.concurrency.example.factory.abstractfactory;

/**
 * @Description 工厂方法面对的是一个产品接口,而抽象工厂面对的是多个产品
 * 什么意思呢，这个工厂只生产和用户相关的所有数据库访问
 * @Author dasongju
 * @Date 2018/12/27 上午10:22
 */
public class DeptHandlerFactory {

    public DeptHandler getDeptHandler(String dataSource, Class clazz)
        throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (clazz == null) {
            return (DeptHandler)Class.forName(dataSource).newInstance();
        }

        if (dataSource == null) {
            return (DeptHandler)clazz.newInstance();
        }

        return null;
    }
}
