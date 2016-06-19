package eeecs.qub.ac.uk.gp13;


import android.app.Activity;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	BackgroundMusic theme;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Window window = getWindow();
		window.requestFeature(Window.FEATURE_NO_TITLE);
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		AssetManager assetManager = getAssets();
		KrakenEscape krakenEscape = ((KrakenEscape)getApplicationContext());
		krakenEscape.SetAssetManager(assetManager);
		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		theme = new BackgroundMusic(MainActivity.this , R.raw.theme);
		theme.StartMusic();
		
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new StartGameFragment()).commit();
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		theme.pauseMusic();
	}
	
	@Override 
	public void onResume(){
		super.onResume();
		theme.resumeMusic();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		theme.stopMusic();
	}
	
	public BackgroundMusic getPlayer() {
		return theme;
	}
}
