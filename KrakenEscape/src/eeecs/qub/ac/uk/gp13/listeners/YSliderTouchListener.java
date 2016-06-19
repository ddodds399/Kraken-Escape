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
import eeecs.qub.ac.uk.gp13.controlobjects.YSliderObject;

@SuppressLint("ClickableViewAccessibility")
public class YSliderTouchListener implements OnTouchListener
{
	private int prevPos;
	private Vibrator vib;
	private YSliderObject ySlider;
	private ImageView ySliderV;
	private MyAssetManager myAssetManager;
	
	public YSliderTouchListener(Vibrator iVib, YSliderObject iYSlider, ImageView iYSliderV, MyAssetManager iMyAssetManager)
	{
		vib = iVib;
		ySlider = iYSlider;
		ySliderV = iYSliderV;
		myAssetManager = iMyAssetManager;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		prevPos = ySlider.getValueSelected();
		double x = event.getY();
		
		ySlider.findClosestPosition(ySliderV.getHeight() - x);
		
		if (ySlider.getValueSelected() != prevPos)
		{
			vib.vibrate(100);
			prevPos = ySlider.getValueSelected();
		}
		if (MainGameFragment.getCurrentCommand().getControlType() == ySlider && prevPos == MainGameFragment.getCurrentCommand().getCompleteCondition())
		{
			MainGameFragment.setcCorrect(true);
		}
		
		Bitmap changeDisplay = myAssetManager.LoadBitmap(ySlider.getPicLoc());
		ySliderV.setImageBitmap(changeDisplay);
		
		//text.setText(button.getLabel() + " - " + button.getActivated() + "\n" + flipSwitch.getLabel() + " - " + flipSwitch.getActivated() + "\n" + slidingSwitch.getLabel() + " - " + slidingSwitch.getActivated() + "\n" + slider.getLabel() + " - " + slider.getValueSelected() + "\n" + dial.getLabel() + " - " + dial.getValueSelected());
		
		return true;
	}
}
