package com.gerry.pang.party2;

public class WaitNotifyThread {
	
	/*
	 * wati() 线程等待，线程进入等待队列
	 * notify() 会随机选择一个线程进行唤醒，
	 * notifyAll() 会唤醒所有等待的线程
	 * 注意：wait,notify,notifyAll 必须在synchronzied语句中执行，必须获取目标对象的监视器（锁）
	 */
	final static Object object = new Object();
	
	public static class ThreadOne extends Thread {
		@Override
		public void run() {
			synchronized (object) {
				System.out.println(System.currentTimeMillis()+ ": t1 start");
				try {
					System.out.println(System.currentTimeMillis()+ ": t1 wait");
					// 必须在sychronzied 中执行获取锁，wait执行后会释放锁
					object.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis()+": t1 end");
			}
		}
	}
	
	public static class ThreadTwo extends Thread {
		@Override
		public void run() {
			synchronized (object) {
				System.out.println(System.currentTimeMillis()+ ": t2 start");
				// notify执行后，会随机唤醒等待队列中线程，notify不会立即释放当前锁，
				object.notify();
				System.out.println(System.currentTimeMillis()+": t2 notify t1");
				// 虽然已经执行了notify，但是此时T1还没有真正执行，因为还没有获取锁
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis()+": t2 end");
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadOne t1 = new ThreadOne();
		ThreadTwo t2 = new ThreadTwo();
		t1.start();
		t2.start();
	}
}
