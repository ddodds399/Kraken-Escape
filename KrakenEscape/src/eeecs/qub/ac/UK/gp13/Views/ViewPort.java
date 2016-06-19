package eeecs.qub.ac.uk.gp13.views;

import eeecs.qub.ac.uk.gp13.maingame.GameWorld;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class ViewPort extends View {
	
	private Rect screenViewport;
	private GameWorld gameWorld;
	private DisplayMetrics metrics;

	
	/**
	 * ViewPort constructor that gets called in XML when the main game view is inflated 
	 * 
	 * @param context this is the current context of the game so that I can get a handle on the Activity
	 * @param attrs this is a variable that is needed when view is extended to pass to the constructor of the super class (view)
	 */
	public ViewPort(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		metrics = new DisplayMetrics();
		
		//Create an activity object from the current context
		Activity activity = (Activity)context;
		
		//init the metrics object
		activity.getWindowManager().getDefaultDisplay()
			.getMetrics(metrics);
		
		//Create an instance of the game world
		gameWorld = new GameWorld(activity);
		
		//This should be the position on the screen
		
		screenViewport = new Rect(0, 0, metrics.widthPixels, metrics.heightPixels);
	}
	
	/* (non-Javadoc)
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 * 
	 * This is the draw method that gets called by the view
	 */
	@Override
	protected void onDraw(Canvas canvas) {
        
		gameWorld.draw(canvas, screenViewport);
		
		invalidate();
	}
	
	/**
	 * A getter for the viewport
	 * 
	 * @return the screen viewport 
	 */
	public Rect getScreenViewPort() {
		return screenViewport;
	}
	
	/**
	 * This is used to get a handle on the game world as this class creates the gameworld and we need to access it to update it
	 * 
	 * @return the game world
	 */
	public GameWorld getGameWorld() {
		return gameWorld;
	}
	
	/* (non-Javadoc)
	 * @see android.view.View#onMeasure(int, int)
	 * 
	 * This sets the view to a third of the overall screen
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    setMeasuredDimension(metrics.widthPixels, metrics.heightPixels/3);
	}
}	