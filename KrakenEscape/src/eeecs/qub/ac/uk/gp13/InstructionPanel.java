package eeecs.qub.ac.uk.gp13;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class InstructionPanel extends TextView{

	
	String fontName;
	Typeface fontFamily;
	//super constructor for TextView
	public InstructionPanel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//calling method where style is set
		initialise(attrs);
		
	}
	
	public InstructionPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialise(attrs);
		
	}
	
	public InstructionPanel(Context context) {
		super(context);
		initialise(null);
	}
	
	private void initialise(AttributeSet attrs)
	{
		TypedArray fonts = getContext().obtainStyledAttributes(attrs, R.styleable.instructionPanel);
		fontName = fonts.getString(R.styleable.instructionPanel_fontName);
		//Possible need to implement our own custom AssetManager(Connor) as first arg for following line
		fontFamily = Typeface.createFromAsset(getContext().getAssets(), "fonts/"+fontName);
		setTypeface(fontFamily);
		fonts.recycle();
	}

}
