package com.gerry.pang.party3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockConditionDemo implements Runnable {

	public static ReentrantLock rl = new ReentrantLock();
	// 创建一个与当前重入锁绑定的Condition实例
	// Condition只是接口，创建实例必须借助Lock接口下的实现类（例如：ReentrantLock）
	public static Condition condition = rl.newCondition();
	
	@Override
	public void run() {
		System.out.println("start");
		rl.lock();
		try {
			condition.await(); // 使当前线程等待，并释放锁
			System.out.println(Thread.currentThread().getName() + " is running");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rl.unlock();
		}
		System.out.println("end");
	}

	public static void main(String[] args) throws InterruptedException {
		ReentrantLockConditionDemo rcd = new ReentrantLockConditionDemo();
		Thread t1 = new Thread(rcd, "t1");
		t1.start();
		Thread.sleep(2000);
		rl.lock();
		
		// 唤醒一个在等待中的线程，只是唤醒，并不能直接获取锁
		// 注意：由于await已经释放锁，所以使用前必须重新获取锁，否则会出现 java.lang.IllegalMonitorStateException
		condition.signal();
		
		rl.unlock();
	}
}
