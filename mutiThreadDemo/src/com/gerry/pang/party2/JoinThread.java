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
		// join 会在这里无限等待，阻塞当前线程，知道目标线程（a线程）执行完毕
		// join 本质是让调用线程wait在当前线程对象实例上
		a.join();
		System.out.println(i);
	}
}
