package com.gerry.pang.party3;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockFairLockDemo implements Runnable {

	/**
	 * ReentrantLock(true) ��ǰ��Ϊ��ƽ��
	 * �����̻߳ύ��ִ��
	 * ע�⣺��ƽ����ʵ�ֳɱ��ϸߣ�������Ե��£�����û���������󲻽���ʹ��
	 */
	public static ReentrantLock fairLock = new ReentrantLock(true);

	@Override
	public void run() {
		while (true) {
			try {
				fairLock.lock();
				System.out.println(Thread.currentThread().getName() + " is running");
			} finally {
				fairLock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		ReentrantLockFairLockDemo rfl = new ReentrantLockFairLockDemo();
		Thread t1 = new Thread(rfl, "t1");
		Thread t2 = new Thread(rfl, "t2");
		t1.start();
		t2.start();
	}
}
