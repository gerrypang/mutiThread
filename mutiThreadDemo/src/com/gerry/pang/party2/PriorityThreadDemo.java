package com.gerry.pang.party2;

public class PriorityThreadDemo {

	public static class HighPriority extends Thread {
		static int count = 0;
		@Override
		public void run() {
			while(true) {
				synchronized (PriorityThreadDemo.class) {
					count ++;
					if (count > 1000000) {
						System.out.println("HighPriority complete");
						break;
					}
				}
			}
		}
	}
	
	public static class LowPriority extends Thread {
		static int count = 0;
		@Override
		public void run() {
			while(true) {
				// �����̶߳�ͬһ��Դ����
				synchronized (PriorityThreadDemo.class) {
					count ++;
					if (count > 1000000) {
						System.out.println("lowPriority complete");
						break;
					}
				}
			}
		}
	} 
	
	/**
	 * �̵߳��и����ȼ�ֻ�Ǵ���,�п��ܱȵ����ȼ���������,����ʾ��Ȼ�ȵ����ȼ�������,ֻ����,������
	 * @param args
	 */
	public static void main(String[] args) {
		Thread high = new HighPriority();
		Thread low = new LowPriority();
		// �����߳����ȼ�
		high.setPriority(Thread.MAX_PRIORITY);
		low.setPriority(Thread.MIN_PRIORITY);
		low.start();
		high.start();
	}
}
