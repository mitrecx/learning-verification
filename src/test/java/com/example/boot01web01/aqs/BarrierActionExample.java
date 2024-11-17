package com.example.boot01web01.aqs;

import java.util.concurrent.CyclicBarrier;

public class BarrierActionExample {
    public static void main(String[] args) {
        // 创建一个屏障，带屏障动作
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("所有线程到达屏障点，执行屏障动作");
        });

        for (int i = 1; i <= 3; i++) {
            final int threadId = i;
            new Thread(() -> {
                try {
                    System.out.println("线程 " + threadId + " 到达屏障点");
                    barrier.await(); // 等待其他线程到达屏障点
                    System.out.println("线程 " + threadId + " 继续执行");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}