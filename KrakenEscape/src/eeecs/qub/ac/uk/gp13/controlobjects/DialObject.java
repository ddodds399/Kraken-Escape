package eeecs.qub.ac.uk.gp13.controlobjects;


public class DialObject extends MultiStateControlPanelObject
{
	double[] thetaValue = new double[8];
	public DialObject(String iLabel, int iHeight, int iWidth)
	{
		//This creates the object without colored reference dots
		//super(iLabel, iHeight, iWidth, "img/DialPos1.png", 1);
		
		//This creates the object with colored reference dots
		super(iLabel, iHeight, iWidth, "img/Dial/Dial2Pos1.png", 1);
		thetaValue[0] = Math.PI;
		thetaValue[1] = 0.75 * Math.PI;
		thetaValue[2] = Math.PI/2;
		thetaValue[3] = Math.PI/4;
		thetaValue[4] = 0;
		thetaValue[5] = 1.75 * Math.PI;
		thetaValue[6] = 1.5 * Math.PI;
		thetaValue[7] = 1.25 * Math.PI;
	}
	
	public double[] getThetaValue()
	{
		return thetaValue;
	}
	
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
		
		setValueSelected(closestPos + 1);
	}
	
	@Override
	public void setValueSelected(int newValueSelected)
	{
		//Reference dots not included
		//super.setPicLoc("img/DialPos" + newValueSelected + ".png");
		
		//Reference dots included
		super.setPicLoc("img/Dial/Dial2Pos" + newValueSelected + ".png");
		
		super.setValueSelected(newValueSelected);
	}
}
