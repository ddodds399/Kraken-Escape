package eeecs.qub.ac.uk.gp13.activities;


import eeecs.qub.ac.uk.gp13.BackgroundMusic;
import eeecs.qub.ac.uk.gp13.R;
import eeecs.qub.ac.uk.gp13.fragments.StartGameFragment;
import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;

public class MainActivity extends Activity {

	private BackgroundMusic theme;
	private int difficulty;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * 
	 * This is a method that android calls when the activity is created
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Window window = getWindow();
		window.requestFeature(Window.FEATURE_NO_TITLE);
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		theme = new BackgroundMusic(MainActivity.this , R.raw.theme);
		if (theme.hasPlayed() == false){
		theme.StartMusic();
		}
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new StartGameFragment()).commit();
		}
		
		difficulty = 0;
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 * 
	 * This is another method that android calls when the app is pushed to the background or it is interrupted, it pauses our game and music
	 */
	@Override
	public void onPause() {
		super.onPause();
		theme.pauseMusic();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 * 
	 * This is called by android when the user brings our app back to the front, it resumes our game and music
	 */
	@Override 
	public void onResume(){
		super.onResume();
		theme.resumeMusic();
		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 * 
	 * A method called by android when our app is closed fully
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		theme.destroyMusic();
	}
	
	/**
	 * @return
	 * returns the background music player for the activity
	 */
	public BackgroundMusic getPlayer() {
		return theme;
	}
	
	/**
	 * @return
	 * returns the difficulty that game has been set as in options
	 * (Easy by default)
	 */
	public int getDifficulty(){
		return difficulty;
	}
	
	/**
	 * @param i 
	 * setter used to set overall difficulty of game
	 */
	public void setDifficulty(int i){
		if (i == 1){
			difficulty = 1;
		} else {
			difficulty = 0;
		}
		
	}
	
	/**
	 * @param view
	 * Method called when the radio buttons are clicked within the options fragment
	 * Used Android Developer documentation to understand radio button grouping
	 * http://developer.android.com/guide/topics/ui/controls/radiobutton.html
	 */
	public void onRadioClicked(View view){
		boolean checked = ((RadioButton) view).isChecked();
		
		switch (view.getId()) {
		case R.id.radioEasy:
			if (checked)
				setDifficulty(0);
			break;
		case R.id.radioHard:
			if (checked)
				setDifficulty(1);
			break;
		}
	}
}
