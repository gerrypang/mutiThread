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
	 * volatile的作用
	 * 1、可以保证数据的可见性和有序性
	 * 2、防止jvm进行指令重排
	 */
	public static void main(String[] args) throws InterruptedException {
		new ReadThread().start();
		Thread.sleep(1000);
		number = 55;
		ready = true;
		Thread.sleep(1000);
	}
}
