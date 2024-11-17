package com.example.boot01web01.aqs;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        // 创建一个信号量，允许最多 3 个线程同时访问资源
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    String tn = Thread.currentThread().getName();
                    System.out.println(tn + " 请求访问资源");
                    semaphore.acquire(); // 获取信号量
                    System.out.println(tn + " 正在访问资源");
                    Thread.sleep(2000); // 模拟资源使用
                    System.out.println(tn + " 释放资源");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    semaphore.release(); // 释放信号量
                }
            }, "线程" + i).start();
        }
    }
}