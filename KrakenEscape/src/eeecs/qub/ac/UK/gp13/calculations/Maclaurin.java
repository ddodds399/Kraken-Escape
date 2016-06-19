package eeecs.qub.ac.uk.gp13.calculations;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * The Maclaurin class contains several static methods which each evaluate a different maclaurin series
 * Each method only carries out 100 iterations, this increases efficiency over the in built Math methods
 *
 */

public class Maclaurin 
{
	/**
	 * Calculates the trigonometric function sine of an angle
	 * 
	 * @param angleInRad - the angle in Radians to calculate the sine of
	 * @return - the sine of angleInRad
	 */
	public static double getSinOfAngle(double angleInRad)
	{
		double out = 0;
		
		for (int i = 0; i < 100; i++)
		{
			double sign = Math.pow((-1), i);
			double numerator = Math.pow(angleInRad, ((2 * i) + 1));
			double denominator = Factorial.findFactorial((2 * i) + 1);
			
			out = out + (sign * (numerator / denominator));
		}
		
		return out;
	}
	
	/**
	 * Calculates the trigonometric function cosine of an angle
	 * 
	 * @param angleInRad - angleInRad the angle in Radians to calculate the cosine of
	 * @return - the cosine of angleInRad
	 */
	public static double getCosOfAngle(double angleInRad)
	{
		double out = 0;
		
		for (int i = 0; i < 100; i++)
		{
			double sign = Math.pow((-1), i);
			double numerator = Math.pow(angleInRad, (2 * i));
			double denominator = Factorial.findFactorial(2 * i);
			
			out = out + (sign * (numerator / denominator));
		}
		
		return out;
	}
	
	/**
	 * Calculates the trigonometric function tangent of an angle
	 * 
	 * @param angleInRad - angleInRad the angle in Radians to calculate the tangent of
	 * @return - the tangent of angleInRad
	 */
	public static double getTanOfAngle(double angleInRad)
	{
		double out = 0; 
		
		double numerator = getSinOfAngle(angleInRad);
		double denominator = getCosOfAngle(angleInRad);
		
		out = numerator / denominator;
		
		return out;
	}

	/**
	 * Calculates the inverse trigonometric function arcTangent of an angle
	 * 
	 * @param ratioOfOppToAdj - the ratio of the opposite side of the triangle to the adjacent side, to see more on this look at the design for the dial listener and the Maclaurin proof included in the documentation
	 * @return - the arcTangent of ratioOfOppToAdj
	 */
	public static double getArctanOfAngle(double ratioOfOppToAdj)
	{
		double out = 0;
		
		for (int i = 0; i < 100; i++)
		{
			double sign = Math.pow((-1), i);
			double numerator = Math.pow(ratioOfOppToAdj, ((2 * i) + 1));
			double denominator = (2 * i) + 1;
			
			out = out + (sign * ( numerator / denominator));
		}
		
		return out;
	}
}
