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
	 * �Զ����̳߳� new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler)
	 * J.U.C���ṩ�̳߳ض��ǻ��� ThreadPoolExecutor������
	 * @param args
	 */
	public static void main(String[] args) {
		MyTask task = new MyTask();
		// 5����פ�̣߳�����߳�Ҳ��5��
		ExecutorService exec = new ThreadPoolExecutor(5, 5, 
				// ���ڳ�����פ�̴߳�С�����߳�ʱ������������
				0L, TimeUnit.SECONDS, 
				// ��һ������Ϊ10�ĵȴ�����
				new LinkedBlockingDeque<>(10), 
				// �Զ���ܾ�����
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
