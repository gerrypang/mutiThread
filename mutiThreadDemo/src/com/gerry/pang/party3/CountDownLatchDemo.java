package com.gerry.pang.party3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable {

	static final CountDownLatch end = new CountDownLatch(10);
	static final CountDownLatchDemo demo = new CountDownLatchDemo();

	@Override
	public void run() {
		try {
			Thread.sleep(new Random().nextInt(10) * 1000);
			// 计数器减一
			end.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " finish");
	}

	/**
	 * CountDownLatch 让某一个线程等待计时结束后再开始执行
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool(8);
		for (int i = 0; i < 10; i++) {
			exec.submit(demo);
		}
		// 埋点，等待10个线程都执行完会，才会继续执行后面的代码
		end.await();
		System.out.println("Fire");
		exec.shutdown();
	}

}
