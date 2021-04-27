package org.example.main;

public class Main {

	// java -cp target/concurrency-1.0-SNAPSHOT.jar org.example.main.Main
	public static void  main(String [] args) {
		// --
		Thread thread = new Thread();
		thread.start();
		// --
		MyThread myThread = new MyThread();
		myThread.start();
		// --
		Thread anonymousThread = new Thread() {
			public void run() {
				System.out.println("Thread Running");
			}
		};
		anonymousThread.start();
		// --
		MyRunnable myRunnable = new MyRunnable();
		myRunnable.run();
		// --
		Runnable myAnonymousRunnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("Anonymous runnable running");
			}
		};
		myAnonymousRunnable.run();
		// --
		Runnable runnable = () -> {
			System.out.println("Lambda Runnable running");
		};
		// --
		Thread threadWithRunnable = new Thread(runnable);
		threadWithRunnable.start();

		//
		Thread thread1 = new Thread("New Thread") {
			public void run() {
				System.out.println("run by: " + getName());
			}
		};
		thread1.start();
		System.out.println(thread1.getName());

		//
		MyRunnable runnable1 = new MyRunnable();
		Thread thread2 = new Thread(runnable1, "New Thread with runnable");
		thread2.start();
		System.out.println(thread2.getName());

		//
		Thread currentThread = Thread.currentThread();
		String threadName = currentThread.getName();
		System.out.println(threadName);

		//
		Main main = new Main();
		main.threadExample(20);
		main.pauseThread();
		main.runnableWithStop();
	}

	public void threadExample(int cant) {
		System.out.println(Thread.currentThread().getName());
		for(int i = 1;i<= cant;i++) {
			new Thread(String.valueOf(i)) {
				public void run() {
					System.out.println("Thread: " + getName() + " running");
				}
			}.start();
		}
	}

	public void pauseThread() {
		try {
			Thread.sleep(10L * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void runnableWithStop() {
		MyRunnable myRunnable = new MyRunnable();
		Thread thread = new Thread(myRunnable);
		thread.start();
		try {
			Thread.sleep(10L * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		myRunnable.doStop();
	}
}
