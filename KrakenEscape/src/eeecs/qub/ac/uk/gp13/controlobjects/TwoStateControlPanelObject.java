package eeecs.qub.ac.uk.gp13.controlobjects;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * The TwoStateControlPanelObject extends the ControlPanelObject
 * This is for ControlPanelObjects which only have two possible states and can have this represented by a boolean value
 *
 */
public class TwoStateControlPanelObject extends ControlPanelObject
{
	private boolean activated;
	public TwoStateControlPanelObject(String iLabel, int iHeight, int iWidth, String iPicLoc, boolean iActivated)
	{
		super(iLabel, iHeight, iWidth, iPicLoc);
		activated = iActivated;
	}
	
	/**
	 * This method gets whether the object is activated or not
	 * 
	 * @return - whether the object is activated or not
	 */
	public boolean getActivated()
	{
		return activated;
	}
	
	/**
	 * This method sets whether the object is activated or not
	 * 
	 * @param newActivated - the new value for the activation state of the object
	 */
	public void setActivated(boolean newActivated)
	{
		activated = newActivated;
	}
}
