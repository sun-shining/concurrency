package com.juddar.concurrency.example.factory.abstractfactory;

import org.junit.Test;

/**
 * @Description 主演化逻辑,再看的时候从这儿开始看,看看为什么需要某种设计模式,而不是硬往上靠,能解决问题才是王道
 * @Author dasongju
 * @Date 2018/12/27 上午8:43
 */
public class FactoryTest {

    @Test
    public void test1() {
        //1 现在要换数据库成oracle怎么办？
        //1.1 加oracleUser类
        //1.2 重新编写客户端代码
        MysqlUser mysqlUser = new MysqlUser();
        User user = new User(1, "juddar");
        mysqlUser.insertUser(user);
        mysqlUser.getUser(1);
    }

    @Test
    public void test2() {
        //2 能否用一个工厂,来屏蔽 new MysqlUser 或者 new OracleUser 带来的影响
        OracleUser mysqlUser = new OracleUser();
        User user = new User(1, "oracle");
        mysqlUser.insertUser(user);
        mysqlUser.getUser(1);
    }


    @Test
    public void test3() throws Exception{
        //3 这个的问题是如果需要新增一种访问方式,新增handler的同时,还需要修改UserHandlerFactory里的switch分支
        //3.1 switch 分支可以通过反射解决
        UserHandlerFactory userHandlerFactory = new UserHandlerFactory();
        UserHandler userHandler = userHandlerFactory.getUserHandler("oracle", null);
        userHandler.insertUser(new User(2,"amanda"));
        userHandler.getUser(2);
    }

    @Test
    public void test4() throws Exception{
        //4 通过反射解决了 3.1的需要修改工厂里switch分支的问题;如果再增加个对部门的操作,需要加1个接口,3个实现,外加一个HandlerFactory
        UserHandlerFactory userHandlerFactory = new UserHandlerFactory();
//        UserHandler userHandler = userHandlerFactory.getUserHandler("com.juddar.concurrency.example.factory.abstractfactory.H2User", null);
        UserHandler userHandler = userHandlerFactory.getUserHandler(null, H2User.class);
        userHandler.insertUser(new User(3, "jack"));
        userHandler.getUser(3);
    }

    @Test
    public void test5() throws Exception{
        //4 加一套代码,能解决4中新增对部门操作的问题,但是这也太费劲了
        DeptHandlerFactory deptHandlerFactory = new DeptHandlerFactory();
        DeptHandler deptHandler = deptHandlerFactory.getDeptHandler(null, H2User.class);
        deptHandler.insertDept(new Department(4,"tom"));
        deptHandler.getDept(4);
    }
}
