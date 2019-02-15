package com.gerry.pang.party2;

public class CreateThreadDemo {
	/**
	 * 创建线程的方法
	 * @param args
	 */
	public static void main(String[] args) {
		method01();
		method02();
	}
	
	/**
	 * 方法一：继承thread类，覆写run方法
	 */
	public static void method01() {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				System.out.println("001 - Hello, i am thread");
			}
		};
		t1.start();
	}
	
	/**
	 * 方法二：实现runable接口,覆写run方法 
	 * @author Gerry_Pang
	 *
	 */
	static class CreateThread implements Runnable {
		@Override
		public void run() {
			System.out.println("002 - Hello, i am thread");
		}
	}  
	
	public static void method02() {
		CreateThread ct = new CreateThread();
		Thread t2 = new Thread(ct);
		t2.start();
	}

}
