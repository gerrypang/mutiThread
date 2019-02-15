package com.gerry.pang.party2;

public class SynchronzedThreadDemo3 implements Runnable {
	
	static int A = 0;
	public static void main(String[] args) throws InterruptedException {
		// 锁-同一个实例
		SynchronzedThreadDemo3 instace = new SynchronzedThreadDemo3();
		Thread t1 = new Thread(instace, "t1");
		Thread t2 = new Thread(instace, "t2");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(A);
	}

	public void add() {
		A++;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			add();
		}
	}
}
