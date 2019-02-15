package com.gerry.pang.party2;

public class ThreadGroupDemo implements Runnable {
	@Override
	public void run() {
		String groupNameAndName = Thread.currentThread().getThreadGroup().getName() + "_"
				+ Thread.currentThread().getName();
		while (true) {
			System.out.println(groupNameAndName);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 注意：在创建线程和线程组的时候，必须给其命名一个便于识别的名字
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadGroup tg = new ThreadGroup("gerry");
		// 构造方法 thread (threadGroup, runnable, threadName )
		Thread t1 = new Thread(tg, new ThreadGroupDemo(), "t1");
		Thread t2 = new Thread(tg, new ThreadGroupDemo(), "t2");
		t1.start();
		t2.start();
		// activeCount可以获得活动线程总数，由于线程是动态的，此值并不精确，只是估值
		System.out.println(tg.activeCount());
		tg.list();
	}
}
