package eeecs.qub.ac.uk.gp13;

import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import eeecs.qub.ac.uk.gp13.controlobjects.ButtonObject;
import eeecs.qub.ac.uk.gp13.controlobjects.DialObject;
import eeecs.qub.ac.uk.gp13.controlobjects.FlipSwitchObject;
import eeecs.qub.ac.uk.gp13.controlobjects.SlidingSwitchObject;
import eeecs.qub.ac.uk.gp13.controlobjects.XSliderObject;
import eeecs.qub.ac.uk.gp13.controlobjects.YSliderObject;
import eeecs.qub.ac.uk.gp13.listeners.DialTouchListener;
import eeecs.qub.ac.uk.gp13.listeners.TwoStateClickListener;
import eeecs.qub.ac.uk.gp13.listeners.XSliderTouchListener;
import eeecs.qub.ac.uk.gp13.RandSelect;
import eeecs.qub.ac.uk.gp13.listeners.YSliderTouchListener;

public class MainGameFragment extends Fragment {
	
	private GameLoop gameLoop;
	MyAssetManager myAssetManager;
	
	//The things for Josh
	public AssetManager assetManager;
	private ImageView buttonV, flipSwitchV, slidingSwitchV, xSliderV, ySliderV, dialV;
	private ButtonObject button;
	private FlipSwitchObject flipSwitch;
	private SlidingSwitchObject slidingSwitch;
	private XSliderObject xSlider;
	private YSliderObject ySlider;
	private DialObject dial;
	private Vibrator vib;
	private ProgressBar pb;
	
	private InstructionPanel instructionPanel;
	private ViewPort viewPort;
	
	private static Command currentCommand;
	private static boolean cCorrect;
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		
		gameLoop = new GameLoop(30) {
			
			@Override
			public void doUpdate() {
				//put update code here
				pb.setProgress(pb.getProgress() - 1);
				viewPort.getGameWorld().update();
				
			}
			
			@Override
			public void doDraw() {
				
				//This is a method that allows you to change what thread you run things on
				Utils.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						//Put draw code here
						if (iscCorrect() || pb.getProgress() == 0){
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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.fragment_main_game, container, false);
		
		vib = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
		
		//get a handle on the custom viewport object
		//this creates an instance of the object too
		//In this object there is an instance of the game world, the actual scrolling bitmap
		viewPort = (ViewPort) view.findViewById(R.id.ViewPort);
		
		//Link to XML
		buttonV = (ImageView) view.findViewById(R.id.all_control_object_button);
		flipSwitchV = (ImageView) view.findViewById(R.id.all_control_object_flipswitch);
		slidingSwitchV = (ImageView) view.findViewById(R.id.all_control_object_slidingswitch);
		xSliderV = (ImageView) view.findViewById(R.id.all_control_object_xslider);
		dialV = (ImageView) view.findViewById(R.id.all_control_object_dial);
		ySliderV = (ImageView) view.findViewById(R.id.all_control_object_yslider);
		
		instructionPanel = (InstructionPanel)view.findViewById(R.id.instructionPanel);
		pb = (ProgressBar)view.findViewById(R.id.progressBar1);
		pb.setProgress(pb.getMax());
		
		//Create Objects
		button = new ButtonObject("Test Button", 0, 0);
		flipSwitch = new FlipSwitchObject("Test FlipSwitch", 0, 0);
		slidingSwitch = new SlidingSwitchObject("Test SlidingSwitch", 0, 0);
		xSlider = new XSliderObject("Test Slider", 0, 0, xSliderV);
		ySlider = new YSliderObject("Test YSlider", 0, 0, ySliderV);
		dial = new DialObject("Test Dial", 0, 0);
		
		cCorrect = false;
		
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
		
		//To Josh
		//This is how you use our custom asset manager
		//myAssetManager.StoreAsset("img/ARGB_8888.png", "defaultImage");
		//myAssetManager.StoreAsset("txt/welcome.txt", "defaultText");
		//The first value is the location of the asset in the assets folder and the second name is the name you wanna give to it
		//If you want to know how to get a handle on it in this fragment look at the bottom of the on create method
		//You can also go looking around the code for it etc
		//
		//This is how you display assets that have been stored in the asset manager
		//Bitmap bitmap = myAssetManager.LoadBitmap("defaultImage");
		//and the same goes for text files if you want to for some reason
		//String text = myAssetManager.LoadText("defaultText");
		//
		//We will eventually put all the loading in a loading screen so we just have to access them here but that is for future us to do
		
		
		//Set initial imageviews
		buttonV.setImageBitmap(displayButton);
		flipSwitchV.setImageBitmap(displayFlipSwitch);
		slidingSwitchV.setImageBitmap(displaySlidingSwitch);
		xSliderV.setImageBitmap(displaySlider);
		dialV.setImageBitmap(displayDial);
		ySliderV.setImageBitmap(displayYSlider);
		
		//Set listeners
		
		buttonV.setOnClickListener(new TwoStateClickListener(vib, button, buttonV, myAssetManager));
		flipSwitchV.setOnClickListener(new TwoStateClickListener(vib, flipSwitch, flipSwitchV, myAssetManager));
		slidingSwitchV.setOnClickListener(new TwoStateClickListener(vib, slidingSwitch, slidingSwitchV, myAssetManager));
		xSliderV.setOnTouchListener(new XSliderTouchListener(vib, xSlider, xSliderV, myAssetManager));
		ySliderV.setOnTouchListener(new YSliderTouchListener(vib, ySlider, ySliderV, myAssetManager));
		dialV.setOnTouchListener(new DialTouchListener(vib, dial, dialV, myAssetManager));
		
		return view;
	}
	
	//Selecting a new random command and setting the instructionPanel
	public void newCommand() {
		setCurrentCommand(RandSelect.getRand());
		cCorrect = false;
		instructionPanel.setText(getCurrentCommand().getText());
		pb.setProgress(pb.getMax());
		
	}

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
		
		xSlider.setValueSelected(1);
		
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
	}
	
	@Override
	public void onPause() {
		gameLoop.pause();

		super.onPause();
	}
	
	@Override
	public void onResume() {
		gameLoop.resume();

		super.onResume();
	}
 
	public static Command getCurrentCommand() {
		return currentCommand;
	}

	public void setCurrentCommand(Command currentCommand) {
		MainGameFragment.currentCommand = currentCommand;
	}

	public static boolean iscCorrect() {
		return cCorrect;
	}

	public static void setcCorrect(boolean cCorrect) {
		MainGameFragment.cCorrect = cCorrect;
	}	
}
