package com.example.boot01web01;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// 定义一个任务类，实现 Delayed 接口
class DelayedTask implements Delayed {
    private final String name;
    private final long delayTime; // 任务的延迟时间（毫秒）
    private final long startTime; // 任务的到期时间

    public DelayedTask(String name, long delayTime) {
        this.name = name;
        this.delayTime = delayTime;
        this.startTime = System.currentTimeMillis() + delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // 返回剩余的延迟时间
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
            return -1;
        } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Task{name='" + name + "', delayTime=" + delayTime + "ms}";
    }
}

public class DelayQueueExample {
    public static void main(String[] args) throws InterruptedException {
        // 创建 DelayQueue 实例
        DelayQueue<DelayedTask> queue = new DelayQueue<>();

        // 添加任务到队列，设置不同的延迟时间
        queue.put(new DelayedTask("Task1", 3000));
        queue.put(new DelayedTask("Task2", 1000));
        queue.put(new DelayedTask("Task3", 5000));

        System.out.println("开始处理任务：");

        // 消费任务
        while (!queue.isEmpty()) {
            // 从队列中取出到期任务
            DelayedTask task = queue.take();
            System.out.println("处理任务: " + task);
        }

        System.out.println("所有任务已处理完毕。");
    }
}