package eeecs.qub.ac.uk.gp13;

import android.os.Handler;
import android.os.Looper;

public class Utils{
	
	public static void runOnUiThread(Runnable runnable) {
		final Handler UIHandler = new Handler(Looper.getMainLooper());
		UIHandler.post(runnable);
	}
	
}
