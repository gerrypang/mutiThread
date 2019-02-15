package com.gerry.pang.party2;

public class DeamonThreadDemo {
	
	public static class DeamonT extends Thread {
		@Override
		public void run() {
			while(true) {
				System.out.println("runing ……");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		DeamonT dt = new DeamonT();
		// 注意：设置守护线程必须在线程启动之前
		dt.setDaemon(true);
		dt.start();
		Thread.sleep(10000);
	}
}
