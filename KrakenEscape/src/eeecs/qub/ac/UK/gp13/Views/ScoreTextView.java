package eeecs.qub.ac.uk.gp13.views;

import eeecs.qub.ac.uk.gp13.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class ScoreTextView extends TextView{

	
	String fontName;
	Typeface fontFamily;
	//super constructor for TextView
	public ScoreTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//calling method where style is set
		initialise(attrs);
		
	}
	
	public ScoreTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialise(attrs);
		
	}
	
	public ScoreTextView(Context context) {
		super(context);
		initialise(null);
	}
	
	private void initialise(AttributeSet attrs)
	{
		TypedArray fonts = getContext().obtainStyledAttributes(attrs, R.styleable.customTextView);
		fontName = fonts.getString(R.styleable.customTextView_fontName);
		fontFamily = Typeface.createFromAsset(getContext().getAssets(), "fonts/"+fontName);
		setTypeface(fontFamily);
		fonts.recycle();
	}

}
