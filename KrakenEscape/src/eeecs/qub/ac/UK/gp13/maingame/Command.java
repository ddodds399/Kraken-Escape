package eeecs.qub.ac.uk.gp13.maingame;

import eeecs.qub.ac.uk.gp13.controlobjects.ControlPanelObject;

public class Command {

	// Instance Variables
	private int commandID;
	private String text;
	private double probability;
	private ControlPanelObject controlType;
	private boolean activated;
	private int completeCondition;
	
	/**
	 * Constructor for two state control objects
	 * @param commandText
	 * @param ID
	 * @param cObject
	 */
	public Command(String commandText, int ID, ControlPanelObject cObject) {
		commandID = ID;
		text = commandText;
		probability = 1.00;
		activated = false;
		controlType = cObject;
	}
	
	/**
	 * Constructor for multi-state control objects
	 * @param commandText
	 * @param ID
	 * @param cObject
	 * @param cCondition
	 */
	public Command(String commandText, int ID, ControlPanelObject cObject, int cCondition) {
		commandID = ID;
		text = commandText;
		probability = 1.00;
		completeCondition = cCondition;
		controlType = cObject;
	}
	
	// Old constructor pending removal
	public Command(String commandText, int ID) {
		commandID = ID;
		text = commandText;
		probability = 1.00;
	
	}

	// Increase a command's probability by 1
	public void incrProbability() {
		this.setProbability(this.getProbability() + 1);
	}

	// Decrease a command's probability by 1
	public void decrProbability() {
		this.setProbability(this.getProbability() - 1);
	}

	// Getters and setters
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double d) {
		this.probability = d;
	}

	public int getCommandID() {
		return commandID;
	}

	public void setCommandID(int commandID) {
		this.commandID = commandID;
	}

	public ControlPanelObject getControlType() {
		return controlType;
	}

	public void setControlType(ControlPanelObject controlType) {
		this.controlType = controlType;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public int getCompleteCondition() {
		return completeCondition;
	}

	public void setCompleteCondition(int completeCondition) {
		this.completeCondition = completeCondition;
	}
	
	

}
