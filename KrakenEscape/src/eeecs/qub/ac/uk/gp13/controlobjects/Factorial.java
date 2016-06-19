package eeecs.qub.ac.uk.gp13.controlobjects;

public class Factorial 
{
	//This method returns the factorial of the integer passed in in double format (i.e 5! returns 120)
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
