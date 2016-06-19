package eeecs.qub.ac.uk.gp13;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class MyAssetManager {
	
	Map <String, byte[]> assets;
	AssetManager androidAssetManager;
	
	public MyAssetManager(AssetManager assetManager) {
		assets = new HashMap<String, byte[]>();
		androidAssetManager = assetManager;
	}
	
	public void StoreAssetList(String[] assets, String[] names) {
		//Needa do this!
	}
	
	public void StoreAsset(String fileLocation, String fileName) {
		InputStream inputStream = null;
		byte[] byteArray = null;
		
		try {
			inputStream = androidAssetManager.open(fileLocation);
			
			byteArray = new byte[inputStream.available()];
			
			inputStream.read(byteArray);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			inputStream.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		assets.put(fileName, byteArray);
	}
	
	public String ListAssets() {
		String assetString = "";
		int i = 0;
		
		for(String key: assets.keySet()) {
			assetString += "Asset " + i + " " + key;
		}
		
		return assetString;
	}
	
//	public boolean checkExists(String assetName) {
//		try {
//			assets.get(assetName);
//			return true;
//		}
//		catch (Exception e){
//			return false;
//		}
//	}
	
	public Bitmap LoadBitmap(String imageName) {
		Bitmap bitmap = null;
		
		try {
			byte[] imageByteArray = assets.get(imageName);
		
		
			bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
		}
		catch (Exception e) {
			Log.d("debug", "There is no bitmap in the asset manager with that name");
			Log.d("debug", e.toString());
		}
		
		return bitmap;
	}
	
	public String LoadText(String textName) {
		String text = null;
		
		try {
			
			byte[] textByteArray = assets.get(textName);

			text = new String(textByteArray);
		} 
		catch (Exception e) {
			Log.d("debug", "There is no text in the asset manager with that name");
			Log.d("debug", e.toString());
		}
		
		return text;
	}
}
