package com.gerry.pang.party2;

public class VolatileBadUse {
	
	static volatile int A = 0;
	
	public static class PlusTask extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 10000; i++) {
				A++;
			}
		}
	}
	
	/*
	 * ע�⣺volatile�ǲ�������������۱�֤һЩ���ϲ�����ԭ����
	 */
	
	public static void main(String[] args) throws InterruptedException {
		Thread[] thread = new Thread[10];
		for (int i = 0; i < thread.length; i++) {
			thread[i] = new Thread(new PlusTask());
			thread[i].start();
		}
		for (int i = 0; i < thread.length; i++) {
			thread[i].join();
		}
		System.out.println(A);
	}

}
