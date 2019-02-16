package com.gerry.pang.party3;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements Runnable {
	
	public static ReentrantLock reentrantLock = new ReentrantLock();
	public static int A = 0;
	
	public static void main(String[] args) throws InterruptedException {
		ReentrantLockDemo rd = new ReentrantLockDemo();
		Thread t1 = new Thread(rd);
		Thread t2 = new Thread(rd);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(A);
	}

	/**
	 * Re-entrant-Lock ������
	 * ReentrantLock ������ȫ���synchronized
	 * ע�⣺�����ͽ����ǳɶԵģ��϶�������Ҫ����Ӧ������
	 * ����Ҫ����try/finally��
	 */
	@Override
	public void run() {
		for (int j = 0; j < 1000; j++) {
			reentrantLock.lock();
			//reentrantLock.tryLock();
			try {
				A++;
			} finally {
				reentrantLock.unlock();
			}
		}
	}
}
