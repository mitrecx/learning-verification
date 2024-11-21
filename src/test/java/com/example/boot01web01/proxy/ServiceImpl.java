package com.example.boot01web01.proxy;

public class ServiceImpl implements Service {
    @Override
    public void perform() {
        System.out.println("Executing ServiceImpl.perform()");
    }
}
