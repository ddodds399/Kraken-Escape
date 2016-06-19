package eeecs.qub.ac.uk.gp13.listeners;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import eeecs.qub.ac.uk.gp13.R;
import eeecs.qub.ac.uk.gp13.SoundFX;
import eeecs.qub.ac.uk.gp13.assetmanager.MyAssetManager;
import eeecs.qub.ac.uk.gp13.controlobjects.TwoStateControlPanelObject;
import eeecs.qub.ac.uk.gp13.fragments.MainGameFragment;

/**
 * 
 * The TwoStateClickListener implements OnClickListener
 * This class should be used in conjunction with either a ButtonObject, a FlipSwitchObject or a SlidingSwitchObject
 * This class will listen for any user input relating to any TwoStateControlPanel object
 * 
 */
public class TwoStateClickListener implements OnClickListener
{
	private Vibrator vib;
	private TwoStateControlPanelObject twoState;
	private ImageView twoStateV;
	private MyAssetManager myAssetManager;
	private SoundFX clickOn, clickOff;
	private Context context;
	
	public TwoStateClickListener(Vibrator iVib, TwoStateControlPanelObject iTwoState, ImageView iTwoStateV, MyAssetManager iMyAssetManager, Context iContext)
	{
		vib = iVib;
		twoState = iTwoState;
		twoStateV = iTwoStateV;
		myAssetManager = iMyAssetManager;
		context = iContext;
		try {
		clickOn = new SoundFX(context, R.raw.buttonon);
		clickOff = new SoundFX(context, R.raw.buttonoff);
		} catch (IOException e) {
			Log.e("ClickFX", "Click FX could not be loaded");
		}
	}
	
	@Override
	public void onClick(View v)
	{
		if (!MainGameFragment.isPaused()){
		vib.vibrate(100);
		
		
		if (twoState.getActivated() == true)
		{
			twoState.setActivated(false);
			clickOn.clearFX();
			clickOff.playSoundFX();
			try {
				clickOn = new SoundFX(context, R.raw.buttonon);
				} catch (IOException e) {
					Log.e("ClickFX", "Click FX could not be loaded");
				}
			Bitmap changeDisplay = myAssetManager.LoadBitmap(twoState.getPicLoc());
			twoStateV.setImageBitmap(changeDisplay);
		}
		
		else if (twoState.getActivated() == false)
		{
			twoState.setActivated(true);
			clickOff.clearFX();
			clickOn.playSoundFX();
			try {
				clickOff = new SoundFX(context, R.raw.buttonoff);
				} catch (IOException e) {
					Log.e("ClickFX", "Click FX could not be loaded");
				}
			Bitmap changeDisplay = myAssetManager.LoadBitmap(twoState.getPicLoc());
			twoStateV.setImageBitmap(changeDisplay);
		}
		
		if (MainGameFragment.getCurrentCommand().getControlType() == twoState)
		{
			MainGameFragment.setcCorrect(true);
		}}
	}
	
	public SoundFX getClickOn(){
		return clickOn;
	}
	
	public SoundFX getClickOff(){
		return clickOff;
	}
}
