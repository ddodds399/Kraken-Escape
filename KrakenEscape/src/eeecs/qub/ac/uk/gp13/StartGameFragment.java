package eeecs.qub.ac.uk.gp13;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ClickableViewAccessibility") public class StartGameFragment extends Fragment {

	MediaPlayer playerBack;
	boolean audioAvail = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.frag_start, container,
				false);
		
//		playerBack = new MediaPlayer();
//		
//		getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
//		
//		AssetManager assetManager = getActivity().getAssets();
//		
//		try {
//		
//		AssetFileDescriptor musicBack = assetManager.openFd("music/theme.mp3");
//		
//		playerBack.setDataSource(musicBack.getFileDescriptor(), musicBack.getStartOffset(), musicBack.getLength());
//		
//		playerBack.setLooping(true);
//		playerBack.prepare();
//		audioAvail = true;
//		} catch (IOException e) {
//			
//		}
		
		rootView.setOnTouchListener(new View.OnTouchListener(){
			

			@SuppressLint("CommitTransaction") @Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				FragmentManager fragMan = getFragmentManager();
				FragmentTransaction fragTran = fragMan.beginTransaction().addToBackStack(null);
				MainMenuFragment menu = new MainMenuFragment();
				fragTran.replace(getId(), menu);
				fragTran.commit();
				return false;
			}
		});
		
		return rootView;
		
	}
	
//	@Override
//	public void onResume() {
//		super.onResume();
//		if (audioAvail) {
//			playerBack.start();
//		}
//	}
//	
//	@Override
//	public void onPause() {
//		super.onPause();
//		//if (audioAvail) {
//			//playerBack.pause();
//
//			if (getActivity().isFinishing()) {
//				playerBack.stop();
//				playerBack.release();
//			}
//		}
	}


