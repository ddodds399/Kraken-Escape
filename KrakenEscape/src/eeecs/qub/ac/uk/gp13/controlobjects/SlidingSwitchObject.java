package eeecs.qub.ac.uk.gp13.controlobjects;

/**
 * 
 * @author Josh McConnell 40105917
 *
 * The SlidingSwitchObject extends the TwoStateControlPanelObject
 * This creates a sliding switch which has two possible values
 * A TwoStateClickListener should be used in conjunction with this
 * 
 */
public class SlidingSwitchObject extends TwoStateControlPanelObject
{
	public SlidingSwitchObject(String iLabel, int iHeight, int iWidth)
	{
		super(iLabel, iHeight, iWidth, "img/SingleTouch/SlidingSwitchOff.png", false);
	}
	
	@Override
	public void setActivated(boolean newActivated)
	{
		if (newActivated == true)
		{
			super.setPicLoc("img/SingleTouch/SlidingSwitchOn.png");
			super.setActivated(true);
		}
		
		else if (newActivated == false)
		{
			super.setPicLoc("img/SingleTouch/SlidingSwitchOff.png");
			super.setActivated(false);
		}
	}
}
