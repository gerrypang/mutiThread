package com.gerry.pang.party2;

public class StopThreadSafe {
	
	private static User user = new User();
	
	public static class User {
		private int id = 0;
		private String name = "0";

		@Override
		public String toString() {
			return "user { id:" + id + ", name:" + name + " }";
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	
	/**
	 * 修改对象线程
	 * @author Gerry_Pang
	 */
	public static class ChangeObjectThread extends Thread {
		// 是否设置为 volatile不影响结果
		volatile boolean stopme = false;
		
		public void stopMe() {
			stopme = true;
		}
		
		@Override
		public void run() {
			while(true) {
				if (stopme) {
					break;
				}
				
				synchronized (user) {
					int v = (int)System.currentTimeMillis() / 1000;
					user.setId(v);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					user.setName(String.valueOf(v));
				}
				Thread.yield();
			}
		}
	}

	/**
	 * 读对象线程
	 * @author Gerry_Pang
	 */
	public static class ReadObjectThread extends Thread {
		@Override
		public void run() {
			while(true) {
				synchronized (user) {
					if (user.getId() != Integer.parseInt(user.getName())) {
						System.err.println(user.toString());
					} else {
						System.out.println(user.toString());
					}
				}
				Thread.yield();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new ReadObjectThread().start();
		while(true) {
			ChangeObjectThread changeObject = new ChangeObjectThread();
			changeObject.start();
			Thread.sleep(110);
			// 自定义停止线程方法
			changeObject.stopMe();
		}
	}
}
