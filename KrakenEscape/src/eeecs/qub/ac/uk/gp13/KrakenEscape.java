package eeecs.qub.ac.uk.gp13;

import eeecs.qub.ac.uk.gp13.assetmanager.MyAssetManager;
import android.app.Application;
import android.content.res.AssetManager;

public class KrakenEscape extends Application {
	
	MyAssetManager myAssetManager;
	
	/**
	 * This is used to get a handle on our custom asset manager from any place in the app so that we can have one
	 * central location for all our assets
	 * 
	 * @return the custom asset manager
	 */
	public MyAssetManager GetMyAssetManager() {
		return myAssetManager;
	}	
	
	/**
	 * This method is only called once and is used to set our custom asset manager so that we can use it to store everything
	 * 
	 * @param assetManager this is the android asset manager
	 */
	public void SetAssetManager(AssetManager assetManager) {
		myAssetManager = new MyAssetManager(assetManager);
	}
}
