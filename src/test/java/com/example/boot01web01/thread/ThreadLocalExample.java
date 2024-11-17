package com.example.boot01web01.thread;

public class ThreadLocalExample {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {

        Runnable task = () -> {
            int value = threadLocal.get();
            String name = Thread.currentThread().getName();
            System.out.println(name + " initial value: " + value);
            threadLocal.set(value + 1);
            System.out.println(name + " updated value: " + threadLocal.get());
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
    }
}