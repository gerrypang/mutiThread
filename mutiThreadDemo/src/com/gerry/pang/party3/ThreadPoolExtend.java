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
				// �������
				new SynchronousQueue<>(),
				// �Զ����̹߳��̣������߳�
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
					// ��չ�̳߳�
			
					/**
					 * �߳�����ǰ
					 */
					@Override
					protected void beforeExecute(Thread t, Runnable r) {
						System.out.println("begin " + r.toString() );
					}

					/**
					 * �߳̽�����
					 */
					@Override
					protected void afterExecute(Runnable r, Throwable t) {
						System.out.println("finish " + r.toString() );
					}

					/**
					 * ���˹رպ�
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
