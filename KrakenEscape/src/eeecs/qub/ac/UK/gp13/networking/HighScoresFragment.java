package eeecs.qub.ac.uk.gp13.networking;

import java.util.ArrayList;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import eeecs.qub.ac.uk.gp13.R;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * The HighScoresFragment extends Fragment
 * This class allows the user to check their highscores and includes a text view and a button in the layout
 *
 */
public class HighScoresFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.frag_leaderboard, container, false);
		
		//This text view displays the highscore list
		final TextView displayView = (TextView) view.findViewById(R.id.leaderboard_fragment_leaderboardText);
		displayView.setTextColor(Color.WHITE);
		//This sends a request to the server for the latest highscore list
		ImageView refreshButton = (ImageView) view.findViewById(R.id.leaderboard_fragment_refreshButton);
		
		refreshButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				refreshLeaderboard(displayView);
				
				Toast toast = Toast.makeText(getActivity(), "Highscores Refreshed", Toast.LENGTH_SHORT);
				toast.show();
			}
		});
		
		refreshLeaderboard(displayView);
		
		return view;
	}
	
	/**
	 * This method refreshes the display view which holds the list of highscores with the latest list from the server
	 * 
	 * @param displayView - the display view which is to display the highscores
	 */
	private void refreshLeaderboard(TextView displayView)
	{
		ArrayList<String> high = AppClient.AppClientRun();
		
		displayView.setText("");
		
		for(int i = 0; i < high.size(); i++)
		{
			displayView.append((i + 1) + " - " + high.get(i) + "\n");
		}
	}
}
