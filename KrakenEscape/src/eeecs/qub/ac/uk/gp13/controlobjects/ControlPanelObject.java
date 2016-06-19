package eeecs.qub.ac.uk.gp13.controlobjects;

public class ControlPanelObject 
{
	private String label, picLoc;
	private int height, width;
	public ControlPanelObject(String iLabel, int iHeight, int iWidth, String iPicLoc)
	{
		label = iLabel;
		picLoc = iPicLoc;
		height = iHeight;
		width = iWidth;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public String getPicLoc()
	{
		return picLoc;
	}
	
	public void setLabel(String newLabel)
	{
		label = newLabel;
	}
	
	public void setPicLoc(String newPicLoc)
	{
		picLoc = newPicLoc;
	}
}
