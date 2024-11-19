package com.example.boot01web01.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockPerformanceTest {
    private static final int ITERATIONS = 1_000_000; // 每线程操作次数
    private static final int THREADS = 1;          // 线程数

    // 使用 synchronized 的测试
    static class SynchronizedTest {
        private int count = 0;
        private int a = 0;
        private int b = 0;
        private int c = 0;
        private int d = 0;
        private int e = 0;
        private int f = 0;
        private int g = 0;
        private int h = 0;
        private int i = 0;


        public synchronized void increment() {
            count++; a++; b++; c++; d++; e++; f++; g++; h++; i++;
        }

        public int runTest() throws InterruptedException {
            Thread[] threads = new Thread[THREADS];
            for (int i = 0; i < THREADS; i++) {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < ITERATIONS; j++) {
                        increment();
                    }
                });
            }

            long start = System.currentTimeMillis();
            for (Thread thread : threads) thread.start();
            for (Thread thread : threads) thread.join();
            long end = System.currentTimeMillis();
            System.out.println("SynchronizedTest total time: " + (end - start) + " ms");
            return count;
        }
    }

    // 使用 ReentrantLock 的测试
    static class ReentrantLockTest {
        private int count = 0;
        private int a = 0;
        private int b = 0;
        private int c = 0;
        private int d = 0;
        private int e = 0;
        private int f = 0;
        private int g = 0;
        private int h = 0;
        private int i = 0;
        private final ReentrantLock lock = new ReentrantLock();

        public void increment() {
            lock.lock();
            try {
                count++; a++; b++; c++; d++; e++; f++; g++; h++; i++;
            } finally {
                lock.unlock();
            }
        }

        public int runTest() throws InterruptedException {
            Thread[] threads = new Thread[THREADS];
            for (int i = 0; i < THREADS; i++) {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < ITERATIONS; j++) {
                        increment();
                    }
                });
            }

            long start = System.currentTimeMillis();
            for (Thread thread : threads) thread.start();
            for (Thread thread : threads) thread.join();
            long end = System.currentTimeMillis();
            System.out.println("ReentrantLockTest total time: " + (end - start) + " ms");
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Testing Synchronized...");
        SynchronizedTest syncTest = new SynchronizedTest();
        int syncResult = syncTest.runTest();
        System.out.println("SynchronizedTest result: " + syncResult);

        System.out.println("\nTesting ReentrantLock...");
        ReentrantLockTest lockTest = new ReentrantLockTest();
        int lockResult = lockTest.runTest();
        System.out.println("ReentrantLockTest result: " + lockResult);
    }
}