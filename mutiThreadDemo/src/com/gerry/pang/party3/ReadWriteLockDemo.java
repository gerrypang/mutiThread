package com.gerry.pang.party3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

	public static Lock lock = new ReentrantLock();
	public static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	// 获取读锁
	public static Lock readLock = rwl.readLock();
	// 获取写锁
	public static Lock writeLock = rwl.writeLock();
	public int value;

	public Object handleRead(Lock lock) throws InterruptedException {
		lock.lock();
		try {
			Thread.sleep(1000);
		} finally {
			lock.unlock();
		}
		System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName() + " finish");
		return value;
	}

	public void hanleWrite(Lock lock, int value) throws InterruptedException {
		lock.lock();
		try {
			this.value = value;
			Thread.sleep(1000);
		} finally {
			lock.unlock();
		}
		System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName() + " finish");
	}

	/**
	 * ReentrantReadWrite 可以有效减少竞争，提升系统性能
	 * 口诀：读读共享，读写互斥，写写互斥 
	 * @param args
	 */
	public static void main(String[] args) {
		final ReadWriteLockDemo rwd = new ReadWriteLockDemo();
		Runnable readRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					// rwd.handleRead(lock);
					rwd.handleRead(readLock);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		Runnable writeRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					// rwd.hanleWrite(lock, 100);
					rwd.hanleWrite(writeLock, 100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		for (int i = 0; i < 18; i++) {
			new Thread(readRunnable).start();
		}
		for (int i = 18; i < 20; i++) {
			new Thread(writeRunnable).start();
		}
	}

}
