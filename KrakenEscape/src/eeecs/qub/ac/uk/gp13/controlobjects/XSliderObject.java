package eeecs.qub.ac.uk.gp13.controlobjects;

import eeecs.qub.ac.uk.gp13.calculations.MathematicalModulus;
import android.widget.ImageView;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * The XSliderObject constructor extendsm the MultiStateControlPanelObject
 * This creates an x slider object which has 5 possible values
 * A XSliderTouchListener should be used in conjunction with this
 *
 */
public class XSliderObject extends MultiStateControlPanelObject
{
	ImageView image;
	public XSliderObject(String iLabel, int iHeight, int iWidth, ImageView iImage)
	{
		super(iLabel, iHeight, iWidth, "img/XSlider/SliderPos1.png", 1);
		image = iImage;
	}
	
	/**
	 * This method calculates which position is closest to the inputed touch location
	 * 
	 * @param touchXValue - the value of the x touch position 
	 */
	public void findClosestPosition(double touchXValue)
	{
		double[] difference = new double[5];
		for(int i = 0; i < difference.length; i++)
		{
			double pos = i * (image.getWidth() / 4);
			difference[i] = MathematicalModulus.getAbsoluteValue(touchXValue - pos);
		}
		
		int closestPos = 0;
		double smallestValue = difference[0];
		
		for (int i = 0; i < difference.length; i++)
		{
			if (difference[i] < smallestValue)
			{
				closestPos = i;
				smallestValue = difference[i];
			}
		}
		
		setValueSelected(closestPos + 1);
	}
	
	@Override
	public void setValueSelected(int newValueSelected)
	{
		super.setPicLoc("img/XSlider/SliderPos" + newValueSelected + ".png");
		super.setValueSelected(newValueSelected);
	}
}
