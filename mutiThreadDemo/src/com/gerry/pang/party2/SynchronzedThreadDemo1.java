package com.gerry.pang.party2;

public class SynchronzedThreadDemo1 implements Runnable {
	
	static int A = 0;
	/*
	 * ע��:���ɱ��������synchronized����
	 */
	// �� - objectʵ��
	static Object object = new Object();
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new SynchronzedThreadDemo1(), "t1");
		Thread t2 = new Thread(new SynchronzedThreadDemo1(), "t2");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(A);
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			// ����ͬ�������飬����Synchonized���������õĶ���
			synchronized (object) {
				A++;
			}
		}
	}
}
