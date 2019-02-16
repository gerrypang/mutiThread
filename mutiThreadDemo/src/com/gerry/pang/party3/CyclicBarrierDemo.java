package com.gerry.pang.party3;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	public static class Solder implements Runnable {
		private String solider;
		private final CyclicBarrier cyclicBarrier;

		public Solder(CyclicBarrier cyclicBarrier, String soliderName) {
			this.solider = soliderName;
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			try {
				cyclicBarrier.await();
				doWork();
				cyclicBarrier.await();
				doWork();
				cyclicBarrier.wait();
			} catch (InterruptedException e) {
				// �߳��ж��׳��쳣
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// �����ƻ��쳣
				e.printStackTrace();
			}
		}

		public void doWork() {
			try {
				Thread.sleep(new Random().nextInt(10) * 100);
				System.out.println(solider + " �������");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Barrier implements Runnable {
		int N = 0;

		public Barrier(int index) {
			this.N = index;
		}

		@Override
		public void run() {
			System.out.println("�������");
		}
	}

	/**
	 * CyclicBarrier(int parties, Runnable barrierAction) parties �����̵߳�����
	 * barrierAction �����������һ���������ִ�е�����
	 * 
	 * CyclicBarrier��CountDown��ͬ���ڣ����������Է���ʹ��
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final int N = 5;
		Thread[] allSoldier = new Thread[10];
		CyclicBarrier cyclic = new CyclicBarrier(N, new Barrier(N));
		System.out.println("���϶���");
		for (int i = 0; i < N ; i++) {
			System.out.println("ʿ��" + i + "����");
			allSoldier[i] = new Thread(new Solder(cyclic, "ʿ��" + i));
			allSoldier[i].start();
		}
	}
}
