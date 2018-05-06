package com.juddar.concurrency;

import com.juddar.concurrency.example.threadlocal.RequestHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 每次请求过来的时候都可以对需要线程独享的对象放入ThreadLocal中
 */
public class HttpFiler implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.err.println("filter"+Thread.currentThread().getId());
        RequestHolder.add(Thread.currentThread().getId());

        chain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
