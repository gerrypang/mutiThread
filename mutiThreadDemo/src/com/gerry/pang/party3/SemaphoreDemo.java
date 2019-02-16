package com.gerry.pang.party3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo implements Runnable {

	// �������5���ź�����ɣ�ͬʱ������̻߳�ȡͬһ��Դ
	public static Semaphore semaphore = new Semaphore(5);

	@Override
	public void run() {
		try {
			// ���Ի��׼����ɣ�û����ȴ��� ֱ���߳��ͷŻ��ж�
			semaphore.acquire();
			Thread.sleep(500);
			System.out.println(System.currentTimeMillis() + ": " + Thread.currentThread().getName() + " is running");
			// �߳̽������ͷ�һ����Դ��ɣ��Ա������߳̿�������
			// ע�⣺�뿪ʱ������ʹ��release�ͷ��ź���
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * semaphore ����ָ������̣߳�ͬʱ����һ��Դ
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(20);
		final SemaphoreDemo sd = new SemaphoreDemo();
		for (int i = 0; i < 20; i++) {
			exec.submit(sd);
		}
		exec.shutdown();
	}

}
