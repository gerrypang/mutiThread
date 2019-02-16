package com.gerry.pang.party3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTimeLockDemo1 implements Runnable {

	public static ReentrantLock reentrantLock = new ReentrantLock();

	@Override
	public void run() {
		try {
			if (reentrantLock.tryLock(5, TimeUnit.SECONDS)) {
				Thread.sleep(6000);
			} else {
				System.err.println(Thread.currentThread().getName() + " lock failed");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (reentrantLock.isHeldByCurrentThread()) {
				reentrantLock.unlock();
			}
		}
	}

	/**
	 * tryLock() �����̳��Ի�ȡ�������û�л�ȡ����false
	 * tryLock(long timeout, TimeUnit unit) �᳢����ָ��ʱ���ڻ�����������ȡ����true������false
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		ReentrantLockTimeLockDemo1 rtd = new ReentrantLockTimeLockDemo1();
		Thread t1 = new Thread(rtd, "t1");
		Thread t2 = new Thread(rtd, "t2");
		t1.start();
		Thread.sleep(500);
		t2.start();
	}

}
