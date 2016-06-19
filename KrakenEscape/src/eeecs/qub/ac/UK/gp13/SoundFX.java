package eeecs.qub.ac.uk.gp13;

import java.io.FileNotFoundException;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundFX {

	private SoundPool soundFX;
	private int soundPlayed;
	private final int maxChannels = 20;
	
	/**
	 * SoundFX constructor initialise FX as a soundpool and readies it
	 * 
	 * @param iContext - application context from activity
	 * @param path - raw path of file
	 * @throws FileNotFoundException - throws if file cannot be found in path
	 */
	public SoundFX(Context iContext, int path) throws FileNotFoundException {
		soundFX = new SoundPool(maxChannels, AudioManager.STREAM_MUSIC, 0);
		soundPlayed = soundFX.load(iContext, path , 1);
	}
	
	//Plays soundfx
	public void playSoundFX(){
		if (soundPlayed != -1){
			soundFX.play(soundPlayed, 1.0f, 1.0f, 1, 0, 1.0f);
		}
	}
	
	//Stops the sound and releases it
	public void clearFX(){
		soundFX.stop(soundPlayed);
		soundFX.release();
		soundPlayed = -1;
	}
	
	//Getter
	/**
	 * @return
	 * returns soundfx soundpool
	 */
	public SoundPool getFX(){
		return soundFX;
	}
	
	//Setter
	//Sets if the soundfx has played or not
	public boolean hasPlayed(){
		if (soundPlayed == -1){
			return false;
		} else {
			return true;
		}
	}
	
}
