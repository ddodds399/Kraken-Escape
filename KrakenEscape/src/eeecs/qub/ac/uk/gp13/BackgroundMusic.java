package eeecs.qub.ac.uk.gp13;

import android.content.Context;
import android.media.MediaPlayer;

public class BackgroundMusic extends MediaPlayer {
	
	private MediaPlayer backPlayer;
	private int curPos;
	private boolean played, off, gamePlaying;
	
	/**
	 * BackgroundMusic extends MediaPlayer
	 * Constructor loads in raw file and prepares
	 * 
	 * @param context - application context from activity
	 * @param resid - the raw id of the file to be loaded
	 */
	public BackgroundMusic(Context context, int resid){
		super();
		backPlayer = MediaPlayer.create(context, resid);
		backPlayer.setLooping(true);
		played = false;
		off = false;
		gamePlaying = false;
	}
	
	// Starts the playback of music, sets a boolean that it has played
	public void StartMusic() {
		backPlayer.start();
		played = true;	
	}
	
	//Pauses music and saves current position
	public void pauseMusic() {
		curPos = backPlayer.getCurrentPosition();
		backPlayer.pause();
	}
	
	//Resumes playback
	public void resumeMusic(){	
		if (off == false && gamePlaying == false){
		backPlayer.start();
		backPlayer.seekTo(curPos);
		}
	}
	
	//Stops music and resets to zero
	public void stopMusic() {
		backPlayer.pause();
		curPos = 0;
	}
	
	//Releases music when finished
	public void destroyMusic() {
		backPlayer.stop();
		backPlayer.release();
	}
	
	//Setters
	//Sets off variable false
	public void turnOn() {
		off = false;
	}
	
	//Sets off variable true
	public void turnOff() {
		off = true;
	}
	
	//Sets gamePlaying variable
	public void setGamePlaying(boolean n) {
		gamePlaying = n;
	}
	
	//Getters
	/**
	 * @return
	 * returns variable indicating if the music is off
	 */
	public boolean isOff() {
		return off;
	}
	
	/**
	 * @return
	 * returns variable indicating if main game fragment is playing
	 */
	public boolean isGamePlaying() {
		return gamePlaying;
	}
	
	/**
	 * @return
	 * returns variable indicating if the music has played
	 */
	public boolean hasPlayed() {
		return played;
	}	
}
