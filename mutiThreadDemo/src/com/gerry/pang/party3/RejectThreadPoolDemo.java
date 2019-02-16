package com.gerry.pang.party3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectThreadPoolDemo {
	
	public static class MyTask implements Runnable {

		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 自定义线程池 new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler)
	 * J.U.C中提供线程池都是基于 ThreadPoolExecutor创建的
	 * @param args
	 */
	public static void main(String[] args) {
		MyTask task = new MyTask();
		// 5个常驻线程，最大线程也是5，
		ExecutorService exec = new ThreadPoolExecutor(5, 5, 
				// 存在超过常驻线程大小空闲线程时，会立刻销毁
				0L, TimeUnit.SECONDS, 
				// 有一个容量为10的等待队列
				new LinkedBlockingDeque<>(10), 
				// 自定义拒绝策略
				new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println(System.currentTimeMillis() + ":" + r.toString() + "is discard");
			}
		});
		
		
		
		for (int i = 0; i < 20; i++) {
			exec.submit(task);
		}
		exec.shutdown();
	}
}
