package eeecs.qub.ac.uk.gp13;

import java.io.IOException;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainMenuFragment extends Fragment {

	SoundPool start7, high5, opt6, htpH5;
	int startPlayed = -1, highPlayed = -1, optPlayed = -1, htpPlayed = -1;
	final int maxChannels = 20;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_menu, container,
				false);
			
		getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		AssetManager assetManager = getActivity().getAssets();
			
		ImageView start = (ImageView) view.findViewById(R.id.start_button);
		ImageView scores = (ImageView)view.findViewById(R.id.high_button);
		ImageView options = (ImageView) view.findViewById(R.id.options_button);
		ImageView htp = (ImageView) view.findViewById(R.id.htp_button);
		
		start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (startPlayed != -1){
					start7.play(startPlayed, 1.0f, 1.0f, 1, 0, 1.0f);
				}	
				
				MainGameFragment mainGame = new MainGameFragment();
				transition(mainGame);
				
				
			}
		});
		
		start7 = new SoundPool(maxChannels, AudioManager.STREAM_MUSIC, 0);
		
		try {
			AssetFileDescriptor music7 = assetManager.openFd("music/seven.mp3");

			startPlayed = start7.load(music7, 1);
		} catch (IOException e) {
			Log.e("Music7", "Start music could not be loaded");
		}
		
		scores.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (highPlayed != -1){
					high5.play(highPlayed, 1.0f, 1.0f, 1, 0, 1.0f);
				}	
			
				//Old CommandTester
				//CommandTest commandTester = new CommandTest();
				//transition(commandTester);
				
			}
		});
		
		high5 = new SoundPool(maxChannels, AudioManager.STREAM_MUSIC, 0);
		
		try {
			AssetFileDescriptor music5 = assetManager.openFd("music/five.mp3");

			highPlayed = high5.load(music5, 1);
		} catch (IOException e) {
			Log.e("Music5", "Highscore music could not be loaded");
		}
		
		options.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (optPlayed != -1){
					opt6.play(startPlayed, 1.0f, 1.0f, 1, 0, 1.0f);
				}	
				OptionsFragment options = new OptionsFragment();
				transition(options);
				
			}
		});
		
		opt6 = new SoundPool(maxChannels, AudioManager.STREAM_MUSIC, 0);
		
		try {
			AssetFileDescriptor music6 = assetManager.openFd("music/six.mp3");

			optPlayed = opt6.load(music6, 1);
		} catch (IOException e) {
			Log.e("Music6", "Option music could not be loaded");
		}
		
		htp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (htpPlayed != -1){
					htpH5.play(htpPlayed, 1.0f, 1.0f, 1, 0, 1.0f);
				}
				
				Intent HTPFrag = new Intent(getActivity(),HTPFragmentActivity.class);
				getActivity().startActivity(HTPFrag);
				
			}
		});
		
		htpH5 = new SoundPool(maxChannels, AudioManager.STREAM_MUSIC, 0);
		
		try {
			AssetFileDescriptor music6 = assetManager.openFd("music/efive.mp3");

			htpPlayed = htpH5.load(music6, 1);
		} catch (IOException e) {
			Log.e("MusicH5", "HTP music could not be loaded");
		}
		
		return view;
	}
	
	public void transition(Fragment frag) {
		FragmentManager fragMan = getFragmentManager();
		FragmentTransaction fragTran = fragMan.beginTransaction().addToBackStack(null);
		fragTran.replace(getId(), frag);
		fragTran.commit();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (getActivity().isFinishing()) {			
				start7.release();
				high5.release();
				opt6.release();
				htpH5.release();
		}
	}
	
	
}
