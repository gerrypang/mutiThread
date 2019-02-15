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
	 * ע�⣺�ڴ����̺߳��߳����ʱ�򣬱����������һ������ʶ�������
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadGroup tg = new ThreadGroup("gerry");
		// ���췽�� thread (threadGroup, runnable, threadName )
		Thread t1 = new Thread(tg, new ThreadGroupDemo(), "t1");
		Thread t2 = new Thread(tg, new ThreadGroupDemo(), "t2");
		t1.start();
		t2.start();
		// activeCount���Ի�û�߳������������߳��Ƕ�̬�ģ���ֵ������ȷ��ֻ�ǹ�ֵ
		System.out.println(tg.activeCount());
		tg.list();
	}
}
