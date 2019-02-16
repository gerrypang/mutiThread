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
	 * Executors���������ṩ��
	 * Executors.newCachedThreadPool() ��С�޽���̳߳�
	 * Executors.newFixedThreadPool() �̶��̳߳�
	 * Executors.newSingleThreadExecutor() �����̳߳�
	 * Executors.newScheduledThreadPool(corePoolSize) ���������̳߳�
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		MyTask myTask = new MyTask();
		
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(10);
		// ����ʼ�ڸ�������ʱ, �������ִ�г���ָ������ʱ��ִ����ɺ���һ�����������ִ��
		exec.scheduleAtFixedRate(myTask, 0, 2, TimeUnit.SECONDS);
		
		// �ϸ���������󣬵���һ������ʼ��ʱ���
		// exec.scheduleWithFixedDelay(myTask, 0, 1, TimeUnit.SECONDS);
	}
}
