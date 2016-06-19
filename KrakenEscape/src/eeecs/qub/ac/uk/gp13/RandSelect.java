package eeecs.qub.ac.uk.gp13;

import java.util.Random;

import eeecs.qub.ac.uk.gp13.controlobjects.ButtonObject;
import eeecs.qub.ac.uk.gp13.controlobjects.DialObject;
import eeecs.qub.ac.uk.gp13.controlobjects.FlipSwitchObject;
import eeecs.qub.ac.uk.gp13.controlobjects.SlidingSwitchObject;
import eeecs.qub.ac.uk.gp13.controlobjects.XSliderObject;
import eeecs.qub.ac.uk.gp13.controlobjects.YSliderObject;

public class RandSelect {

	// Instance variables
	private static double total = 0;
	private static Random rand = new Random();
	private static Command[] commands;
	private static Command lastCommand = null, currentCommand = null;

	// Method for setting up command text and adding them to an array using
	// the createArray() method
	public static void setupRand(ButtonObject iButton, DialObject iDial,
			FlipSwitchObject iFlip, SlidingSwitchObject iSliding,
			XSliderObject iXSlider, YSliderObject iYSlider) {

		Command c1 = new Command("Set dial to red", 1, iDial, 6);
		Command c2 = new Command("Set dial to green", 2, iDial, 8);
		Command c3 = new Command("Set dial to pink", 3, iDial, 5);
		Command c4 = new Command("Set horizontal slider to yellow", 4, iXSlider, 4);
		Command c5 = new Command("Set horizontal slider to green", 5, iXSlider, 3);
		Command c6 = new Command("Flip square switch", 6, iButton);
		Command c7 = new Command("Flip arrow switch", 7, iFlip);
		Command c8 = new Command("Slide slider switch",8,iSliding);
		Command c9 = new Command("Set vertical slider to red", 9, iYSlider, 5);
		Command c10 = new Command("Set vertical slider to green", 10, iYSlider, 3);

		commands = createArray(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);

	}

	// Create and populate an array of commands
	public static Command[] createArray(Command command0, Command command1,
			Command command2, Command command3, Command command4, Command command5, Command command6, Command command7, Command command8, Command command9) {

		commands = new Command[10];

		commands[0] = command0;
		commands[1] = command1;
		commands[2] = command2;
		commands[3] = command3;
		commands[4] = command4;
		commands[5] = command5;
		commands[6] = command6;
		commands[7] = command7;
		commands[8] = command8;
		commands[9] = command9;

		return commands;
	}

	// Calculate the total probability
	public static void totalProb() {
		total = 0;
		for (Command command : commands) {
			total = total + command.getProbability();
		}
	}

	// Get a random command from the array of commands and return as a command
	public static Command getRand() {

		while (lastCommand == currentCommand) {
			totalProb();

			int iTotal = (int) total;
			int index = rand.nextInt(iTotal);
			double sum = 0;
			int i = 0;

			while (sum < index) {
				sum = sum + commands[i++].getProbability();
				if (i == commands.length) {
					i = 0;
				}
			}

			currentCommand = commands[i];
		}
		lastCommand = currentCommand;
		return currentCommand;

	}

	// Command array getter
	public static Command[] getCommands() {
		return commands;
	}

	// Total getter
	public static double getTotal() {
		return total;
	}

}
