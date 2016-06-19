package eeecs.qub.ac.uk.gp13.fragments;

import eeecs.qub.ac.uk.gp13.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ClickableViewAccessibility") public class StartGameFragment extends Fragment {

	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 * 
	 * This is the method that deals with the view in the UI
	 * View variables get initialised here
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.frag_start, container,
				false);
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
}


