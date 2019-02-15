package com.gerry.pang.party2;

public class JoinThread {

	private volatile static int i = 0;
	public static class AddThread extends Thread {
		@Override
		public void run() {
			System.out.println("start");
			for(i =0 ; i < 1000; i++) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
			System.out.println("end ");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		AddThread a = new AddThread();
		a.start();
		// join �����������޵ȴ���������ǰ�̣߳�֪��Ŀ���̣߳�a�̣߳�ִ�����
		// join �������õ����߳�wait�ڵ�ǰ�̶߳���ʵ����
		a.join();
		System.out.println(i);
	}
}
