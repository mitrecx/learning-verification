package com.example.boot01web01.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyHandler implements MethodInterceptor {
    private final Object target;

    public CglibProxyHandler(Object target) {
        this.target = target;
    }

    public static void main(String[] args) {
        // 实现了接口
        ServiceImpl target = new ServiceImpl();
        ServiceImpl proxy = (ServiceImpl) Enhancer.create(
                target.getClass(),
                new CglibProxyHandler(target)
        );
        proxy.perform();
        System.out.println("对象 proxy: " + proxy);
        System.out.println("--------------------");
        // 没有实现接口
        ServiceImpl3 target3 = new ServiceImpl3();
        ServiceImpl3 proxy3 = (ServiceImpl3) Enhancer.create(
                target3.getClass(),
                new CglibProxyHandler(target3)
        );
        proxy3.perform();
        System.out.println("对象 proxy3: " + proxy3);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before method execution");
        Object result = proxy.invoke(target, args);
        System.out.println("After method execution");
        return result;
    }
}