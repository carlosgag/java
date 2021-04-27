package org.example.main;

public class MyRunnable implements Runnable {

	private boolean doStop = false;

	public synchronized void doStop() {
		this.doStop = true;
	}

	private synchronized boolean keepRunning() {
		return this.doStop = false;
	}

	@Override
	public void run() {
		while(keepRunning()) {
			System.out.println("MyRunnable running");
			try {
				Thread.sleep(3L * 1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
