package com.app.examples;

public class MyRunnable implements Runnable {

	private int duration;

	public MyRunnable(int duration) {
		this.duration = duration;
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		long endTime = startTime + duration * 1000;
		while(System.currentTimeMillis() <= endTime){
			System.out.println("...");
		}
		System.out.println("Duration: " + duration);
	}

}
