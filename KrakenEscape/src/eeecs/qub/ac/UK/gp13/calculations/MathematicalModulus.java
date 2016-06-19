package eeecs.qub.ac.uk.gp13.calculations;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * The Mathematical Modulus class contains a static method which calculates the mathematical absolute value of any value passed in
 *
 */
public class MathematicalModulus 
{
	/**
	 * Finds the absolute value of a double
	 * 
	 * @param inValue - the double to find the absolute value of
	 * @return - the absolute value of inValue
	 */
	public static double getAbsoluteValue(double inValue)
	{
		if(inValue < 0)
		{
			inValue = inValue * (-1);
		}
		
		return inValue;
	}
}
