package com.gerry.pang.party2;

public class CreateThreadDemo {
	/**
	 * �����̵߳ķ���
	 * @param args
	 */
	public static void main(String[] args) {
		method01();
		method02();
	}
	
	/**
	 * ����һ���̳�thread�࣬��дrun����
	 */
	public static void method01() {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				System.out.println("001 - Hello, i am thread");
			}
		};
		t1.start();
	}
	
	/**
	 * ��������ʵ��runable�ӿ�,��дrun���� 
	 * @author Gerry_Pang
	 *
	 */
	static class CreateThread implements Runnable {
		@Override
		public void run() {
			System.out.println("002 - Hello, i am thread");
		}
	}  
	
	public static void method02() {
		CreateThread ct = new CreateThread();
		Thread t2 = new Thread(ct);
		t2.start();
	}

}
