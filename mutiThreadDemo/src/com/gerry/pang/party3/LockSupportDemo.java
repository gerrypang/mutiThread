package com.gerry.pang.party3;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
	
	public static Object object = new Object();
	
	/**
	 * LockSupport.park() ������ǰ�߳�
	 * ���ź�����ͬ���ź��������ж���̣߳�LockSupportֻ�����һ���߳�
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		ChangeObjectThread t1 = new ChangeObjectThread("t1");
		ChangeObjectThread t2 = new ChangeObjectThread("t2");
		t1.start();
		Thread.sleep(1000);
		t2.start();
		LockSupport.unpark(t1);
		LockSupport.unpark(t2);
		t1.join();
		t2.join();
	}
	
	public static class ChangeObjectThread extends Thread {

		public ChangeObjectThread(String name) {
			super.setName(name);
		}
		
		@Override
		public void run() {
			synchronized (object) {
				System.out.println("in " + getName());
				LockSupport.park();
//				LockSupport.park(this);
				System.out.println("end" + getName());
			}
		}
		
	}

}
