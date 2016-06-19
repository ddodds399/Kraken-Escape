package eeecs.qub.ac.uk.gp13;

import android.app.Application;
import android.content.res.AssetManager;

public class KrakenEscape extends Application {
	
	MyAssetManager myAssetManager;
	
	public MyAssetManager GetMyAssetManager() {
		return myAssetManager;
	}	
	
	public void SetAssetManager(AssetManager assetManager) {
		myAssetManager = new MyAssetManager(assetManager);
	}
}
