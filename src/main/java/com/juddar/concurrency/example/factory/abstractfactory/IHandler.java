package com.juddar.concurrency.example.factory.abstractfactory;

/**
 * @Description
 * @Author dasongju
 * @Date 2018/12/27 下午12:56
 */
public interface IHandler<T> {

    public void insert(T t);

    public T get(int id);
}
