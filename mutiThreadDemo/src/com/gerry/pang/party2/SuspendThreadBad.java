package com.gerry.pang.party2;

public class SuspendThreadBad {
	public static Object object = new Object(); 
	
	public static class ChangeObjectThread extends Thread {
		public ChangeObjectThread(String name) {
			super.setName(name);
		}
		
		@Override
		public void run() {
			synchronized (object) {
				System.out.println("runing in " + getName());
				Thread.currentThread().suspend();
			}
		}
	}
	
	/*
	 * 不能使用suspend和resume，主要因为suspend导致线程暂停的同时，并不释放锁，会导致线程永久挂起
	 * 推荐使用wait/notify替代
	 */
	public static void main(String[] args) throws InterruptedException {
		ChangeObjectThread c1 = new ChangeObjectThread("c1");
		ChangeObjectThread c2 = new ChangeObjectThread("c2");
		c1.start();
		Thread.sleep(100);
		c2.start();
		c1.resume();
		c2.resume();
		c1.join();
		c2.join();
	}

}
