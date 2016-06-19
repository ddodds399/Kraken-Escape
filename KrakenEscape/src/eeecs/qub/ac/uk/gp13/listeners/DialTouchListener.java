package eeecs.qub.ac.uk.gp13.listeners;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import eeecs.qub.ac.uk.gp13.MainGameFragment;
import eeecs.qub.ac.uk.gp13.MyAssetManager;
import eeecs.qub.ac.uk.gp13.controlobjects.DialObject;
import eeecs.qub.ac.uk.gp13.controlobjects.PolarConverter;

@SuppressLint("ClickableViewAccessibility")
public class DialTouchListener implements OnTouchListener
{
	private int prevPos;
	private Vibrator vib;
	private DialObject dial;
	private ImageView dialV;
	private MyAssetManager myAssetManager;
	
	public DialTouchListener(Vibrator iVib, DialObject iDial, ImageView iDialV, MyAssetManager iMyAssetManager)
	{
		vib = iVib;
		dial = iDial;
		dialV = iDialV;
		myAssetManager = iMyAssetManager;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		prevPos = dial.getValueSelected();
		double x = event.getX();
		double y = event.getY();
		
		double currentAngle = PolarConverter.polarConverterAngle(x, y, dialV);
		
		dial.findClosestPosition(currentAngle);
		if (dial.getValueSelected() != prevPos)
		{
			vib.vibrate(100);
			prevPos = dial.getValueSelected();
			
		}
		if (MainGameFragment.getCurrentCommand().getControlType() == dial && prevPos == MainGameFragment.getCurrentCommand().getCompleteCondition())
		{
			MainGameFragment.setcCorrect(true);
		}
		
		Bitmap changeDisplay = myAssetManager.LoadBitmap(dial.getPicLoc());
		dialV.setImageBitmap(changeDisplay);
		
		
		return true;
	}
	
}
