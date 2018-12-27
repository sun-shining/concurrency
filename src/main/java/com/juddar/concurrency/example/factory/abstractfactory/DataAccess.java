package com.juddar.concurrency.example.factory.abstractfactory;

/**
 * @Description 用简单工厂替代那么多抽象工厂
 * @Author dasongju
 * @Date 2018/12/27 下午11:19
 */
public class DataAccess {

    //从数据库获取配置
    private static String dataSource;

    public static UserHandler createUser() throws Exception {
        /*UserHandler userHandler = null;
        switch (dataSource) {
            case "H2" :
                userHandler = new H2User();
                break;
            case "Mysql":
                userHandler = new MysqlUser();
                break;
            case "Oracle":
                userHandler = new OracleUser();

        }
        return userHandler;*/

        //上面的代码可以用反射替代,只改一个,这样数据库里只用传一个数据库名,使用的地方无感知使用了上面数据库,切换数据库也没有影响
        String className = "com.juddar.concurrency.example.factory.abstractfactory." + dataSource + "User";
        return (UserHandler)Class.forName(className).newInstance();
    }

    public static DeptHandler createDept() throws Exception {
        DeptHandler deptHandler = null;
        switch (dataSource) {
            case "H2" :
                deptHandler = new H2Dept();
                break;
            case "Mysql":
                deptHandler = new MysqlDept();
                break;
            case "Oracle":
                deptHandler = new OracleDept();

        }
        return deptHandler;
    }
}
