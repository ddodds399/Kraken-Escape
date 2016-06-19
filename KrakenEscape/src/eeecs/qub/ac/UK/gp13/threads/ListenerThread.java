package eeecs.qub.ac.uk.gp13.threads;

import android.content.Context;
import android.os.Vibrator;
import android.widget.ImageView;
import eeecs.qub.ac.uk.gp13.assetmanager.MyAssetManager;
import eeecs.qub.ac.uk.gp13.controlobjects.DialObject;
import eeecs.qub.ac.uk.gp13.controlobjects.TwoStateControlPanelObject;
import eeecs.qub.ac.uk.gp13.controlobjects.XSliderObject;
import eeecs.qub.ac.uk.gp13.controlobjects.YSliderObject;
import eeecs.qub.ac.uk.gp13.listeners.DialTouchListener;
import eeecs.qub.ac.uk.gp13.listeners.TwoStateClickListener;
import eeecs.qub.ac.uk.gp13.listeners.XSliderTouchListener;
import eeecs.qub.ac.uk.gp13.listeners.YSliderTouchListener;
import eeecs.qub.ac.uk.gp13.views.ScoreTextView;
import eeecs.qub.ac.uk.gp13.views.ViewPort;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * This listener thread initialises all of the listeners required for the control panel
 *
 */
public class ListenerThread extends Thread
{
	Vibrator vib;
	TwoStateControlPanelObject[] twoState;
	DialObject dial;
	XSliderObject xSlider;
	YSliderObject ySlider;
	ImageView[] arrayOfClickViews;
	ImageView[] arrayOfTouchViews;
	MyAssetManager myAssetManager;
	Context context;
	ViewPort viewPort;
	ScoreTextView speedView, depthView, yawView;
	
	public ListenerThread(Vibrator iVib, TwoStateControlPanelObject[] iTwoState, XSliderObject iXSlider, YSliderObject iYSlider, DialObject iDial, ImageView[] iArrayOfClickViews, ImageView[] iArrayOfTouchViews, MyAssetManager iMyAssetManager, ViewPort iViewPort, Context iContext, ScoreTextView iSpeedView, ScoreTextView iDepthView, ScoreTextView iYawView)
	{
		vib = iVib;
		twoState = iTwoState;
		dial = iDial;
		xSlider = iXSlider;
		ySlider = iYSlider;
		arrayOfClickViews = iArrayOfClickViews;
		arrayOfTouchViews = iArrayOfTouchViews;
		myAssetManager = iMyAssetManager;
		viewPort = iViewPort;
		context = iContext;
		speedView = iSpeedView;
		depthView = iDepthView;
		yawView = iYawView;
	}
	
	public void run()
	{
		for (int i = 0; i < 3; i++)
		{
			arrayOfClickViews[i].setOnClickListener(new TwoStateClickListener(vib, twoState[i], arrayOfClickViews[i], myAssetManager, context));
		}
		
		arrayOfTouchViews[0].setOnTouchListener(new XSliderTouchListener(vib, xSlider, arrayOfTouchViews[0], myAssetManager, viewPort, context, yawView));
		arrayOfTouchViews[1].setOnTouchListener(new YSliderTouchListener(vib, ySlider, arrayOfTouchViews[1], myAssetManager, viewPort, context, depthView));
		arrayOfTouchViews[2].setOnTouchListener(new DialTouchListener(vib, dial, arrayOfTouchViews[2], myAssetManager, viewPort, context, speedView));
	}
}
