package com.gerry.pang.party2;

public class PriorityThreadDemo {

	public static class HighPriority extends Thread {
		static int count = 0;
		@Override
		public void run() {
			while(true) {
				synchronized (PriorityThreadDemo.class) {
					count ++;
					if (count > 1000000) {
						System.out.println("HighPriority complete");
						break;
					}
				}
			}
		}
	}
	
	public static class LowPriority extends Thread {
		static int count = 0;
		@Override
		public void run() {
			while(true) {
				// 两个线程对同一资源争夺
				synchronized (PriorityThreadDemo.class) {
					count ++;
					if (count > 1000000) {
						System.out.println("lowPriority complete");
						break;
					}
				}
			}
		}
	} 
	
	/**
	 * 线程的有高优先级只是代表,有肯能比低优先级更有优势,不表示必然比低优先级限制性,只可能,概率性
	 * @param args
	 */
	public static void main(String[] args) {
		Thread high = new HighPriority();
		Thread low = new LowPriority();
		// 设置线程优先级
		high.setPriority(Thread.MAX_PRIORITY);
		low.setPriority(Thread.MIN_PRIORITY);
		low.start();
		high.start();
	}
}
