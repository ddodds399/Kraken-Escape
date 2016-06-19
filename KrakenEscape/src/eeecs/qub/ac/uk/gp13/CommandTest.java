package eeecs.qub.ac.uk.gp13;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CommandTest extends Fragment {

	// Instance variables
	private Command currentCommand;
	private TextView commandLine, prob1, prob2, prob3, prob4;
	private int pressed;
	private Command[] commands;

	// onCreateView setting up view
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.command_tester, container, false);

		commandLine = (TextView) view.findViewById(R.id.textView1);
		prob1 = (TextView) view.findViewById(R.id.textView2);
		prob2 = (TextView) view.findViewById(R.id.textView3);
		prob3 = (TextView) view.findViewById(R.id.textView4);
		prob4 = (TextView) view.findViewById(R.id.textView5);

		Button button1 = (Button) view.findViewById(R.id.button1);
		Button button2 = (Button) view.findViewById(R.id.button2);
		Button button3 = (Button) view.findViewById(R.id.button3);
		Button button4 = (Button) view.findViewById(R.id.button4);

		// Setting up the random generator
		//RandSelect.setupRand();
		commands = RandSelect.getCommands();
		currentCommand = RandSelect.getRand();
		updateAll();

		// Button1 clickListener
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				pressed = 1;
				checkAnswer();
				updateAll();
			}
		});
		// Button2 clickListener
		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				pressed = 2;
				checkAnswer();
				updateAll();
			}
		});
		// Button3 clickListener
		button3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				pressed = 3;
				checkAnswer();
				updateAll();
			}
		});
		// Button4 clickListener
		button4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				pressed = 4;
				checkAnswer();
				updateAll();
			}
		});
		return view;
	}

	// Method to update all textViews
	public void updateAll() {
		RandSelect.totalProb();
		commandLine.setText(currentCommand.getText());
		prob1.setText(String.valueOf(commands[0].getProbability()
				/ RandSelect.getTotal() * 100)
				+ "%");
		prob2.setText(String.valueOf(commands[1].getProbability()
				/ RandSelect.getTotal() * 100)
				+ "%");
		prob3.setText(String.valueOf(commands[2].getProbability()
				/ RandSelect.getTotal() * 100)
				+ "%");
		prob4.setText(String.valueOf(commands[3].getProbability()
				/ RandSelect.getTotal() * 100)
				+ "%");
	}

	// Method to check button clicked against current command
	public void checkAnswer() {
		if (pressed == currentCommand.getCommandID()) {
			currentCommand = RandSelect.getRand();
		} else {
			currentCommand.incrProbability();
		}
	}
}
