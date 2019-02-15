package com.gerry.pang.party2;

public class VolatileGoodUse {
	
	private static volatile boolean ready;
	private static volatile int number;
	
	public static class ReadThread extends Thread {
		@Override
		public void run() {
			System.out.println("start");
			while (!ready);
			System.out.println(number);
			System.out.println("end");
		}
	}
	
	/*
	 * volatile������
	 * 1�����Ա�֤���ݵĿɼ��Ժ�������
	 * 2����ֹjvm����ָ������
	 */
	public static void main(String[] args) throws InterruptedException {
		new ReadThread().start();
		Thread.sleep(1000);
		number = 55;
		ready = true;
		Thread.sleep(1000);
	}
}
