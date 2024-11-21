package com.example.boot01web01.proxy;

// 动态代理类

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyHandler implements InvocationHandler {
    private final Object target;

    public JdkProxyHandler(Object target) {
        this.target = target;
    }

    public static void main(String[] args) {
        Service target = new ServiceImpl();
        Service proxy = (Service) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new JdkProxyHandler(target)
        );
        proxy.perform();

        System.out.println("--------------------");
        Service target2 = new ServiceImpl2();
        Service proxy2 = (Service) Proxy.newProxyInstance(
                target2.getClass().getClassLoader(),
                target2.getClass().getInterfaces(),
                new JdkProxyHandler(target2)
        );
        proxy2.perform();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before method execution");
        Object result = method.invoke(target, args);
        System.out.println("After method execution");
        return result;
    }
}