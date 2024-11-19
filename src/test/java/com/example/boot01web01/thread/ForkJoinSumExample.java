package com.example.boot01web01.thread;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinSumExample {
    // 自定义任务类，继承 RecursiveTask
    static class SumTask extends RecursiveTask<Long> {
        private final int[] arr;
        private final int start, end;
        private static final int THRESHOLD = 10; // 阈值

        public SumTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            // 如果任务足够小，直接计算
            if (end - start <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += arr[i];
                }
                return sum;
            } else {
                // 任务太大，继续拆分
                int mid = (start + end) / 2;
                SumTask leftTask = new SumTask(arr, start, mid);
                SumTask rightTask = new SumTask(arr, mid, end);

                // 递归提交子任务
                leftTask.fork();
                rightTask.fork();

                // 合并子任务的结果
                return leftTask.join() + rightTask.join();
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1; // 初始化 1 到 100 的数据
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumTask task = new SumTask(array, 0, array.length);

        long result = forkJoinPool.invoke(task); // 提交任务
        System.out.println("Sum: " + result);

        forkJoinPool.shutdown(); // 关闭线程池
    }
}