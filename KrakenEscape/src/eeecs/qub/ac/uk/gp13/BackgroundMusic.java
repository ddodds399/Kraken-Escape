package eeecs.qub.ac.uk.gp13;

import android.content.Context;
import android.media.MediaPlayer;

public class BackgroundMusic extends MediaPlayer {
	
	private MediaPlayer backPlayer;
	private int curPos;
	private boolean played;
	
	public BackgroundMusic(Context context, int resid){
		super();
		backPlayer = MediaPlayer.create(context, resid);
		backPlayer.setLooping(true);
		
	}
	
	public void StartMusic() {
		backPlayer.start();
		played = true;	
	}
	
	public void pauseMusic() {
		curPos = backPlayer.getCurrentPosition();
		backPlayer.pause();
	}
	
	public void resumeMusic(){
		backPlayer.start();
		backPlayer.seekTo(curPos);
	}
	
	public void stopMusic() {
		backPlayer.stop();
		backPlayer.release();
	}
	
	public boolean hasPlayed() {
		return played;
	}
}
