package com.gerry.pang.party2;

public class InterruptThread {
	
	/*
	 * public void Thread.interrupt() 中断线程，设置中断标志位
	 * public boolean Thread.isInterrupted() 判断是否中断
	 * public static boolean Thread.interruped() 判断是否中断，并清除当前中断标志位
	 */

	public static void interruptDemo1() throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				while(true) {
					System.out.println("running ……");
				}
			}
		};
		t1.start();
		Thread.sleep(200);
		// interrupt 并未中断线程，线程依旧处于运行状态
		t1.interrupt();
	}
	
	public static void interruptDemo2() throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				while(true) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("thread interrupt");
						break;
					}
					System.out.println("running ……");
				}
			}
		};
		t1.start();
		Thread.sleep(200);
		// interrupt 并未中断线程，线程依旧处于运行状态
		t1.interrupt();
	}
	
	public static void interruptDemo3() throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				while(true) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("thread interrupt");
						break;
					}
					try {
						// 如在sleep时中断线程，会抛出异常并会清除中断标志位，在下一次循环会继续执行
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
						// 故在异常处理中，再次设置中断标志位
						Thread.currentThread().interrupt();
					}
					System.out.println("running ……");
				}
			}
		};
		t1.start();
		Thread.sleep(140);
		// interrupt 并未中断线程，线程依旧处于运行状态
		t1.interrupt();
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		// interruptDemo1();
		// interruptDemo2();
		interruptDemo3();
	}
}
