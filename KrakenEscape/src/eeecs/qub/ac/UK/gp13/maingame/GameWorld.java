package eeecs.qub.ac.uk.gp13.maingame;

import eeecs.qub.ac.uk.gp13.Box;
import eeecs.qub.ac.uk.gp13.KrakenEscape;
import eeecs.qub.ac.uk.gp13.assetmanager.MyAssetManager;
import eeecs.qub.ac.uk.gp13.particle.ParticleEmitter;
import eeecs.qub.ac.uk.gp13.particle.ParticleSettings;
import eeecs.qub.ac.uk.gp13.particle.Vector2;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public class GameWorld {
	
	private MyAssetManager myAssetManager;
	
	private Bitmap subBitmap;
	private Matrix subMatrix = new Matrix();
	private int xSubLocation = 200;
	private int ySubLocation = 160;
	private float subScaleAmount = 1.0f;
	private int subLocX;
	private int subLocY;
	
	private Bitmap krakenBitmap;
	private Matrix krakenMatrix = new Matrix();
	private int xKrakenLocation = -120;
	private int yKrakenLocation = 60;
	private float krakenScaleAmount = 1.0f;
	
	private Box backgroundRibbon;
	private Bitmap ribbonBitmap;
	private Rect sourceRect = new Rect();
	private Rect screenRect = new Rect();

	private Box layerViewport;
	
	private float scrollSpeed = 2.0f;
	
	private ParticleEmitter smokeParticle;
	private Vector2 smokePlacement;
	private boolean subDamaged;
	private int difficulty;

	
	/**
	 * The constructor for the game world that gets called by the viewport
	 * 
	 * @param activity this is the current activity in android
	 */
	public GameWorld(Activity activity) {
		setupWorld(activity);
	}
	
	
	/**
	 * This is the method that actually creates the "game world"
	 * 
	 * @param activity this the current activity in android
	 */
	private void setupWorld(Activity activity) {
		layerViewport = new Box(500, 0, 1000, 1000);
		
		float ribbonWidth = layerViewport.width * 1.2f;
		
		backgroundRibbon = new Box(ribbonWidth / 2.0f, layerViewport.y,
				ribbonWidth, layerViewport.height);
		
		smokeParticle = new ParticleEmitter(activity, ParticleSettings.Smoke);
		subDamaged = false;
		
		KrakenEscape krakenEscape = (KrakenEscape) activity.getApplicationContext();
		myAssetManager = krakenEscape.GetMyAssetManager();
		
	    subBitmap = myAssetManager.LoadBitmap("submarine");
		ribbonBitmap = myAssetManager.LoadBitmap("seaBackground");
		krakenBitmap = myAssetManager.LoadBitmap("easykraken");
		
	}
	
	/**
	 * This is called by the control panel buttons and is used to set the scroll speed of the background
	 * 
	 * @param newSpeed this is the new speed that we want
	 */
	public void setScrollSpeed(float newSpeed) {
		scrollSpeed = newSpeed;
	}
	
	/**
	 * This is our custom update method that we use to update the background scroll and the particle system 
	 */
	public void update() {
		layerViewport.x += scrollSpeed;
		smokePlacement = new Vector2(subLocX, subLocY);
		smokeParticle.update(2, smokePlacement);
	}
	
	/**
	 * This is the GameWorld draw method that gets called and draws the UI components in their correct places
	 * 
	 * @param canvas This is the place where we want to draw all the components
	 * @param screenViewport the rectangle that we have created to display our UI components
	 */
	public void draw(Canvas canvas, Rect screenViewport) {
		
		drawBackgroundRibbon(canvas, screenViewport);
		
		//We reset and reuse the variables so we don't create and distroy vars many times a second
		subMatrix.reset();
		subMatrix.setScale(subScaleAmount, subScaleAmount);
		subMatrix.postTranslate(xSubLocation, ySubLocation);
		canvas.drawBitmap(subBitmap, subMatrix, null);
		
		krakenMatrix.reset();
		krakenMatrix.setScale(krakenScaleAmount, krakenScaleAmount);
		krakenMatrix.postTranslate(xKrakenLocation, yKrakenLocation);
		canvas.drawBitmap(krakenBitmap, krakenMatrix, null);
		
		if (isSubDamaged() == true){
			smokeParticle.draw(canvas, 10.0f);
			setDamaged(false);
		}
		
	}
	
	/**
	 * This works out how much of the ribbon is on the screen and how to draw it
	 * We have selected a repeating background so that when they get drawn side by side they match up
	 * 
	 * @param canvas the location to draw all the UI components
	 * @param screenViewport the rectangle that shows our viewport
	 */
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
	
	
	/**
	 * A method that sets up the screen rect variable that you pass in, this is done for speed, rather than create and return a new object
	 * 
	 * @param entityBound
	 * @param entityBitmapWidth
	 * @param entityBitmapHeight
	 * @param layerViewport
	 * @param screenViewport
	 * @param sourceRect
	 * @param screenRect
	 * @return if the variable update was done successfully
	 */
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
							((entityBound.x - entityBound.width  / 2) - (layerViewport.x - layerViewport.width / 2)));			
			
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

	/**
	 * This is a setter for the x location of the sub bitmap in the viewport
	 * 
	 * @param newLocation a new x location for the sub
	 */
	public void setXSubLocation(int newLocation) {
		xSubLocation = newLocation;
	}
	
	/**
	 * This is a setter for the y location of the sub bitmap in the viewport
	 * 
	 * @param newLocation new y location for the sub
	 */
	public void setYSubLocation(int newLocation) {
		ySubLocation = newLocation;
	}

	
	
	/**
	 * This is a setter to show when the sub is damaged if the player gets a command wrong
	 * 
	 * @param n - true/false
	 */
	public void setDamaged(boolean n){
		subDamaged = n;
	}

	/**
	 * For updating the subs size in the viewport
	 * 
	 * @param the new size of the sub
	 */
	public void setSubScale(float amount) {
		subScaleAmount = amount;
	}
	
	/**
	 * Sets difficulty in gameworld to affect images
	 * 
	 * @param i - 0 or 1
	 */
	public void setDifficulty(int i){
		difficulty = i;
	}
	
	/**
	 * Sets images shown on screen depending on difficulty
	 */
	public void setKrakenPic(){
		if (difficulty == 1) {
			subBitmap = myAssetManager.LoadBitmap("submarinehard");
			krakenBitmap = myAssetManager.LoadBitmap("hardkraken");
			xKrakenLocation = 0;
			yKrakenLocation = 60;
			krakenScaleAmount = 2.0f;
		} else {
			subBitmap = myAssetManager.LoadBitmap("submarine");
			krakenBitmap = myAssetManager.LoadBitmap("easykraken");
			xKrakenLocation = -120;
			yKrakenLocation = 60;
			krakenScaleAmount = 1.0f;
		}
	}
	
	/**
	 * Sets up eastereggs
	 */
	public void setEasterEgg(){
		subBitmap = myAssetManager.LoadBitmap("easter");
	}
	
	//Getters
	/**
	 * @return
	 * returns if the sub is damaged
	 */
	public boolean isSubDamaged(){
		return subDamaged;
	}
		
	/**
	 * @return
	 * returns difficulty selected
	 */
	public int getDifficulty(){
		return difficulty;
	}

}