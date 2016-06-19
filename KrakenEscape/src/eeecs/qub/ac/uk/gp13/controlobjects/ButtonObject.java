package eeecs.qub.ac.uk.gp13.controlobjects;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * The ButtonObject constructor extends the TwoStateControlPanelObject
 * This includes the code to initalise the variable
 * The code to allow the button to be changed is also here
 *
 */
public class ButtonObject extends TwoStateControlPanelObject
{
	public ButtonObject(String iLabel, int iHeight, int iWidth)
	{
		super(iLabel, iHeight, iWidth, "img/SingleTouch/ButtonOff.png", false);
	}
	
	@Override
	public void setActivated(boolean newActivated)
	{
		if (newActivated == true)
		{
			super.setPicLoc("img/SingleTouch/ButtonOn.png");
			super.setActivated(true);
		}
		
		else if (newActivated == false)
		{
			super.setPicLoc("img/SingleTouch/ButtonOff.png");
			super.setActivated(false);
		}
	}
}
