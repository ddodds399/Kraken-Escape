package eeecs.qub.ac.uk.gp13.controlobjects;

import eeecs.qub.ac.uk.gp13.calculations.MathematicalModulus;
import android.widget.ImageView;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * The YSliderObject constructor extends the MultiStateControlPanelObject
 * This creates a YSliderObject which has 5 possible values
 * A YSliderTouchListener should be used in conjunction with this
 *
 */
public class YSliderObject extends MultiStateControlPanelObject
{
	ImageView image;
	public YSliderObject(String iLabel, int iHeight, int iWidth, ImageView iImage)
	{
		super(iLabel, iHeight, iWidth, "img/YSlider/SliderPos1.png", 1);
		image = iImage;
	}
	
	/**
	 * This method calculates which position is closest to the inputed touch location
	 * 
	 * @param touchYValue - the value of the Y touch position 
	 */
	public void findClosestPosition(double touchYValue)
	{
		double[] difference = new double[5];
		for(int i = 0; i < difference.length; i++)
		{
			double pos = i * (image.getHeight() / 4);
			difference[i] = MathematicalModulus.getAbsoluteValue(touchYValue - pos);
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
		super.setPicLoc("img/YSlider/SliderPos" + newValueSelected + ".png");
		super.setValueSelected(newValueSelected);
	}
}

