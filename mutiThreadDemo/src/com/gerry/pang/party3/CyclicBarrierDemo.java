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
				// 线程中断抛出异常
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// 屏障破坏异常
				e.printStackTrace();
			}
		}

		public void doWork() {
			try {
				Thread.sleep(new Random().nextInt(10) * 100);
				System.out.println(solider + " 任务完成");
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
			System.out.println("任务完成");
		}
	}

	/**
	 * CyclicBarrier(int parties, Runnable barrierAction) parties 参与线程的总数
	 * barrierAction 当计数器完成一批计数后会执行的任务
	 * 
	 * CyclicBarrier和CountDown不同在于，计数器可以反复使用
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final int N = 5;
		Thread[] allSoldier = new Thread[10];
		CyclicBarrier cyclic = new CyclicBarrier(N, new Barrier(N));
		System.out.println("集合队伍");
		for (int i = 0; i < N ; i++) {
			System.out.println("士兵" + i + "报道");
			allSoldier[i] = new Thread(new Solder(cyclic, "士兵" + i));
			allSoldier[i].start();
		}
	}
}
