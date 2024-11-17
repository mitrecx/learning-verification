package com.example.boot01web01;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample {
    
    // 自定义任务类实现 Comparable 接口，以确定优先级顺序
    static class Task implements Comparable<Task> {
        private int priority;
        private String name;

        public Task(int priority, String name) {
            this.priority = priority;
            this.name = name;
        }

        public int getPriority() {
            return priority;
        }

        @Override
        public int compareTo(Task o) {
            // 优先级越小越优先
            return Integer.compare(this.priority, o.priority);
        }

        @Override
        public String toString() {
            return "Task{name='" + name + "', priority=" + priority + '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建 PriorityBlockingQueue 实例
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();

        // 添加任务到队列
        queue.put(new Task(3, "Low priority task"));
        queue.put(new Task(1, "High priority task"));
        queue.put(new Task(2, "Medium priority task"));

        // 消费任务
        while (!queue.isEmpty()) {
            // 取出并移除最高优先级任务
            System.out.println("处理任务: " + queue.take());
        }
    }
}