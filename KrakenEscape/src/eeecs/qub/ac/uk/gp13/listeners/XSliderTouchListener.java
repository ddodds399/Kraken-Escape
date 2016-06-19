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
import eeecs.qub.ac.uk.gp13.controlobjects.XSliderObject;

@SuppressLint("ClickableViewAccessibility")
public class XSliderTouchListener implements OnTouchListener
{
	private int prevPos;
	private Vibrator vib;
	private XSliderObject xSlider;
	private ImageView xSliderV;
	private MyAssetManager myAssetManager;
	
	public XSliderTouchListener(Vibrator iVib, XSliderObject iXSlider, ImageView iXSliderV, MyAssetManager iMyAssetManager)
	{
		vib = iVib;
		xSlider = iXSlider;
		xSliderV = iXSliderV;
		myAssetManager = iMyAssetManager;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		prevPos = xSlider.getValueSelected();
		double x = event.getX();
		
		xSlider.findClosestPosition(x);
		if (xSlider.getValueSelected() != prevPos)
		{
			vib.vibrate(100);
			prevPos = xSlider.getValueSelected();
			
		}
		if (MainGameFragment.getCurrentCommand().getControlType() == xSlider && prevPos == MainGameFragment.getCurrentCommand().getCompleteCondition())
		{
			MainGameFragment.setcCorrect(true);
		}
		
		Bitmap changeDisplay = myAssetManager.LoadBitmap(xSlider.getPicLoc());
		xSliderV.setImageBitmap(changeDisplay);
		
		return true;
	}
}
