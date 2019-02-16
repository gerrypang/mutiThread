package com.gerry.pang.party2;

public class SynchronzedThreadDemo2 implements Runnable {
	
	static int A = 0;
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new SynchronzedThreadDemo2(), "t1");
		Thread t2 = new Thread(new SynchronzedThreadDemo2(), "t2");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(A);
	}

	/**
	 * 对于静态同步方法，锁是类
	 * 锁-类锁
	 */
	public static synchronized void add() {
		A++;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			add();
		}
	}
}
