package com.example.boot01web01.thread;

public class StackTraceExample {
    public static void main(String[] args) {
        method1();
    }

    public static void method1() {
        method2();
    }

    public static void method2() {
        printStackTrace();
    }

    public static void printStackTrace() {
        // 获取当前线程的调用栈
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        
        // 遍历并打印每个栈帧
        for (StackTraceElement element : stackTrace) {
            System.out.println(element);
        }
    }
}