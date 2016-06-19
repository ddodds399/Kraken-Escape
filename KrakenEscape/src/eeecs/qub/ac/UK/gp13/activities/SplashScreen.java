package eeecs.qub.ac.uk.gp13.activities;

import eeecs.qub.ac.uk.gp13.KrakenEscape;
import eeecs.qub.ac.uk.gp13.R;
import eeecs.qub.ac.uk.gp13.assetmanager.MyAssetManager;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {

	private MyAssetManager myAssetManager;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * 
	 * This is called when the activity is created
	 * It creates an instance of the asset manager to load in assets
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Window window = getWindow();
		window.requestFeature(Window.FEATURE_NO_TITLE);
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		setContentView(R.layout.layout_splash);
		
		//This sets our asset manager so that I can get access to it and store our assets in the loading screen
		AssetManager assetManager = getAssets();
		KrakenEscape krakenEscape = ((KrakenEscape)getApplicationContext());
		krakenEscape.SetAssetManager(assetManager);
		myAssetManager = krakenEscape.GetMyAssetManager();
		
		Thread logoTimer = new Thread() {
			public void run() {
				try {
					myAssetManager.StoreAsset("img/sub.png", "submarine");
					myAssetManager.StoreAsset("img/subhard.png", "submarinehard");
					myAssetManager.StoreAsset("img/easteregg.png", "easter");
				    myAssetManager.StoreAsset("img/repeat-sea.jpg", "seaBackground");
					myAssetManager.StoreAsset("img/krak.png", "hardkraken");
				    myAssetManager.StoreAsset("img/kraken.png", "easykraken");

					startActivity(new Intent(SplashScreen.this, MainActivity.class));
				}
				catch (Exception e) {
					Log.e("SplashTime", "Error with loading");
				} finally {
					finish();
				}
			}	
		};	
		logoTimer.start();	
	}
}
