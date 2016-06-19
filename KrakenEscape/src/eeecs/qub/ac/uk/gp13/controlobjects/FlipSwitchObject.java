package eeecs.qub.ac.uk.gp13.controlobjects;

public class FlipSwitchObject extends TwoStateControlPanelObject
{
	public FlipSwitchObject(String iLabel, int iHeight, int iWidth)
	{
		super(iLabel, iHeight, iWidth, "img/SingleTouch/FlipSwitchOff.png", false);
	}
	
	@Override
	public void setActivated(boolean newActivated)
	{
		if (newActivated == true)
		{
			super.setPicLoc("img/SingleTouch/FlipSwitchOn.png");
			super.setActivated(true);
		}
		
		else if (newActivated == false)
		{
			super.setPicLoc("img/SingleTouch/FlipSwitchOff.png");
			super.setActivated(false);
		}
	}
}
