package eeecs.qub.ac.uk.gp13.listeners;

import android.graphics.Bitmap;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import eeecs.qub.ac.uk.gp13.MainGameFragment;
import eeecs.qub.ac.uk.gp13.MyAssetManager;
import eeecs.qub.ac.uk.gp13.controlobjects.TwoStateControlPanelObject;

public class TwoStateClickListener implements OnClickListener
{
	private Vibrator vib;
	private TwoStateControlPanelObject twoState;
	private ImageView twoStateV;
	private MyAssetManager myAssetManager;
	
	public TwoStateClickListener(Vibrator iVib, TwoStateControlPanelObject iTwoState, ImageView iTwoStateV, MyAssetManager iMyAssetManager)
	{
		vib = iVib;
		twoState = iTwoState;
		twoStateV = iTwoStateV;
		myAssetManager = iMyAssetManager;
	}
	
	@Override
	public void onClick(View v)
	{
		vib.vibrate(100);
		if (twoState.getActivated() == true)
		{
			twoState.setActivated(false);
			
			Bitmap changeDisplay = myAssetManager.LoadBitmap(twoState.getPicLoc());
			twoStateV.setImageBitmap(changeDisplay);
		}
		
		else if (twoState.getActivated() == false)
		{
			twoState.setActivated(true);
			
			Bitmap changeDisplay = myAssetManager.LoadBitmap(twoState.getPicLoc());
			twoStateV.setImageBitmap(changeDisplay);
		}
		if (MainGameFragment.getCurrentCommand().getControlType() == twoState)
		{
			MainGameFragment.setcCorrect(true);
		}
	}
}
