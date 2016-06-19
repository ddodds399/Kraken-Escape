package eeecs.qub.ac.uk.gp13.controlobjects;

public class MultiStateControlPanelObject extends ControlPanelObject
{
	int valueSelected;
	public MultiStateControlPanelObject(String iLabel, int iHeight, int iWidth, String iPicLoc, int iValueSelected)
	{
		super(iLabel, iHeight, iWidth, iPicLoc);
		valueSelected = iValueSelected;
	}
	
	public int getValueSelected()
	{
		return valueSelected;
	}
	
	public void setValueSelected(int newValueSelected)
	{
		valueSelected = newValueSelected;
	}
}
