package eeecs.qub.ac.uk.gp13.controlobjects;

/**
 * 
 * @author Josh McConnell 40105917
 *
 * The ControlPanelObject constructor is the super class which is extended by each of the different objects which is to appear on the control panel
 * 
 */
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
	
	/**
	 * This method returns the label of the object
	 * 
	 * @return - the label
	 */
	public String getLabel()
	{
		return label;
	}
	
	/**
	 * This method returns the height of the object
	 * 
	 * @return - the height
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * This method returns the width of the object
	 * 
	 * @return - the width
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * This method returns a string which contains the picture location of the image to be displayed by a particular control object
	 * 
	 * @return - the picture location of the picture to be used on the control panel
	 */
	public String getPicLoc()
	{
		return picLoc;
	}
	
	/**
	 * This method changes the current label for a new one
	 * 
	 * @param newLabel - the new label
	 */
	public void setLabel(String newLabel)
	{
		label = newLabel;
	}
	
	/**
	 * This method changes the current picture location for a new one
	 * This is designed to be used whenver there is a change of state of the object
	 * 
	 * @param newPicLoc - the location of the new picture to be displayed
	 */
	public void setPicLoc(String newPicLoc)
	{
		picLoc = newPicLoc;
	}
}
