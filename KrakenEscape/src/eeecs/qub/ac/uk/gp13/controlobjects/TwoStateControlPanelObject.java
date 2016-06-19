package eeecs.qub.ac.uk.gp13.controlobjects;

public class TwoStateControlPanelObject extends ControlPanelObject
{
	private boolean activated;
	public TwoStateControlPanelObject(String iLabel, int iHeight, int iWidth, String iPicLoc, boolean iActivated)
	{
		super(iLabel, iHeight, iWidth, iPicLoc);
		activated = iActivated;
	}
	
	public boolean getActivated()
	{
		return activated;
	}
	
	public void setActivated(boolean newActivated)
	{
		activated = newActivated;
	}
}
