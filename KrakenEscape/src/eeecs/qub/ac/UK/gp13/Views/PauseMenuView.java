package eeecs.qub.ac.uk.gp13.views;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import eeecs.qub.ac.uk.gp13.R;
import eeecs.qub.ac.uk.gp13.fragments.MainGameFragment;
import eeecs.qub.ac.uk.gp13.fragments.MainMenuFragment;

public class PauseMenuView extends Dialog implements android.view.View.OnClickListener{

	public Activity activity;
	public Fragment inFrag;
	public Button menuButton, resumeButton;

	/**
	 * @param activity
	 * @param inFrag
	 * 
	 * Constructor for the PauseMenuView
	 */
	public PauseMenuView(Activity activity, Fragment inFrag) {
		super(activity);
		this.activity = activity;
		this.inFrag = inFrag;
	}

	/* (non-Javadoc)
	 * @see android.app.Dialog#onCreate(android.os.Bundle)
	 * 
	 * Oncreate method that sets the click listeners and links the buttons to the XML
	 */
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pausemenufragment);
		resumeButton = (Button)findViewById(R.id.btn_resume);
		menuButton = (Button)findViewById(R.id.btn_pausemenu);
		menuButton.setOnClickListener(this);
		resumeButton.setOnClickListener(this);
		setCanceledOnTouchOutside(false);
		
	}
	
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 * 
	 * Method called on click that uses a switch statement to decide which fragment to
	 * transition to
	 */
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.btn_resume:
			dismiss();
			MainGameFragment.getGameLoop().resume();
			MainGameFragment.setPaused(false);
			if (MainGameFragment.getBackMusic().hasPlayed() == true){
				MainGameFragment.getBackMusic().resumeMusic();
			}
			break;
		case R.id.btn_pausemenu:
			if (MainGameFragment.getBackMusic().hasPlayed() == true){
				MainGameFragment.getBackMusic().stopMusic();
				MainGameFragment.getBackMusic().turnOff();
			}
			transition(new MainMenuFragment());
			dismiss();
		default :
			break;
		}
		dismiss();
		
		
	}

	/**
	 * 
	 * This method transitions to the next fragment  
	 * 
	 * @param frag
	 */
	public void transition(Fragment frag)
	{
		FragmentManager fragMan = activity.getFragmentManager();
		FragmentTransaction fragTran = fragMan.beginTransaction();
		fragTran.replace(inFrag.getId(), frag);
		fragTran.commit();
	}
}
