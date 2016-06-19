package eeecs.qub.ac.uk.gp13.views;
/*
 *  Solution found on StackOverflow for setting up a custom view dialog view 
 *  object: http://stackoverflow.com/questions/13341560/how-to-create-a-custom-dialog-box-in-android
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import eeecs.qub.ac.uk.gp13.R;
import eeecs.qub.ac.uk.gp13.fragments.MainMenuFragment;
import eeecs.qub.ac.uk.gp13.maingame.PlaySession;
import eeecs.qub.ac.uk.gp13.networking.HighScoresFragment;


public class GameOverDialogView extends Dialog implements 
android.view.View.OnClickListener{

	public PlaySession playSession;
	public Activity activity;
	public Dialog dialog;
	public Button menu, highscore;
	public Fragment inFrag;
	public TextView scoreText;
	
	/**
	 * @param activity
	 * @param iInFrag
	 * @param iPlaySession
	 * 
	 * Constructor for the GameOverDialogView 
	 */
	public GameOverDialogView(Activity activity, Fragment iInFrag, PlaySession iPlaySession)
	{
		super(activity);
		this.activity = activity;
		playSession = iPlaySession;
		inFrag = iInFrag;
	}
	/* (non-Javadoc)
	 * @see android.app.Dialog#onCreate(android.os.Bundle)
	 * 
	 * Oncreate method that sets the content view and links buttons and textviews to XML IDs
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gameoverdialog);
		menu = (Button)findViewById(R.id.btn_menu);
		highscore = (Button)findViewById(R.id.highscores);
		menu.setOnClickListener(this);
		highscore.setOnClickListener(this);
		scoreText = (TextView) findViewById(R.id.dialogtext);
		setCanceledOnTouchOutside(false);
	}
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 * 
	 * OnClick method called when touch detected, switch statement decides what action to take depending on integer value of 
	 * which view is clicked
	 */
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.highscores:
			
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			
			if (connectedToInternet() == true)
			{
				long startTime = System.currentTimeMillis();
				
				try
				{
					Socket clientSocket = new Socket();
					
					//Permanent Server with ports forwarded
					clientSocket.connect(new InetSocketAddress("82.14.86.172", 9002), 500);
					
					clientSocket.close();
				}
				catch(IOException e)
				{
					
				}
				
				long totalTime = System.currentTimeMillis() - startTime;
				
				if (totalTime < 500)
				{
					transition(new HighScoresFragment());
				}
				
				else
				{
					Toast toast = Toast.makeText(activity, "Server is offline - Frag Tran", Toast.LENGTH_LONG);
					toast.show();
				}
			}
			
			else
			{
				Toast toast = Toast.makeText(activity, "You are offline - Frag Tran", Toast.LENGTH_LONG);
				toast.show();
			}
			
			break;
		case R.id.btn_menu:
			//return to main menu
			transition(new MainMenuFragment());
			break;
		default:
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
	/**
	 * @param txt
	 * Sets the value of scoreText to a parameter value (txt)
	 */
	public void setScore(String txt){
		scoreText.setText(txt);
	}
	
	/**
	 * A method which checks to see whether the android device has internet connectivity
	 * @return true if device is connected to the internet and false otherwise
	 */
	private boolean connectedToInternet()
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) inFrag.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		
		boolean output = networkInfo != null && networkInfo.isConnected();
		
		return output;
	}

}
