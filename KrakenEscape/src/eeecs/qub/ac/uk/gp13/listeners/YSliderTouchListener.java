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
import eeecs.qub.ac.uk.gp13.controlobjects.YSliderObject;
import eeecs.qub.ac.uk.gp13.fragments.MainGameFragment;
import eeecs.qub.ac.uk.gp13.views.ScoreTextView;
import eeecs.qub.ac.uk.gp13.views.ViewPort;

/**
 * 
 * The YSliderTouchListener class implements OnTouchListener
 * This class should be used in conjunction with a YSliderObject object
 * This class will listen for any user input relating to the object and will update the object accordingly
 *
 */
@SuppressLint("ClickableViewAccessibility")
public class YSliderTouchListener implements OnTouchListener
{
	private int prevPos;
	private Vibrator vib;
	private YSliderObject ySlider;
	private ImageView ySliderV;
	private MyAssetManager myAssetManager;
	private ViewPort viewPort;
	private SoundFX slideFX;
	private Context context;
	private ScoreTextView depthView;
	
	public YSliderTouchListener(Vibrator iVib, YSliderObject iYSlider, ImageView iYSliderV, MyAssetManager iMyAssetManager, ViewPort iViewPort, Context iContext, ScoreTextView iDepthView)
	{
		vib = iVib;
		ySlider = iYSlider;
		ySliderV = iYSliderV;
		myAssetManager = iMyAssetManager;
		viewPort = iViewPort;
		context = iContext;
		depthView = iDepthView;
		
		try {
			slideFX = new SoundFX(context, R.raw.slider);
		} catch (IOException e) {
			Log.e("SliderSFX", "Y Slider FX could not be loaded");
		}
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		if (!MainGameFragment.isPaused()){
		prevPos = ySlider.getValueSelected();
		double x = event.getY();
		
		ySlider.findClosestPosition(ySliderV.getHeight() - x);
		
		if (ySlider.getValueSelected() != prevPos)
		{
			vib.vibrate(100);
			prevPos = ySlider.getValueSelected();
			slideFX.playSoundFX();
		}
		if (event.getAction() == android.view.MotionEvent.ACTION_UP){
			slideFX.clearFX();
			if (!slideFX.hasPlayed()){
				try {
					slideFX = new SoundFX(context, R.raw.slider);
				} catch (IOException e) {
					Log.e("SlideFX", "Y Start FX could not be loaded");
				}
			}
		if (MainGameFragment.getCurrentCommand().getControlType() == ySlider && prevPos == MainGameFragment.getCurrentCommand().getCompleteCondition())
		{
			MainGameFragment.setcCorrect(true);
		}}
		
		Bitmap changeDisplay = myAssetManager.LoadBitmap(ySlider.getPicLoc());
		ySliderV.setImageBitmap(changeDisplay);
		
		//text.setText(button.getLabel() + " - " + button.getActivated() + "\n" + flipSwitch.getLabel() + " - " + flipSwitch.getActivated() + "\n" + slidingSwitch.getLabel() + " - " + slidingSwitch.getActivated() + "\n" + slider.getLabel() + " - " + slider.getValueSelected() + "\n" + dial.getLabel() + " - " + dial.getValueSelected());
		
		
		//get a handle on the gameworld and set the y location of the sub bitmap
		switch (ySlider.getValueSelected()) {
		case 1:
			//Change the ySubLocation to the same value in the game world class
			viewPort.getGameWorld().setYSubLocation(160);
			depthView.setText("200m");
			break;
		case 2:
			viewPort.getGameWorld().setYSubLocation(120);
			depthView.setText("160m");
			break;
		case 3:
			viewPort.getGameWorld().setYSubLocation(80);
			depthView.setText("120m");
			break;
		case 4:
			viewPort.getGameWorld().setYSubLocation(40);
			depthView.setText("80m");
			break;
		case 5:
			viewPort.getGameWorld().setYSubLocation(0);
			depthView.setText("40m");
			break;
			
		default:
			break;
		}}
		return true;
	}
}
