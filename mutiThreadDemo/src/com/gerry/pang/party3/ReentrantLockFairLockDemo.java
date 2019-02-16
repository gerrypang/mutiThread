package com.gerry.pang.party3;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockFairLockDemo implements Runnable {

	/**
	 * ReentrantLock(true) 当前锁为公平锁
	 * 两个线程会交替执行
	 * 注意：公平锁定实现成本较高，性能相对低下，如有没有特殊需求不建议使用
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
