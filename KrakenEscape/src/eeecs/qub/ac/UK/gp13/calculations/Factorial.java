package eeecs.qub.ac.uk.gp13.calculations;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * The Factorial class contains a static method which uses an iterative technique to calculate the factorial
 * Decision was made not to use recursion for this method as it was no more efficient
 *
 */

public class Factorial 
{
	/**
	 * Finds the factorial of an integer
	 * 
	 * @param getFactorialOf - the number to calculate the factorial of
	 * @return - the factorial of getFactorialOf (example getFactorial = 5 will return 5! = 120)
	 */
	public static double findFactorial(int getFactorialOf)
	{
		double out = 1; 
		
		for (int i = getFactorialOf; i > 0; i--)
		{
			out = out * i;
		}
		
		return out;
	}
}
