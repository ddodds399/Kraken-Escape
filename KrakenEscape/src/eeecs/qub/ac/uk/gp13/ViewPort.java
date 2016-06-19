package eeecs.qub.ac.uk.gp13;

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

	//This is the constructor that is used when an object is created from the XML document
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
		screenViewport = new Rect(0, 0, metrics.widthPixels, metrics.heightPixels/3);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
        
		gameWorld.draw(canvas, screenViewport);
		
		invalidate();
	}
	
	public Rect getScreenViewPort() {
		return screenViewport;
	}
	
	public GameWorld getGameWorld() {
		return gameWorld;
	}
	
	//This sets the view to a third of the overall screen
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    setMeasuredDimension(metrics.widthPixels, metrics.heightPixels/3);
	}
}	