package eeecs.qub.ac.uk.gp13.listeners;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import eeecs.qub.ac.uk.gp13.R;
import eeecs.qub.ac.uk.gp13.SoundFX;
import eeecs.qub.ac.uk.gp13.assetmanager.MyAssetManager;
import eeecs.qub.ac.uk.gp13.controlobjects.XSliderObject;
import eeecs.qub.ac.uk.gp13.fragments.MainGameFragment;
import eeecs.qub.ac.uk.gp13.views.ScoreTextView;
import eeecs.qub.ac.uk.gp13.views.ViewPort;

/**
 * 
 * The XSliderTouchListener class implements OnTouchListener
 * This class should be used in conjunction with a XSliderObject
 * This class will listen to any user input relating to the xSlider object and will update the object accordingly
 *
 */
@SuppressLint("ClickableViewAccessibility")
public class XSliderTouchListener implements OnTouchListener
{
	private int prevPos;
	private Vibrator vib;
	private XSliderObject xSlider;
	private ImageView xSliderV;
	private MyAssetManager myAssetManager;
	private ViewPort viewPort;
	private SoundFX slideFX;
	private Context context;
	private ScoreTextView yawView;
	
	public XSliderTouchListener(Vibrator iVib, XSliderObject iXSlider, ImageView iXSliderV, MyAssetManager iMyAssetManager, ViewPort iViewPort, Context iContext, ScoreTextView iYawView)
	{
		vib = iVib;
		xSlider = iXSlider;
		xSliderV = iXSliderV;
		myAssetManager = iMyAssetManager;
		viewPort = iViewPort;
		context = iContext;
		yawView = iYawView;
		
		try {
			slideFX = new SoundFX(context, R.raw.slider);
		} catch (IOException e) {
			Log.e("SlideFX", "X Start FX could not be loaded");
		}
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		
		if (!MainGameFragment.isPaused()){
			
		prevPos = xSlider.getValueSelected();
		double x = event.getX();
		xSlider.findClosestPosition(x);
		if (xSlider.getValueSelected() != prevPos)
		{
			vib.vibrate(100);
			prevPos = xSlider.getValueSelected();
			slideFX.playSoundFX();
			
		}
		if (event.getAction() == android.view.MotionEvent.ACTION_UP){
			slideFX.clearFX();
			if (!slideFX.hasPlayed()){
				try {
					slideFX = new SoundFX(context, R.raw.slider);
				} catch (IOException e) {
					Log.e("SlideFX", "X Start FX could not be loaded");
				}
			}
		if (MainGameFragment.getCurrentCommand().getControlType() == xSlider && prevPos == MainGameFragment.getCurrentCommand().getCompleteCondition())
		{
			MainGameFragment.setcCorrect(true);
		}}
		
		Bitmap changeDisplay = myAssetManager.LoadBitmap(xSlider.getPicLoc());
		xSliderV.setImageBitmap(changeDisplay);
		
		//Gets a handle of the game world and sets the size of the sub bitmap
		switch (xSlider.getValueSelected()) {
		case 1:
			viewPort.getGameWorld().setSubScale(0.6f);
			yawView.setText("H-Port");
			break;
		case 2:
			viewPort.getGameWorld().setSubScale(0.7f);
			yawView.setText("Port");
			break;
		case 3:
			viewPort.getGameWorld().setSubScale(0.8f);
			yawView.setText("Ahead");
			break;
		case 4:
			viewPort.getGameWorld().setSubScale(0.9f);
			yawView.setText("*board");
			break;
		case 5:
			viewPort.getGameWorld().setSubScale(1.0f);
			yawView.setText("H-*board");
			break;
		}
		}
		return true;
	}
}
