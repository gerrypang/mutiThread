package com.gerry.pang.party3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo1 {
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
	
	/**
	 * Executors���������ṩ��
	 * Executors.newCachedThreadPool() ��С�޽���̳߳�
	 * Executors.newFixedThreadPool() �̶��̳߳�
	 * Executors.newSingleThreadExecutor() �����̳߳�
	 * Executors.newScheduledThreadPool(corePoolSize) ���������̳߳�
	 * @param args
	 */
	public static void main(String[] args) {
		MyTask myTask = new MyTask();
		
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			exec.submit(myTask);
		}
		exec.shutdown();
	}
}
