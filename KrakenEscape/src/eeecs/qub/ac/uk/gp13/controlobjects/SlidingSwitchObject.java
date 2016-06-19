package eeecs.qub.ac.uk.gp13.controlobjects;

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
