package com.example.boot01web01.lock;

import com.google.common.util.concurrent.Striped;
import java.util.concurrent.locks.Lock;

public class StringLockHandlerWithGuava {
    // 创建一个支持 64 个分段的锁组
    private final Striped<Lock> lockStriped = Striped.lock(1024);

    public void synchronizedOperation(String key) {
        // 根据 key 获取相应的锁
        Lock lock = lockStriped.get(key);

        // 使用该锁来同步操作
        lock.lock();
        try {
            // 同步操作逻辑
            System.out.println("Processing key: " + key);
            // 模拟操作
            Thread.sleep(4000); // 模拟延时
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        StringLockHandlerWithGuava handler = new StringLockHandlerWithGuava();

        // 示例
        new Thread(() -> handler.synchronizedOperation("exampleKey1")).start();
        new Thread(() -> handler.synchronizedOperation("exampleKey2")).start();
        new Thread(() -> handler.synchronizedOperation("exampleKey3")).start();
        new Thread(() -> handler.synchronizedOperation("exampleKey1")).start();
    }
}