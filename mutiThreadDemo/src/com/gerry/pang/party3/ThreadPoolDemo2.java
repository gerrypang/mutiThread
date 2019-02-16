package com.gerry.pang.party3;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo2 {
	public static class MyTask implements Runnable {
		@Override
		public void run() {
			System.out.println(System.currentTimeMillis()/1000 + ": " + Thread.currentThread().getName());
			try {
				int a = new Random().nextInt(5) * 1000;
				System.out.println(a);
				Thread.sleep(a);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Executors工厂类型提供了
	 * Executors.newCachedThreadPool() 大小无界的线程池
	 * Executors.newFixedThreadPool() 固定线程池
	 * Executors.newSingleThreadExecutor() 单个线程池
	 * Executors.newScheduledThreadPool(corePoolSize) 周期任务线程池
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		MyTask myTask = new MyTask();
		
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(10);
		// 任务开始于给定的延时, 如果任务执行超过指定的延时，执行完成后下一个任务会立刻执行
		exec.scheduleAtFixedRate(myTask, 0, 2, TimeUnit.SECONDS);
		
		// 上个任务结束后，到下一个任务开始的时间差
		// exec.scheduleWithFixedDelay(myTask, 0, 1, TimeUnit.SECONDS);
	}
}
