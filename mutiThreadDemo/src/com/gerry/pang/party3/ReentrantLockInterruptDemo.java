package com.gerry.pang.party3;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockInterruptDemo implements Runnable {

	public static ReentrantLock r1 = new ReentrantLock();
	public static ReentrantLock r2 = new ReentrantLock();
	
	public int lock = 0;

	public ReentrantLockInterruptDemo(int lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		try {
			if (lock == 1) {
				r1.lockInterruptibly();
				Thread.sleep(200);
				r2.lockInterruptibly();
			} else {
				r2.lockInterruptibly();
				Thread.sleep(200);
				r1.lockInterruptibly();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(r1.isHeldByCurrentThread()) {
				r1.unlock();
			}
			if(r2.isHeldByCurrentThread()) {
				r2.unlock();
			}
			System.out.println(Thread.currentThread().getName() + "�˳��߳�");
		}
	}
	
	/**
	 * lockInterruptibly() �����ȴ��Ĺ����У�������Ӧ�ж�
	 * isHeldByCurrentThread��ѯ��ǰ�߳��Ƿ񱣳ִ����� 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		ReentrantLockInterruptDemo rd1 = new ReentrantLockInterruptDemo(1);
		ReentrantLockInterruptDemo rd2 = new ReentrantLockInterruptDemo(2);
		Thread t1 = new Thread(rd1);
		Thread t2 = new Thread(rd2);
		t1.start();
		t2.start();
		Thread.sleep(600);
		// ����Ĵ���ᵼ��t1 / t2 ֮���໥�ȴ��� ��������
		t2.interrupt(); // t2 �жϻ�ִ�� catch/ finally �Ӷ��ͷ��߳���
 	}
}
