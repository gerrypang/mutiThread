package com.gerry.pang.party3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExtend {

	public static class MyTask implements Runnable {
		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + ": " + Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		MyTask myTask = new MyTask();
		ExecutorService exec = new ThreadPoolExecutor(5, 50, 0L, TimeUnit.SECONDS,
				// 任务队列
				new SynchronousQueue<>(),
				// 自定义线程工程，创建线程
				new ThreadFactory() {
					@Override
					public Thread newThread(Runnable r) {
						Thread thread = new Thread(r);
						thread.setDaemon(false);
						thread.setPriority(Thread.NORM_PRIORITY);
						thread.setDaemon(false);
						return thread;
					}
				}) {
					// 扩展线程池
			
					/**
					 * 线程运行前
					 */
					@Override
					protected void beforeExecute(Thread t, Runnable r) {
						System.out.println("begin " + r.toString() );
					}

					/**
					 * 线程结束后
					 */
					@Override
					protected void afterExecute(Runnable r, Throwable t) {
						System.out.println("finish " + r.toString() );
					}

					/**
					 * 池退关闭后
					 */
					@Override
					protected void terminated() {
						System.out.println("finish and out" );
					}
			
		};
		for (int i = 0; i < 50; i++) {
			exec.submit(myTask);
		}
		exec.shutdown();
	}
}
