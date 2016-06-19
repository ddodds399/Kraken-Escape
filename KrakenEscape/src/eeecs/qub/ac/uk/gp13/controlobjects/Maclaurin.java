package eeecs.qub.ac.uk.gp13.controlobjects;

public class Maclaurin 
{
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
	
	public static double getTanOfAngle(double angleInRad)
	{
		double out = 0; 
		
		double numerator = getSinOfAngle(angleInRad);
		double denominator = getCosOfAngle(angleInRad);
		
		out = numerator / denominator;
		
		return out;
	}
	
	public static double getArctanOfAngle(double ratioOfOppToAdj)
	{
		double out = 0;
		
		for (int i = 0; i < 100000; i++)
		{
			double sign = Math.pow((-1), i);
			double numerator = Math.pow(ratioOfOppToAdj, ((2 * i) + 1));
			double denominator = (2 * i) + 1;
			
			out = out + (sign * ( numerator / denominator));
		}
		
		return out;
	}
}
