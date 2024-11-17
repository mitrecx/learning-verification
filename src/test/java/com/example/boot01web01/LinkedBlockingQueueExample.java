package com.example.boot01web01;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {
    private static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

    // 生产者线程
    static class Producer implements Runnable {
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(i); // 若队列满，则阻塞
                    System.out.println("生产者生产了: " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // 消费者线程
    static class Consumer implements Runnable {
        public void run() {
            try {
                while (true) {
                    Thread.sleep(5000);
                    Integer item = queue.take(); // 若队列为空，则阻塞
                    System.out.println("消费者消费了: " + item);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());

        producerThread.start();
        consumerThread.start();
    }
}