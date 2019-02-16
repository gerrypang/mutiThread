package com.gerry.pang.party3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockConditionDemo implements Runnable {

	public static ReentrantLock rl = new ReentrantLock();
	// ����һ���뵱ǰ�������󶨵�Conditionʵ��
	// Conditionֻ�ǽӿڣ�����ʵ���������Lock�ӿ��µ�ʵ���ࣨ���磺ReentrantLock��
	public static Condition condition = rl.newCondition();
	
	@Override
	public void run() {
		System.out.println("start");
		rl.lock();
		try {
			condition.await(); // ʹ��ǰ�̵߳ȴ������ͷ���
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
		
		// ����һ���ڵȴ��е��̣߳�ֻ�ǻ��ѣ�������ֱ�ӻ�ȡ��
		// ע�⣺����await�Ѿ��ͷ���������ʹ��ǰ�������»�ȡ������������ java.lang.IllegalMonitorStateException
		condition.signal();
		
		rl.unlock();
	}
}
