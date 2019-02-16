package com.gerry.pang.party3;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTimeLockDemo2 implements Runnable {

	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();

	public int A = 0;

	public ReentrantLockTimeLockDemo2(int a) {
		A = a;
	}

	@Override
	public void run() {
		if (A == 1) {
			while (true) {
				try {
					if (lock1.tryLock()) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					if (lock2.tryLock()) {
						try {
							System.out.println(Thread.currentThread().getName() + " my job done");
							return;
						} finally {
							lock2.unlock();
						}
					}
				} finally {
					lock1.unlock();
				}
			}
		} else {
			while (true) {
				try {
					if (lock2.tryLock()) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					if (lock1.tryLock()) {
						try {
							System.out.println(Thread.currentThread().getName() + " my job done");
							return;
						} finally {
							lock1.unlock();
						}
					}
				} finally {
					lock2.unlock();
				}
			}
		}
	}

	/**
	 * tryLock() �����̳��Ի�ȡ�������û�л�ȡ����false tryLock(long timeout, TimeUnit unit)
	 * �᳢����ָ��ʱ���ڻ�����������ȡ����true������false
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		ReentrantLockTimeLockDemo2 rtd1 = new ReentrantLockTimeLockDemo2(1);
		ReentrantLockTimeLockDemo2 rtd2 = new ReentrantLockTimeLockDemo2(2);
		Thread t1 = new Thread(rtd1, "t1");
		Thread t2 = new Thread(rtd2, "t2");
		t1.start();
		t2.start();
	}

}
