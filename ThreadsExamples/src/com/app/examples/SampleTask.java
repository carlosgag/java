package com.app.examples;

import java.util.TimerTask;

public class SampleTask extends TimerTask {
	Thread myThreadObj;
	SampleTask(Thread t){
		this.myThreadObj = t;
	}
	@Override
	public void run() {
		myThreadObj.start();
	}

}
