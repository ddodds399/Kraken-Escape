package eeecs.qub.ac.uk.gp13.controlobjects;

public class MathematicalModulus 
{
	public static double getAbsoluteValue(double inValue)
	{
		if(inValue < 0)
		{
			inValue = inValue * (-1);
		}
		
		return inValue;
	}
}
