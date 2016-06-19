package eeecs.qub.ac.uk.gp13;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class GameWorld {
	
	//The screen rect is incorrect so the sub doesnt get drawn correctly, I dont know how to fix this atm
	//However we wont be using the scrolling sub picture, We will be altering it ourself
	
	
	private final int numSub = 100;
	private Box sub[] = new Box[numSub];
	private Bitmap subBitmap;
	
	private Box backgroundRibbon;
	private Bitmap ribbonBitmap;
	
	private Box layerViewport;
	
	private Rect sourceRect = new Rect();
	private Rect screenRect = new Rect();

	float scrollSpeed = 10.0f;
	
	public GameWorld(Activity activity) {
		setupWorld(activity);
	}
	
	private void setupWorld(Activity activity) {
		layerViewport = new Box(0, 0, 1000, 1000);
		
		float ribbonWidth = layerViewport.width * 1.2f;
		backgroundRibbon = new Box(ribbonWidth / 2.0f, layerViewport.y,
				ribbonWidth, layerViewport.height);

		//I will update this eventually
		
//		myAssetManager.StoreAsset("img/sub.png", "submarine");
//		myAssetManager.StoreAsset("img/sea.png", "seaside");
//		
//		subBitmap = myAssetManager.LoadBitmap("submarine");
//		ribbonBitmap = myAssetManager.LoadBitmap("seaside");
		
		try {
			AssetManager assetManager = activity.getAssets();
			
			InputStream inputStream = assetManager.open("img/sub.png");
			subBitmap = BitmapFactory.decodeStream(inputStream);
			inputStream.close();
			
			inputStream = assetManager.open("img/sea.png");
			ribbonBitmap = BitmapFactory.decodeStream(inputStream);
			inputStream.close();
		}
		catch (IOException e) {
			
		}
		Random random = new Random();
		for (int idx = 0; idx < numSub; idx++) {
			int x = (subBitmap.getWidth() + 100) * idx;
			int y = random.nextInt(1000);
			sub[idx] = new Box(x, y, subBitmap.getWidth(),
					subBitmap.getHeight());
		}
	}
	
	public void setScrollSpeed(float newSpeed) {
		scrollSpeed = newSpeed;
	}
	
	public void update() {
		layerViewport.x += scrollSpeed;
	}
	
	public void draw(Canvas canvas, Rect screenViewport) {
		
		drawBackgroundRibbon(canvas, screenViewport);
		
		for(int idx = 0; idx < numSub; idx++) {
			
			if (getSourceAndScreenRect(sub[idx], subBitmap.getWidth(),
					subBitmap.getHeight(), layerViewport,
					screenViewport, sourceRect, screenRect)) {
				canvas.drawBitmap(subBitmap, sourceRect, screenRect, null);
			}
		}
	}
	
	private void drawBackgroundRibbon(Canvas canvas, Rect screenViewport) {
		
		int ribbonInset = (int) (layerViewport.x - layerViewport.width /2)
				/ (int) backgroundRibbon.width;
		
		backgroundRibbon.x = ribbonInset * backgroundRibbon.width
				+ backgroundRibbon.width / 2.0f;
		
		getSourceAndScreenRect(backgroundRibbon, ribbonBitmap.getWidth(), ribbonBitmap.getHeight(), layerViewport, screenViewport, sourceRect, screenRect);
		
		canvas.drawBitmap(ribbonBitmap, sourceRect, screenRect, null);
		
		if (backgroundRibbon.x + backgroundRibbon.width / 2 < layerViewport.x
				+ layerViewport.width / 2) {
			backgroundRibbon.x += backgroundRibbon.width;
			
			getSourceAndScreenRect(backgroundRibbon, ribbonBitmap.getWidth(), ribbonBitmap.getHeight(), layerViewport, screenViewport, sourceRect, screenRect);
			
			canvas.drawBitmap(ribbonBitmap, sourceRect, screenRect, null);
		}
	}
	
	private boolean getSourceAndScreenRect(Box entityBound, int entityBitmapWidth, 
			int entityBitmapHeight, Box layerViewport, Rect screenViewport,
			Rect sourceRect, Rect screenRect) {

		if (entityBound.x - entityBound.width / 2 < layerViewport.x
					+ layerViewport.width/2
				&& entityBound.x + entityBound.width /2 > layerViewport.x
					- layerViewport.width /2
				&& entityBound.y - entityBound.height / 2 < layerViewport.y
					+ layerViewport.height / 2
				&& entityBound.y + entityBound.height / 2 > layerViewport.y
					- layerViewport.height / 2) {
			
			float sourceX = Math.max(0.0f, 
					(layerViewport.x - layerViewport.width / 2)
						- (entityBound.x - entityBound.width / 2));
			
			float sourceY = Math.max(0.0f, 
					(entityBound.y + entityBound.height /2)
						- (layerViewport.y + layerViewport.height/2));
			
			float sourceWidth = ((entityBound.width - sourceX) - Math.max(
					0.0f, (entityBound.x + entityBound.width /2 )
						- (layerViewport.x + layerViewport.width /2)));
			
			float sourceHeight = ((entityBound.height - sourceY) - Math.max(
					0.0f, (layerViewport.y + layerViewport.height /2 )
					- (entityBound.y + entityBound.height / 2)));
		
			
			float sourceScaleWidth = (float) entityBitmapWidth
					/ (float) entityBound.width;
			
			float sourceScaleHeight = (float) entityBitmapHeight
					/ (float) entityBound.height;
			
			sourceRect.set((int) (sourceX * sourceScaleWidth),
					(int) (sourceY * sourceScaleHeight),
					(int) ((sourceX + sourceWidth) * sourceScaleWidth),
					(int) ((sourceY + sourceHeight) * sourceScaleHeight));
			
			float screenXScale = (float) screenViewport.width()
					/ (float) layerViewport.width;
			float screenYScale = (float) screenViewport.height()
					/ (float) layerViewport.height;
			
			float screenX = screenViewport.left
					+ screenXScale
					* Math.max(0.0f,
							((entityBound.x - entityBound.width /2) -(layerViewport.x - layerViewport.width /2)));
			
			float screenY = screenViewport.top
					+ screenYScale
					* Math.max(0.0f,
							((layerViewport.y + layerViewport.height / 2) - (entityBound.y + entityBound.height /2)));
			
			screenRect.set((int) screenX, (int) screenY,
				(int) (screenX + sourceWidth * screenXScale),
				(int) (screenY + sourceHeight * screenYScale));
			
			return true;
		}
		
		return false;
	}
}