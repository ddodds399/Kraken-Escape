package eeecs.qub.ac.uk.gp13.utils;

import android.os.Handler;
import android.os.Looper;

public class Utils{
	
	/**
	 * This method passes a runnable to the UI thread to get handled there so if the GUI needs updated we can just call this
	 * If you try to do an update of the GUI when not on the UI thread the app will crash
	 * 
	 * @param runnable this is the aspect of the game you want to pass to the UI thread
	 */
	public static void runOnUiThread(Runnable runnable) {
		final Handler UIHandler = new Handler(Looper.getMainLooper());
		UIHandler.post(runnable);
	}
	
}
