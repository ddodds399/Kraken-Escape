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
import eeecs.qub.ac.uk.gp13.calculations.PolarConverter;
import eeecs.qub.ac.uk.gp13.controlobjects.DialObject;
import eeecs.qub.ac.uk.gp13.fragments.MainGameFragment;
import eeecs.qub.ac.uk.gp13.views.ScoreTextView;
import eeecs.qub.ac.uk.gp13.views.ViewPort;

/**
 * 
 * The DialTouchListener class implements OnTouchListener
 * This class should be used in conjunction with a DialObject object
 * This class will listen for any user input relating to the dial and will update the dial accordingly
 * 
 */
@SuppressLint("ClickableViewAccessibility")
public class DialTouchListener implements OnTouchListener
{
	private int prevPos;
	private Vibrator vib;
	private DialObject dial;
	private ImageView dialV;
	private MyAssetManager myAssetManager;
	private ViewPort viewPort;
	private SoundFX dialFX;
	private Context context;
	private ScoreTextView speedView;
	
	public DialTouchListener(Vibrator iVib, DialObject iDial, ImageView iDialV, MyAssetManager iMyAssetManager,ViewPort iViewPort, Context iContext, ScoreTextView iSpeedView)
	{
		vib = iVib;
		dial = iDial;
		dialV = iDialV;
		myAssetManager = iMyAssetManager;
		viewPort = iViewPort;
		context = iContext;
		speedView = iSpeedView;
		
		try {
			dialFX = new SoundFX(context, R.raw.dialfx);
		} catch (IOException e) {
			Log.e("DialFX", "Dial FX could not be loaded");
		}
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		if (!MainGameFragment.isPaused()){
		prevPos = dial.getValueSelected();
		double x = event.getX();
		double y = event.getY();
		
		double currentAngle = PolarConverter.polarConverterAngle(x, y, dialV);
		
		dial.findClosestPosition(currentAngle);
		if (dial.getValueSelected() != prevPos)
		{
			vib.vibrate(100);
			prevPos = dial.getValueSelected();
			dialFX.playSoundFX();
			Bitmap changeDisplay = myAssetManager.LoadBitmap(dial.getPicLoc());
			dialV.setImageBitmap(changeDisplay);
			
		}
		if (event.getAction() == android.view.MotionEvent.ACTION_UP){
			dialFX.clearFX();
			if (!dialFX.hasPlayed()){
				try {
					dialFX = new SoundFX(context, R.raw.dialfx);
				} catch (IOException e) {
					Log.e("DialFX", "Dial FX could not be loaded");
				}
			}
		if (MainGameFragment.getCurrentCommand().getControlType() == dial && prevPos == MainGameFragment.getCurrentCommand().getCompleteCondition())
		{
			MainGameFragment.setcCorrect(true);
		
		}}
		
		//Get a handle on the game world and set the background scrolling speed
		switch (dial.getValueSelected()) {
		case 1:
			viewPort.getGameWorld().setScrollSpeed(2.0f);
			speedView.setText("10 kn");
			break;
		case 2:
			viewPort.getGameWorld().setScrollSpeed(4.0f);
			speedView.setText("12 kn");
			break;
		case 3:
			viewPort.getGameWorld().setScrollSpeed(6.0f);
			speedView.setText("14 kn");
			break;
		case 4: 
			viewPort.getGameWorld().setScrollSpeed(8.0f);
			speedView.setText("16 kn");
			break;
		case 5:
			viewPort.getGameWorld().setScrollSpeed(10.0f);
			speedView.setText("18 kn");
			break;
		case 6:
			viewPort.getGameWorld().setScrollSpeed(12.0f);
			speedView.setText("20 kn");
			break;
		case 7:
			viewPort.getGameWorld().setScrollSpeed(14.0f);
			speedView.setText("22 kn");
			break;
		case 8:
			viewPort.getGameWorld().setScrollSpeed(16.0f);
			speedView.setText("24 kn");
			break;
		}
		}
		return true;
	
	}
	
}
