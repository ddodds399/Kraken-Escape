package eeecs.qub.ac.uk.gp13.fragments;

import eeecs.qub.ac.uk.gp13.R;
import eeecs.qub.ac.uk.gp13.activities.MainActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class OptionsFragment extends Fragment {

	private TextView musicText;
	private Switch musicSwitch;
	//Don't know why this is coming up as unused as it is being used
	@SuppressWarnings("unused")
	private RadioGroup radioDiffGroup;
	private RadioButton radioEasy, radioHard;
	private MainActivity mainAct;
	private View view;
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 *
	 * This is the method that deals with the view in the UI
	 * View variables get initialised here
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frag_opt, container,
				false);
	
		mainAct = (MainActivity)getActivity();
		musicText = (TextView) view.findViewById(R.id.music_text);
		musicSwitch = (Switch) view.findViewById(R.id.switch1);
		radioDiffGroup = (RadioGroup) view.findViewById(R.id.radioDiff);
		radioEasy = (RadioButton) view.findViewById(R.id.radioEasy);
		radioHard = (RadioButton) view.findViewById(R.id.radioHard);
		musicSwitch.setChecked(!mainAct.getPlayer().isOff());
		if (mainAct.getDifficulty() == 1){
			radioHard.setChecked(true);
		} else {
			radioEasy.setChecked(true);
		}
		addMusicListener();

		return view;
	}
	
	/**
	 * Adds functionality to the Music On/Off compound button
	 * Used Android documentation to understand compound button
	 * http://developer.android.com/reference/android/widget/CompoundButton.OnCheckedChangeListener.html
	 */
	public void addMusicListener(){
		musicSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked){
					musicText.setText("Music On");
					mainAct.getPlayer().StartMusic();
					mainAct.getPlayer().turnOn();
				} else { 
					musicText.setText("Music Off");
					mainAct.getPlayer().stopMusic();
					mainAct.getPlayer().turnOff();
				}
			}
		});	
	}
	
}
