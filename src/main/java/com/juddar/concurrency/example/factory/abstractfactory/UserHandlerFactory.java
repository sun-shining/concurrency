package com.juddar.concurrency.example.factory.abstractfactory;

/**
 * @Description  工厂方法面对的是一个产品接口,而抽象工厂面对的是多个产品
 * 什么意思呢，这个工厂只生产和用户相关的所有数据库访问
 * 此处是工厂方法
 * @Author dasongju
 * @Date 2018/12/27 上午10:22
 */
public class UserHandlerFactory {

    public UserHandler getUserHandler(String dataSource, Class clazz)
        throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        /*switch (dataSource) { 分支可以通过反射解决,以后就不用动了
            case "mysql":
                return new MysqlUser();
            case "oracle":
                return new OracleUser();
            case "H2":
                return new H2User();
            default:
                return null;
        }*/
        if (clazz == null) {
            return (UserHandler)Class.forName(dataSource).newInstance();
        }

        if (dataSource == null) {
            return (UserHandler)clazz.newInstance();
        }

        return null;
    }
}
