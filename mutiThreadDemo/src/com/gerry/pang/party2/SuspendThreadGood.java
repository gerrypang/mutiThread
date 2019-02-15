package com.gerry.pang.party2;

public class SuspendThreadGood {
	public static Object object = new Object(); 
	
	public static class ChangeObjectThread extends Thread {
		
		public ChangeObjectThread(String name) {
			super.setName(name);
		}
		
		public void suspendMe() {
			try {
				object.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public void resumeMe() {
			synchronized (object) {
				object.notify();
				System.out.println("resume " + getName());
			}
		}
		
		@Override
		public void run() {
			synchronized (object) {
				System.out.println("runing in " + getName());
				suspendMe();
			}
			System.out.println("end  " + getName());
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ChangeObjectThread c1 = new ChangeObjectThread("c1");
		//ChangeObjectThread c2 = new ChangeObjectThread("c2");
		c1.start();
		Thread.sleep(200);
		//c2.start();
		// ���⣺��Ϊnotify������ģ������������̣߳��п���resume�Ĳ�������Ҫ���Ǹ��߳�
		c1.resumeMe();
		//c2.resumeMe();
	}

}
