package com.app.examples;

public class Main {
	public static void main(String[] args) {
		// Runnable task = () -> {
		// String threadName = Thread.currentThread().getName();
		// System.out.println("Hello " + threadName);
		// };

		// task.run();

		// Thread thread = new Thread(task);
		// thread.start();

		// System.out.println("Done!");

		// -----

		// MyRunnable myRunnable = new MyRunnable(5);
		// Thread t = new Thread(myRunnable);
		// t.start();

		Runnable r = new Runnable() {
			public void run() {

				long startTime = System.currentTimeMillis();
				long endTime = startTime + 5000;
//				while (System.currentTimeMillis() <= endTime) {
//					System.out.println("...");
//				}
				int i = 0;
				while (!Thread.currentThread().isInterrupted()) {
					i++;
					System.out.println(i + "...");
					if (System.currentTimeMillis() > endTime) {
						System.out.println("Done!");
						Thread.currentThread().interrupt();
					}
				}
			}
		};
		Thread thr = new Thread(r);
		thr.start();
	}
}
