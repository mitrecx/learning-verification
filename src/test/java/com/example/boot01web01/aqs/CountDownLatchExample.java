package com.example.boot01web01.aqs;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        // 初始化 CountDownLatch，计数器为 3
        CountDownLatch latch = new CountDownLatch(1);

        for (int i = 1; i <= 3; i++) {
            int workerId = i;
            new Thread(() -> {
                try {
                    // 工作线程完成，计数器减一
                    latch.await();
                    System.out.println("工作线程 " + workerId + " 开始执行");
                    // 模拟工作时间
                    Thread.sleep(2000);
                    System.out.println("工作线程 " + workerId + " 完成工作");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {

                }
            }).start();
        }

        // 主线程等待所有工作线程完成
        System.out.println("全部启动");
        Thread.sleep(5000);
        latch.countDown();
        System.out.println("开始执行");
    }
}