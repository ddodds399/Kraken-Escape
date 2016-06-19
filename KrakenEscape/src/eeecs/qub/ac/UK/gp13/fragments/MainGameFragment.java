package eeecs.qub.ac.uk.gp13.fragments;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Locale;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import eeecs.qub.ac.uk.gp13.BackgroundMusic;
import eeecs.qub.ac.uk.gp13.KrakenEscape;
import eeecs.qub.ac.uk.gp13.R;
import eeecs.qub.ac.uk.gp13.SoundFX;
import eeecs.qub.ac.uk.gp13.activities.MainActivity;
import eeecs.qub.ac.uk.gp13.assetmanager.MyAssetManager;
import eeecs.qub.ac.uk.gp13.controlobjects.ButtonObject;
import eeecs.qub.ac.uk.gp13.controlobjects.DialObject;
import eeecs.qub.ac.uk.gp13.controlobjects.FlipSwitchObject;
import eeecs.qub.ac.uk.gp13.controlobjects.SlidingSwitchObject;
import eeecs.qub.ac.uk.gp13.controlobjects.TwoStateControlPanelObject;
import eeecs.qub.ac.uk.gp13.controlobjects.XSliderObject;
import eeecs.qub.ac.uk.gp13.controlobjects.YSliderObject;
import eeecs.qub.ac.uk.gp13.maingame.Command;
import eeecs.qub.ac.uk.gp13.maingame.GameLoop;
import eeecs.qub.ac.uk.gp13.maingame.PlaySession;
import eeecs.qub.ac.uk.gp13.maingame.RandSelect;
import eeecs.qub.ac.uk.gp13.networking.AppClient;
import eeecs.qub.ac.uk.gp13.threads.ListenerThread;
import eeecs.qub.ac.uk.gp13.utils.Utils;
import eeecs.qub.ac.uk.gp13.views.GameOverDialogView;
import eeecs.qub.ac.uk.gp13.views.PauseMenuView;
import eeecs.qub.ac.uk.gp13.views.InstructionPanel;
import eeecs.qub.ac.uk.gp13.views.ScoreTextView;
import eeecs.qub.ac.uk.gp13.views.ViewPort;

public class MainGameFragment extends Fragment {
	
	private static GameLoop gameLoop;
	private MyAssetManager myAssetManager;
	public View view;

	private ImageView buttonV, flipSwitchV, slidingSwitchV, xSliderV, ySliderV, dialV;
	private ButtonObject button;
	private FlipSwitchObject flipSwitch;
	private SlidingSwitchObject slidingSwitch;
	private XSliderObject xSlider;
	private YSliderObject ySlider;
	private DialObject dial;
	private Vibrator vib;
	private GameOverDialogView gameOverDialog;
	private PauseMenuView pauseMenu;
	
	private ScoreTextView scoreText;
	private ScoreTextView speedText;
	private ScoreTextView livesText;
	private ScoreTextView depthText;
	private ScoreTextView yawText;
	private ProgressBar progressBar;
	private InstructionPanel instructionPanel;
	private PlaySession playSession;
	private Animation instructionFail;
	private static Command currentCommand;
	private static boolean commandIsCorrect;
	private static boolean paused = false;
	
	private int difficulty;
	private int firstCommand = 0;
	private MainActivity mainAct;
	private SoundFX loseFX;
	private static BackgroundMusic bMusic;
	
	private ViewPort viewPort;
	private LinearLayout mainLayout;
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
	 * 
	 * This is called when the view is created
	 * It creates an instance of the game loop and the session
	 */
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		playSession = new PlaySession();
		
