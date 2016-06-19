package eeecs.qub.ac.uk.gp13;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class OptionsFragment extends Fragment {

	TextView musicText;
	Switch musicSwitch;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_opt, container,
				false);
	
		musicText = (TextView) view.findViewById(R.id.music_text);
		musicSwitch = (Switch) view.findViewById(R.id.switch1);
		musicSwitch.setChecked(true);
		
		final MainActivity cd = (MainActivity)getActivity();
		
		musicSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				
				if(isChecked){
					musicText.setText("Music On");
					cd.getPlayer().resumeMusic();
				} else { 
					musicText.setText("Music Off");
					cd.getPlayer().pauseMusic();
				}
			}
		});
		
		return view;
	}
}
