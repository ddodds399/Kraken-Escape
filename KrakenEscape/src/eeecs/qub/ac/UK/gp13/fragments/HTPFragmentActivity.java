package eeecs.qub.ac.uk.gp13.fragments;

import eeecs.qub.ac.uk.gp13.R;
import eeecs.qub.ac.uk.gp13.SectionsPagerAdapter;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class HTPFragmentActivity extends FragmentActivity {
	
	//This is the location of some of the code we used
	//http://developer.android.com/reference/android/support/v4/app/FragmentPagerAdapter.html
	
	private SectionsPagerAdapter sectionsPagerAdapter;
	private ViewPager viewPager;
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 * 
	 * This must be included for android to know how to handle it because the How to Play fragment extends fragment activity
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		//To keep the screen full screen
		Window window = getWindow();
		window.requestFeature(Window.FEATURE_NO_TITLE);
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				
		setContentView(R.layout.fragment_pager);
		
		sectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(sectionsPagerAdapter);
	}
	
	
	//These are the three fragments that are called by the section pager adapter to show three sliding 'fragments' to the user
	
	
	/**
	 * Help class 1
	 * 
	 * This is the first of three pages that teaches the user how to play
	 */
	public static class Help1 extends Fragment 
	{
		public static final String ARG_SECTION_NUMBER = "section_number";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) 
		{
			View rootView = inflater.inflate(
					R.layout.frag_htp, container, false);

			TextView title = (TextView) rootView.findViewById(R.id.HTP_title_text_view);
			title.setText("How to Play?!");
			
			TextView htpText = (TextView) rootView.findViewById(R.id.HTP_text_view);
			htpText.setText("\n\nStep one is to look at the instruction bar in the middle of the screen. This will show you "
					+ "what you have to do in your current situation.\n\n Hit the corresponding button or turn the dial to the "
					+ "instruction that it's referring to. \n\nFor example, if the instruction is telling you to turn on the slider switch, "
					+ "you find corresponding control and you tap it to turn it on!");
			
			return rootView;
		}
	}
	
	/**
	 * Help class 2
	 * 
	 * This is the second of three pages that teaches the user how to play
	 */
	public static class Help2 extends Fragment 
	{
		public static final String ARG_SECTION_NUMBER = "section_number";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) 
		{
			View rootView = inflater.inflate(
					R.layout.frag_htp, container, false);
			
			TextView htpText = (TextView) rootView.findViewById(R.id.HTP_text_view);
			htpText.setText("Step two is to watch the timer bar. The timer is above the instruction bar, it decreases over time! "
					+ "This is the bar that indicates how long you have to answer your instruction!\n\n This time period decreases as "
					+ "the difficutlty increases. The faster you answer your instuction, the more points you get!");
			
			return rootView;
		}
	}
	
	/**
	 * Help class 3
	 *
	 * This is the third and final class that teaches the user how to play
	 */
	public static class Help3 extends Fragment 
	{
		public static final String ARG_SECTION_NUMBER = "section_number";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) 
		{
			View rootView = inflater.inflate(
					R.layout.frag_htp, container, false);
			
			TextView htpText = (TextView) rootView.findViewById(R.id.HTP_text_view);
			htpText.setText("The final feature is the score. The score is worked out depending on how long you take to "
					+ "answer a question and what difficulty you are on.\n\n If you answer lots of questions quickly you can earn a "
					+ "combo score! \n\nWhen the kraken has caught you, your score will be recorded and saved for future reference in "
					+ "the high score page which can be accessed from the main menu.");
			
			return rootView;
		}
	}
}
