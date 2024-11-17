package com.example.boot01web01.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class StringLockHandler {
    // 存储各个字符串对应的锁
    private final ConcurrentHashMap<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    public void synchronizedOperation(String key) {
        // 获取字符串对应的锁，如果不存在则创建
        ReentrantLock lock = lockMap.computeIfAbsent(key, k -> new ReentrantLock());

        // 使用该锁来同步操作
        lock.lock();
        try {
            // 同步操作逻辑
            System.out.println("Processing key: " + key);
            // 假设进行一些操作
            Thread.sleep(4000); // 这里仅是模拟延时
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            lock.unlock();
            // 操作完成后，可以选择清除不再需要的锁
            lockMap.remove(key, lock);
        }
    }

    public static void main(String[] args) {
        StringLockHandler handler = new StringLockHandler();

        // 示例
        new Thread(() -> handler.synchronizedOperation("exampleKey1")).start();
        new Thread(() -> handler.synchronizedOperation("exampleKey2")).start();
        new Thread(() -> handler.synchronizedOperation("exampleKey3")).start();
        new Thread(() -> handler.synchronizedOperation("exampleKey1")).start();
    }
}