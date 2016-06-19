package eeecs.qub.ac.uk.gp13.controlobjects;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * The MultiStateControlPanelObject extends the ControlPanelObject
 * This creates a control panel object which can have any number of possible values
 *
 */
public class MultiStateControlPanelObject extends ControlPanelObject
{
	int valueSelected;
	public MultiStateControlPanelObject(String iLabel, int iHeight, int iWidth, String iPicLoc, int iValueSelected)
	{
		super(iLabel, iHeight, iWidth, iPicLoc);
		valueSelected = iValueSelected;
	}
	
	/**
	 * This method returns the current value selected
	 * 
	 * @return - the current value selected
	 */
	public int getValueSelected()
	{
		return valueSelected;
	}
	
	/**
	 * This method sets the valueSelected to the new value that is passed in
	 * 
	 * @param newValueSelected - the value to set valueSelected to
	 */
	public void setValueSelected(int newValueSelected)
	{
		valueSelected = newValueSelected;
	}
}