		gameLoop = new GameLoop(30) {
			
			//This is the update method called by the game loop
			@Override
			public void doUpdate() {
				/*
				 * Checking if the player was correct or incorrect and taking the appropriate action
				 */
				if (difficulty == 1){
				progressBar.setProgress(progressBar.getProgress() - 2);
				} else {
				progressBar.setProgress(progressBar.getProgress() - 1);
				}
				viewPort.getGameWorld().update();
			}
			
			//This is the draw method that draws to the UI
			@Override
			public void doDraw() {
				
				//This is a method that allows you to change what thread you run things on
				Utils.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						//Put draw code here
						if (iscCorrect() || progressBar.getProgress() == 0){
							newCommand();
						}
						
					}
				});
			}
		};

		//This is the code that you include in fragments if you want to get a handle on the global asset manager
		KrakenEscape krakenEscape = ((KrakenEscape)getActivity().getApplicationContext());
		myAssetManager = krakenEscape.GetMyAssetManager();		
		super.onCreate(savedInstanceState);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 * 
	 * This is the method that deals with the view in the UI
	 * View variables get initialised here
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		view = inflater.inflate(R.layout.fragment_main_game, container, false);
		int numLines = 2;
		vib = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
		
		//initialising and hiding the game over dialog
		gameOverDialog = new GameOverDialogView(getActivity(), this, playSession);
		gameOverDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		gameOverDialog.hide();
		
		//initialising and hiding the pause menu
		pauseMenu = new PauseMenuView(getActivity(), this);
		pauseMenu.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		pauseMenu.hide();
		
		//initialising the difficulty variable as set in options
		mainAct = (MainActivity)getActivity();
		difficulty = mainAct.getDifficulty();
		
		//initialising the Background Music
		bMusic = new BackgroundMusic(getActivity().getApplicationContext(), R.raw.krakenkrieg);
		if(mainAct.getPlayer().isOff() == false){
			bMusic.StartMusic();
		}
		
		//initialising SoundFX for when lives run out
		try {
			loseFX = new SoundFX(getActivity().getApplicationContext(), R.raw.lose);
		} catch (IOException e) {
			Log.e("LoseFX", "Lose FX could not be loaded");
		}
		//get a handle on the custom viewport object
		//this creates an instance of the object too
		//In this object there is an instance of the game world, the actual scrolling bitmap
		
		viewPort = (ViewPort) view.findViewById(R.id.ViewPort);
		viewPort.getGameWorld().setDifficulty(difficulty);
		viewPort.getGameWorld().setKrakenPic();
		
		//Link to XML
		mainLayout = (LinearLayout) view.findViewById(R.id.mainlayout);
		buttonV = (ImageView) view.findViewById(R.id.all_control_object_button);
		flipSwitchV = (ImageView) view.findViewById(R.id.all_control_object_flipswitch);
		slidingSwitchV = (ImageView) view.findViewById(R.id.all_control_object_slidingswitch);
		xSliderV = (ImageView) view.findViewById(R.id.all_control_object_xslider);
		dialV = (ImageView) view.findViewById(R.id.all_control_object_dial);
		ySliderV = (ImageView) view.findViewById(R.id.all_control_object_yslider);
		instructionPanel = (InstructionPanel)view.findViewById(R.id.instructionPanel);
		scoreText = (ScoreTextView)view.findViewById(R.id.fragment_main_game_scoretext);
		speedText = (ScoreTextView) view.findViewById(R.id.fragment_main_game_speedtext);
		livesText = (ScoreTextView) view.findViewById(R.id.fragment_main_game_livestext);
		depthText = (ScoreTextView) view.findViewById(R.id.fragment_main_game_depthtext);
		yawText = (ScoreTextView) view.findViewById(R.id.fragment_main_game_yawtext);
		
		speedText.setText("10 kn");
		depthText.setText("200m");
		yawText.setText("Ahead");
		
		//assigning xml tween animations
		instructionFail = AnimationUtils.loadAnimation((KrakenEscape)getActivity().getApplicationContext(), R.anim.instructionfail);
		
		//set the instruction panel to always be 2 lines in height
		instructionPanel.setLines(numLines);
		
		//initialise the progress bar to 100%
		/*
		 * Custom progress bar styling learnt from this site: 
		 * http://www.tiemenschut.com/how-to-customize-android-progress-bars/
		 */
		progressBar = (ProgressBar)view.findViewById(R.id.progressBar1);
		progressBar.setProgress(progressBar.getMax());
		
		//Create Objects
		button = new ButtonObject("Test Button", 0, 0);
		flipSwitch = new FlipSwitchObject("Test FlipSwitch", 0, 0);
		slidingSwitch = new SlidingSwitchObject("Test SlidingSwitch", 0, 0);
		xSlider = new XSliderObject("Test Slider", 0, 0, xSliderV);
		ySlider = new YSliderObject("Test YSlider", 0, 0, ySliderV);
		dial = new DialObject("Test Dial", 0, 0);
		
		commandIsCorrect = false;
		
		//Setting up random command picker and selecting first command
		RandSelect.setupRand(button, dial, flipSwitch, slidingSwitch, xSlider, ySlider);
		newCommand();
		
		//Loading All Assets
		LoadAssets();
		
		//Load Bitmaps
		Bitmap displayButton = myAssetManager.LoadBitmap(button.getPicLoc());
		Bitmap displayFlipSwitch = myAssetManager.LoadBitmap(flipSwitch.getPicLoc());
		Bitmap displaySlidingSwitch = myAssetManager.LoadBitmap(slidingSwitch.getPicLoc());
		Bitmap displaySlider = myAssetManager.LoadBitmap(xSlider.getPicLoc());
		Bitmap displayDial = myAssetManager.LoadBitmap(dial.getPicLoc());
		Bitmap displayYSlider = myAssetManager.LoadBitmap(ySlider.getPicLoc());
		
		//Load background image and set Tilemode
		Bitmap backbmp = myAssetManager.LoadBitmap("backimg");
		BitmapDrawable backbmpd = new BitmapDrawable(getResources(), backbmp);
		backbmpd.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
		mainLayout.setBackground(backbmpd);
		
		//Set initial imageviews
		buttonV.setImageBitmap(displayButton);
		flipSwitchV.setImageBitmap(displayFlipSwitch);
		slidingSwitchV.setImageBitmap(displaySlidingSwitch);
		xSliderV.setImageBitmap(displaySlider);
		dialV.setImageBitmap(displayDial);
		ySliderV.setImageBitmap(displayYSlider);
		
		scoreText.setOnClickListener(new View.OnClickListener() {
			
			//assigning a click listener to the scoreText to pause the game
			@Override
			public void onClick(View v) {
				if (gameLoop.isRunning()) {
					onPause();
					pauseMenu.show();
					paused = true;
				} else {
					onResume();
				}
			}
		});
		
		//Establishing arrays for thread creation
		TwoStateControlPanelObject[] twoState = {button, flipSwitch, slidingSwitch};
		ImageView[] arrayOfClickViews = {buttonV, flipSwitchV, slidingSwitchV};
		ImageView[] arrayOfTouchViews = {xSliderV, ySliderV, dialV};
		
		//Creating/running thread
		ListenerThread listenerThread = new ListenerThread(vib, twoState, xSlider, ySlider, dial, arrayOfClickViews, arrayOfTouchViews, myAssetManager, viewPort, getActivity().getApplicationContext(), speedText, depthText, yawText);
		listenerThread.start();
		
		return view;
	}
	
	public String scoreToString()
	{
		/*
		 * Padding the score with 0s
		 * to give a consist experience. ( Custom text view has "match_parent" width property )
		 */

	    String scoreString = String.format(Locale.ENGLISH,"%05d", playSession.getScore());
		
		return scoreString;
	}
	
	//Selecting a new random command and setting the instructionPanel
	public void newCommand() {
		//Statement for easter egg
		if (firstCommand == 1){
			if (difficulty == 1) {
				if (dial.getValueSelected() == 6){
					if (xSlider.getValueSelected() == 5){
						if (ySlider.getValueSelected() == 5){
							if (slidingSwitch.getActivated() == true){
								viewPort.getGameWorld().setEasterEgg();
							}
						}
					}
				}
			}
		}
		if (firstCommand < 3){
			firstCommand++;
		}
		// Set current command to a random command
		setCurrentCommand(RandSelect.getRand());
		
		// Check if current command is already true, if it is the generate a new random command
		while(checkCommand()){
			setCurrentCommand(RandSelect.getRand());
		}
		
		// If command was not answered correctly, reset the counter, subtract 1 HP and start instruction fail animation
		if(commandIsCorrect == false)
		{
			playSession.resetCounter();
			playSession.setSubHP(playSession.getSubHP()-1);
			view.startAnimation(instructionFail);
		}
		// If command was answered correctly, add 1 HP (limit of 10), add to counter and add to score
		else
		{
			playSession.setSubHP(playSession.getSubHP()+1);
			playSession.addCounter();
			playSession.addScore(progressBar.getProgress());
			if(playSession.getSubHP() > 10)
			{
				playSession.setSubHP(10);
			}
			
		}
		// Ends the game when HP is below 1
		if(playSession.getSubHP() < 1)
		{
				loseFX.playSoundFX();
				if (bMusic.hasPlayed() == true){
					bMusic.stopMusic();
					bMusic.turnOff();
				}
				gameOverDialog.show();
				gameOverDialog.setScore("Score: " + scoreToString());
				
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				long startTime = System.currentTimeMillis();
				
				try
				{
					Socket clientSocket = new Socket();
					
					//Permanent Server with ports forwarded
					clientSocket.connect(new InetSocketAddress("82.14.86.172", 9002), 500);
					
					clientSocket.close();
				}
				catch(IOException e)
				{
					
				}
				
				long totalTime = System.currentTimeMillis() - startTime;
				
				if (totalTime < 500)
				{
					AppClient.sendNewHighScore(playSession.getScore());
				}
				
				else
				{
					Toast toast = Toast.makeText(getActivity(), "Server is offline", Toast.LENGTH_LONG);
					toast.show();
				}	
				
			onPause();
		}
		
		switch(playSession.getSubHP())
		{
			case 10: viewPort.getGameWorld().setXSubLocation(600);
					break;
				
			case 9: viewPort.getGameWorld().setXSubLocation(550);
					break;
					
			case 8: viewPort.getGameWorld().setXSubLocation(500);
					break;
			
			case 7: viewPort.getGameWorld().setXSubLocation(450);
					break;
			
			case 6: viewPort.getGameWorld().setXSubLocation(400);
					break;
					
			case 5: viewPort.getGameWorld().setXSubLocation(350);
					break;
			
			case 4: viewPort.getGameWorld().setXSubLocation(300);
					break;
					
			case 3: viewPort.getGameWorld().setXSubLocation(200);
					break;
					
			case 2: viewPort.getGameWorld().setXSubLocation(100);
					break;
					
			case 1: viewPort.getGameWorld().setXSubLocation(75);
					break;
		}	
		livesText.setText(Integer.toString(playSession.getSubHP()));
		commandIsCorrect = false;
		instructionPanel.setText(getCurrentCommand().getText());
		scoreText.setText(scoreToString());
		progressBar.setProgress(progressBar.getMax());
	}
	
	/**
	 * Checks if the command selected is already true
	 * @return true or false
	 */
	public boolean checkCommand(){
		if (getCurrentCommand().getControlType() == dial)
		{
			if (dial.getValueSelected() == (double)getCurrentCommand().getCompleteCondition())
			{
				return true;	
			}
		}
		if (getCurrentCommand().getControlType() == xSlider)
		{
			if (xSlider.getValueSelected() == (double)getCurrentCommand().getCompleteCondition())
			{
				return true;	
			}
		}
		if (getCurrentCommand().getControlType() == ySlider)
		{
			if (ySlider.getValueSelected() == (double)getCurrentCommand().getCompleteCondition())
			{
				return true;	
			}
		}
		return false;
	}

	/**
	 * Load the assets using the custom assetmanager
	 */
	private void LoadAssets()
	{
		//Loading assets for button
		myAssetManager.StoreAsset(button.getPicLoc(), button.getPicLoc());
		button.setActivated(true);
		myAssetManager.StoreAsset(button.getPicLoc(), button.getPicLoc());
		button.setActivated(false);
		
		//Loading assets for flipswitch
		myAssetManager.StoreAsset(flipSwitch.getPicLoc(), flipSwitch.getPicLoc());
		flipSwitch.setActivated(true);
		myAssetManager.StoreAsset(flipSwitch.getPicLoc(), flipSwitch.getPicLoc());
		flipSwitch.setActivated(false);
		
		//Loading assets for sliding switch
		myAssetManager.StoreAsset(slidingSwitch.getPicLoc(), slidingSwitch.getPicLoc());
		slidingSwitch.setActivated(true);
		myAssetManager.StoreAsset(slidingSwitch.getPicLoc(), slidingSwitch.getPicLoc());
		slidingSwitch.setActivated(false);
		
		//Loading assets for slider
		for (int i = 1; i <= 5; i++)
		{
			xSlider.setValueSelected(i);
			myAssetManager.StoreAsset(xSlider.getPicLoc(), xSlider.getPicLoc());
		}
		
		xSlider.setValueSelected(3);
		viewPort.getGameWorld().setScrollSpeed(0.8f);
		
		//Loading assets for ySlider
		for (int i = 1; i <= 5; i++)
		{
			ySlider.setValueSelected(i);
			myAssetManager.StoreAsset(ySlider.getPicLoc(), ySlider.getPicLoc());
		}
		
		ySlider.setValueSelected(1);
		
		//Loading assets for dial
		for (int i = 1; i <= 8; i++)
		{
			dial.setValueSelected(i);
			myAssetManager.StoreAsset(dial.getPicLoc(), dial.getPicLoc());
		}
		
		dial.setValueSelected(1);
		
		//load background image
		myAssetManager.StoreAsset("img/panel3.jpg", "backimg");
	}
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onPause()
	 * 
	 * The android pause method
	 */
	@Override
	public void onPause() {
		gameLoop.pause();
		if (bMusic.hasPlayed() == true){
			bMusic.pauseMusic();
		}
		if (getActivity().isFinishing()) {			
			loseFX.clearFX();
			if (bMusic.hasPlayed() == true){
				bMusic.destroyMusic();
			}
	}
		super.onPause();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onResume()
	 * 
	 * The android resume method
	 */
	@Override
	public void onResume() {
		gameLoop.resume();
		if (bMusic.hasPlayed() == true){
			bMusic.resumeMusic();
		}
		paused = false;
		pauseMenu.hide();
		super.onResume();
	}
 
	/**
	 * @return
	 * 
	 * returns the value of the pause flag
	 */
	public static boolean isPaused() {
		return paused;
	}
	/**
	 * @param npause
	 * toggles the value of the pause flag
	 */
	public static void setPaused(boolean npause)
	{
		paused = npause;
	}
	/**
	 * @return
	 * returns the current command
	 */
	public static Command getCurrentCommand() {
		return currentCommand;
	}

	/**
	 * @param currentCommand
	 * 
	 * sets the current command to the parameter
	 */
	public void setCurrentCommand(Command currentCommand) {
		MainGameFragment.currentCommand = currentCommand;
	}

	/**
	 * @return
	 * returns boolean value commandIsCorrect
	 */
	public static boolean iscCorrect() {
		return commandIsCorrect;
	}

	/**
	 * @param cCorrect
	 * sets the boolean value of cCorrect
	 */
	public static void setcCorrect(boolean cCorrect) {
		MainGameFragment.commandIsCorrect = cCorrect;
	}

	/**
	 * @return
	 * returns the game loop for this fragment
	 */
	public static GameLoop getGameLoop() {
		return gameLoop;
	}
	
	/**
	 * @return
	 * returns the background music player for this fragment
	 */
	public static BackgroundMusic getBackMusic(){
		return bMusic;
	}
}
