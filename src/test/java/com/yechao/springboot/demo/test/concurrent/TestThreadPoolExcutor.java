package com.yechao.springboot.demo.test.concurrent;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author yechao
 */
public class TestThreadPoolExcutor {

    static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10);

    @Test
    public void test1() throws InterruptedException {
        System.out.println("TestThread:" + Thread.currentThread().getName());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 1, TimeUnit.MINUTES, workQueue);
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("currentThread:" + Thread.currentThread().getName());
                    System.out.println("11111");
                    Thread.sleep(2000);
                    System.out.println("22222");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(2000);
    }


}
