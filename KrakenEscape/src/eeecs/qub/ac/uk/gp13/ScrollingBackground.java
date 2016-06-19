package eeecs.qub.ac.uk.gp13;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class ScrollingBackground extends View {

	Bitmap bg;
	public ScrollingBackground(Context context, AttributeSet attrs) {
		super(context, attrs);
		bg = BitmapFactory.decodeResource(getResources(),
                R.drawable.canvasbg);
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		setBackgroundColor(Color.CYAN);
		
	}
}

