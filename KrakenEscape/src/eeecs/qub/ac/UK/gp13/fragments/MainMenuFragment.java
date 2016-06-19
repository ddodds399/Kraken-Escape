package eeecs.qub.ac.uk.gp13.fragments;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import eeecs.qub.ac.uk.gp13.R;
import eeecs.qub.ac.uk.gp13.SoundFX;
import eeecs.qub.ac.uk.gp13.activities.MainActivity;
import eeecs.qub.ac.uk.gp13.networking.HighScoresFragment;

public class MainMenuFragment extends Fragment {
	
	private ImageView start, scores, options, htp; 
	private MainActivity mainAct;
	private SoundFX startFX, scoreFX, optFX, htpFX;
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 *
	 * This is the method that deals with the view in the UI
	 * View variables get initialised here
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_menu, container,
				false);
		getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
		mainAct = (MainActivity)getActivity();	
		start = (ImageView) view.findViewById(R.id.start_button);
		scores = (ImageView)view.findViewById(R.id.high_button);
		options = (ImageView) view.findViewById(R.id.options_button);
		htp = (ImageView) view.findViewById(R.id.htp_button);
		
		setupSoundEffects(mainAct.getApplicationContext());
		setupStart();
		setupScores();
		setupOptions();
		setupHowToPlay();
		
		return view;
	}
	
	/**
	 * 
	 * This method transitions to the next fragment while keeping the menu on backstack
	 * 
	 * @param frag
	 */
	public void transition(Fragment frag) {
		FragmentManager fragMan = getFragmentManager();
		FragmentTransaction fragTran = fragMan.beginTransaction().addToBackStack(null);
		fragTran.replace(getId(), frag);
		fragTran.commit();
	}
	
	//Adds a click listener to start button aswell as actions
	public void setupStart() {	
		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mainAct.getPlayer().isGamePlaying() == true){
					MainGameFragment.getBackMusic().release();
					mainAct.getPlayer().setGamePlaying(false);				}
				startFX.playSoundFX();
				MainGameFragment mainGame = new MainGameFragment();
				mainAct.getPlayer().pauseMusic();
				mainAct.getPlayer().setGamePlaying(true);
				transition(mainGame);	
			}
		});	
	}
	
	//Adds a click listener to high scores button aswell as actions
	public void setupScores() {
		scores.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				scoreFX.playSoundFX();	
				
				//Inserting code to prevent the app from crashing if the server is offline
				//Possible error message if server is offline
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				long startTime = System.currentTimeMillis();
				
				if (connectedToInternet() == true)
				{
					try
					{
						Socket clientSocket = new Socket();
						
						//Permanent Server with ports forwarded/
						//Ygritte
						//clientSocket.connect(new InetSocketAddress("82.14.86.172", 7502), 250);
						//Serenity
						clientSocket.connect(new InetSocketAddress("82.14.86.172", 9002), 500);
						
						clientSocket.close();
					}
					catch(IOException e)
					{
						
					}
					
					long totalTime = System.currentTimeMillis() - startTime;
					
					if (totalTime < 500)
					{
						HighScoresFragment highFrag = new HighScoresFragment();
						transition(highFrag);
					}
					
					else
					{
						Toast toast = Toast.makeText(getActivity(), "Server is offline", Toast.LENGTH_LONG);
						toast.show();
					}
				}
				
				else
				{
					Toast toast = Toast.makeText(getActivity(), "You are offline", Toast.LENGTH_LONG);
					toast.show();
				}
			}
		});	
	}
	
	//Adds a click listener to options button aswell as actions
	public void setupOptions() {
		options.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				optFX.playSoundFX();	
				OptionsFragment options = new OptionsFragment();
				transition(options);	
			}
		});
	}
	
	//Adds a click listener to HTP button aswell as actions
	public void setupHowToPlay() {
		htp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				htpFX.playSoundFX();
				Intent HTPFrag = new Intent(getActivity(),HTPFragmentActivity.class);
				getActivity().startActivity(HTPFrag);	
			}
		});		
	}
	
	/**
	 * Initialises the SoundFX for each button
	 * 
	 * @param context - application context from activity
	 */
	public void setupSoundEffects(Context context) {
		try {
			startFX = new SoundFX(context, R.raw.startfx);
			scoreFX = new SoundFX(context, R.raw.scorefx);
			optFX = new SoundFX(context, R.raw.optfx);
			htpFX = new SoundFX(context, R.raw.htpfx);
		} catch (IOException e) {
			Log.e("StartFX", "Start FX could not be loaded");
		}
	}
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onPause()
	 * 
	 *  The android pause method
	 */ 
	@Override
	public void onPause() {
		super.onPause();
		if (getActivity().isFinishing()) {			
				startFX.clearFX();
				scoreFX.clearFX();
				optFX.clearFX();
				htpFX.clearFX();
		}
	}
	
	private boolean connectedToInternet()
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		
		boolean output = networkInfo != null && networkInfo.isConnected();
		
		return output;
	}
}
