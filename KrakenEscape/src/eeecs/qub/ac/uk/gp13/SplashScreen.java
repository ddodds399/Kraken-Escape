package eeecs.qub.ac.uk.gp13;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Window window = getWindow();
		window.requestFeature(Window.FEATURE_NO_TITLE);
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		setContentView(R.layout.layout_splash);
		
		//Need to set up ASync task here for actually loading instead of a timer
		
		Thread logoTimer = new Thread() {
			public void run() {
				try {
					int logoTimer = 0;
					while (logoTimer < 3000) {
						sleep(100);
						logoTimer = logoTimer + 100;
					};
					startActivity(new Intent(SplashScreen.this, MainActivity.class));
				}
				catch (InterruptedException e) {
					Log.e("SplashTime", "Timer interrupted");
				} finally {
					finish();
				}
			}	
		};	
		logoTimer.start();	
	}
}
