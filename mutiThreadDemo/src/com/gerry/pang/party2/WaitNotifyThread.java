package com.gerry.pang.party2;

public class WaitNotifyThread {
	
	/*
	 * wati() �̵߳ȴ����߳̽���ȴ�����
	 * notify() �����ѡ��һ���߳̽��л��ѣ�
	 * notifyAll() �ỽ�����еȴ����߳�
	 * ע�⣺wait,notify,notifyAll ������synchronzied�����ִ�У������ȡĿ�����ļ�����������
	 */
	final static Object object = new Object();
	
	public static class ThreadOne extends Thread {
		@Override
		public void run() {
			synchronized (object) {
				System.out.println(System.currentTimeMillis()+ ": t1 start");
				try {
					System.out.println(System.currentTimeMillis()+ ": t1 wait");
					// ������sychronzied ��ִ�л�ȡ����waitִ�к���ͷ���
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
				// notifyִ�к󣬻�������ѵȴ��������̣߳�notify���������ͷŵ�ǰ����
				object.notify();
				System.out.println(System.currentTimeMillis()+": t2 notify t1");
				// ��Ȼ�Ѿ�ִ����notify�����Ǵ�ʱT1��û������ִ�У���Ϊ��û�л�ȡ��
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
