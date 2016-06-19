package eeecs.qub.ac.uk.gp13.controlobjects;

import eeecs.qub.ac.uk.gp13.calculations.MathematicalModulus;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * The DialObject constructor extends the MultiStateControlPanelObject
 * This creates a dial object which has 8 possible values
 * A DialTouchListener should be used in conjunction with this
 *
 */
public class DialObject extends MultiStateControlPanelObject
{
	double[] thetaValue = new double[9];
	public DialObject(String iLabel, int iHeight, int iWidth)
	{
		super(iLabel, iHeight, iWidth, "img/Dial/Dial2Pos1.png", 1);
		thetaValue[0] = Math.PI;
		thetaValue[1] = 0.75 * Math.PI;
		thetaValue[2] = Math.PI/2;
		thetaValue[3] = Math.PI/4;
		thetaValue[4] = 0;
		thetaValue[5] = 1.75 * Math.PI;
		thetaValue[6] = 1.5 * Math.PI;
		thetaValue[7] = 1.25 * Math.PI;
		thetaValue[8] = 2 * Math.PI;
	}
	
	/**
	 * This method gets the array which contains all of the different positions it is possible for the dial to register
	 * 
	 * @return - the array of theta values
	 */
	public double[] getThetaValue()
	{
		return thetaValue;
	}
	
	/**
	 * This method calculates the closest value of theta to the inputed touch location
	 * This then calls the setValueSelected method to change the current selected value
	 * 
	 * @param touchThetaValue - the value of the touch position in radians (use polar converter class)
	 */
	public void findClosestPosition(double touchThetaValue)
	{
		double[] difference = new double[thetaValue.length];
		for (int i = 0; i < thetaValue.length; i++)
		{
			difference[i] = MathematicalModulus.getAbsoluteValue(thetaValue[i] - touchThetaValue);
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
		
		if (closestPos == 8)
		{
			setValueSelected(5);
		}
		else
		{
			setValueSelected(closestPos + 1);
		}
	}
	
	@Override
	public void setValueSelected(int newValueSelected)
	{
		super.setPicLoc("img/Dial/Dial2Pos" + newValueSelected + ".png");
		
		super.setValueSelected(newValueSelected);
	}
}
