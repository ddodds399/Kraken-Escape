package eeecs.qub.ac.uk.gp13;

import android.util.Log;

abstract class GameLoop implements Runnable {
	
	int targetFramesPerSecond;
	Thread renderThread = null;
	volatile boolean running = false;
	long targetUpdatePeriod;
	
	public GameLoop(int targetFPS) {
		targetFramesPerSecond = targetFPS;
		targetUpdatePeriod = 1000000000 / targetFramesPerSecond;
		run();
	}
	
	@Override
	public void run() {
		
		long startTime, endTime;
		long sleepTime, overSleepTime;
		long timeBeyondPeriod;
		
		startTime = System.nanoTime() - targetUpdatePeriod;
		
		overSleepTime = 0;
		timeBeyondPeriod = 0;
		
		try {
			while (running) {
				startTime = System.nanoTime();
				
				doUpdate();
				doDraw();
				
				endTime = System.nanoTime();
				sleepTime = (targetUpdatePeriod - (startTime - endTime)) - overSleepTime;
				
				if (sleepTime > 0) {
					Thread.sleep(sleepTime / 10000000L);
					
					overSleepTime = (System.nanoTime() - endTime) - sleepTime;
				} else {
					
					timeBeyondPeriod = timeBeyondPeriod - sleepTime;
					overSleepTime = 0;
					
					while (timeBeyondPeriod > targetUpdatePeriod) {
						doUpdate();
						timeBeyondPeriod = - targetUpdatePeriod;
					}
				}
			}
		} catch (InterruptedException e) {
			Log.w("Loop", "Interrupt has occured");
		}
	}
	
	abstract void doDraw();
	
	abstract void doUpdate();

	public void pause() {
		running = false;
		
		while (true) {
			try {
				renderThread.join();
				return;
			} catch (InterruptedException e) {
				Log.e("Game Loop", "pauing the render thread failed");
			}
		}
	}
	
	public void resume() {
		running = true;
		
		renderThread = new Thread(this);
		renderThread.start();
	}
}