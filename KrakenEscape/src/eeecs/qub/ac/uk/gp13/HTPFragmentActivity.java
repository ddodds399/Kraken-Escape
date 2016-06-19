package eeecs.qub.ac.uk.gp13;

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
	
	//http://developer.android.com/reference/android/support/v4/app/FragmentPagerAdapter.html
	
	SectionsPagerAdapter sectionsPagerAdapter;
	ViewPager viewPager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
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
			htpText.setText("\n\nStep one is to look at the instruction panel in the middle of the screen. This will show you "
					+ "what you have to do in the current screen.\n\n You then find the control panel button or dial that the "
					+ "instruction is referring to. \n\nFor example if the instruction is telling you to turn on a control button "
					+ "you find the button at the bottom that is called the control button and you tap it to turn it on!");
			
			return rootView;
		}
	}
	
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
			htpText.setText("Step two is the timer bar, there is a timer bar at the top of the screen that decreases over time. "
					+ "This is the bar that tells you how long you have to answer your instruction!\n\n This time period decreases as "
					+ "the difficutlty increases. The faster you answer your instuction the more points you get!");
			
			return rootView;
		}
	}
	
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
			htpText.setText("The third and final step is the score. The score is worked out depending on how long you took to "
					+ "answer a question and what difficulty you are on.\n\n If you answer lots of questions quickly you can earn a "
					+ "combo score! \n\nWhen the kraken has caught you, your score will be recorded and saved for future reference in "
					+ "the high score page which can be accessed from the main screen");
			
			return rootView;
		}
	}
}
