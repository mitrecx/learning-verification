package com.example.boot01web01;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueTest {

    public static void main(String[] args) {
        // 初始化一个容量为 5 的阻塞队列
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        // 创建生产者线程
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Producer producing: " + i);
                    queue.put(i); // 如果队列满了会阻塞等待
                    System.out.println("Queue after producing: >>>> " + queue);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 创建消费者线程
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(2000);
                    Integer value = queue.take(); // 如果队列为空会阻塞等待
                    System.out.println("Consumer consuming: " + value);
                    System.out.println("Queue after consuming: <<<< " + queue);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 启动生产者和消费者线程
        producer.start();
        consumer.start();
    }
}
