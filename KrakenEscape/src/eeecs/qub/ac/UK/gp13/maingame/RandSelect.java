package eeecs.qub.ac.uk.gp13.maingame;

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

	/**
	 *  Method for setting up command text and adding them to an array using the createArray method
	 * @param iButton
	 * @param iDial
	 * @param iFlip
	 * @param iSliding
	 * @param iXSlider
	 * @param iYSlider
	 */
	public static void setupRand(ButtonObject iButton, DialObject iDial,
			FlipSwitchObject iFlip, SlidingSwitchObject iSliding,
			XSliderObject iXSlider, YSliderObject iYSlider) {

		Command c1 = new Command("Set dial to black", 1, iDial, 1);
		Command c2 = new Command("Set dial to turquoise", 2, iDial, 2);
		Command c3 = new Command("Set dial to orange", 3, iDial, 3);
		Command c4 = new Command("Set dial to yellow", 4, iDial, 4);
		Command c5 = new Command("Set dial to pink", 5, iDial, 5);
		Command c6 = new Command("Set dial to red", 6, iDial, 6);
		Command c7 = new Command("Set dial to blue", 7, iDial, 7);
		Command c8 = new Command("Set dial to green", 8, iDial, 8);
		Command c9 = new Command("Set horizontal slider to red", 9, iXSlider, 1);
		Command c10 = new Command("Set horizontal slider to turquoise", 10, iXSlider, 2);
		Command c11 = new Command("Set horizontal slider to green", 11, iXSlider, 3);
		Command c12 = new Command("Set horizontal slider to yellow", 12, iXSlider, 4);
		Command c13 = new Command("Set horizontal slider to orange", 13, iXSlider, 5);
		Command c14 = new Command("Flip square switch", 14, iButton);
		Command c15 = new Command("Flip arrow switch", 15, iFlip);
		Command c16 = new Command("Slide slider switch",16,iSliding);
		Command c17 = new Command("Set vertical slider to orange", 17, iYSlider, 1);
		Command c18 = new Command("Set vertical slider to yellow", 18, iYSlider, 2);
		Command c19 = new Command("Set vertical slider to green", 19, iYSlider, 3);
		Command c20 = new Command("Set vertical slider to turquoise", 20, iYSlider, 4);
		Command c21 = new Command("Set vertical slider to red", 21, iYSlider, 5);


		commands = createArray(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21);

	}

	// Create and populate an array of commands
	public static Command[] createArray(Command command0, Command command1,
			Command command2, Command command3, Command command4, Command command5, Command command6, Command command7, Command command8, Command command9, 
			Command command10, Command command11, Command command12, Command command13, Command command14, Command command15, Command command16,
			Command command17, Command command18, Command command19, Command command20) {

		commands = new Command[21];

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
		commands[10] = command10;
		commands[11] = command11;
		commands[12] = command12;
		commands[13] = command13;
		commands[14] = command14;
		commands[15] = command15;
		commands[16] = command16;
		commands[17] = command17;
		commands[18] = command18;
		commands[19] = command19;
		commands[20] = command20;

		return commands;
	}

	// Calculate the total probability
	public static void totalProb() {
		total = 0;
		for (Command command : commands) {
			total = total + command.getProbability();
		}
	}

	/**
	 * Get a random command from the array of commands and return as a command
	 * @return currentCommand
	 */
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
