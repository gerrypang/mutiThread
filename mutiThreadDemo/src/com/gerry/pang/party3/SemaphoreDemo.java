package com.gerry.pang.party3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo implements Runnable {

	// 申请包含5个信号量许可，同时有五个线程获取同一资源
	public static Semaphore semaphore = new Semaphore(5);

	@Override
	public void run() {
		try {
			// 尝试获得准入许可，没有则等待， 直到线程释放或中断
			semaphore.acquire();
			Thread.sleep(500);
			System.out.println(System.currentTimeMillis() + ": " + Thread.currentThread().getName() + " is running");
			// 线程结束后释放一个资源许可，以便其他线程可以运行
			// 注意：离开时，必须使用release释放信号量
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * semaphore 可以指定多个线程，同时访问一资源
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(20);
		final SemaphoreDemo sd = new SemaphoreDemo();
		for (int i = 0; i < 20; i++) {
			exec.submit(sd);
		}
		exec.shutdown();
	}

}
