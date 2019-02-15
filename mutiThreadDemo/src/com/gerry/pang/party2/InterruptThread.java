package com.gerry.pang.party2;

public class InterruptThread {
	
	/*
	 * public void Thread.interrupt() �ж��̣߳������жϱ�־λ
	 * public boolean Thread.isInterrupted() �ж��Ƿ��ж�
	 * public static boolean Thread.interruped() �ж��Ƿ��жϣ��������ǰ�жϱ�־λ
	 */

	public static void interruptDemo1() throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				while(true) {
					System.out.println("running ����");
				}
			}
		};
		t1.start();
		Thread.sleep(200);
		// interrupt ��δ�ж��̣߳��߳����ɴ�������״̬
		t1.interrupt();
	}
	
	public static void interruptDemo2() throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				while(true) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("thread interrupt");
						break;
					}
					System.out.println("running ����");
				}
			}
		};
		t1.start();
		Thread.sleep(200);
		// interrupt ��δ�ж��̣߳��߳����ɴ�������״̬
		t1.interrupt();
	}
	
	public static void interruptDemo3() throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				while(true) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("thread interrupt");
						break;
					}
					try {
						// ����sleepʱ�ж��̣߳����׳��쳣��������жϱ�־λ������һ��ѭ�������ִ��
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
						// �����쳣�����У��ٴ������жϱ�־λ
						Thread.currentThread().interrupt();
					}
					System.out.println("running ����");
				}
			}
		};
		t1.start();
		Thread.sleep(140);
		// interrupt ��δ�ж��̣߳��߳����ɴ�������״̬
		t1.interrupt();
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		// interruptDemo1();
		// interruptDemo2();
		interruptDemo3();
	}
}
