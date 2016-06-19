package eeecs.qub.ac.uk.gp13.calculations;

import android.widget.ImageView;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * The Polar Converter class contains several methods to assist in the conversion to and from polar co-ordinates
 * Method is also included to convert between degrees and radians
 *
 */
public class PolarConverter 
{
	/**
	 * Calculates the theta value of the polar co-ordinates
	 * 
	 * @param x - the x co-ordinate to be converted into a polar angle
	 * @param y - the y co-ordinate to be converted into a polar angle
	 * @param image - the image view of the dial, needed to find size
	 * @return - the value of theta in the polar co-ordinates
	 */
	public static double polarConverterAngle(double x, double y, ImageView image)
	{
		double out = 0; 
		
		double xValMiddle = image.getWidth() / 2;
		double yValMiddle = image.getHeight() / 2;
		
		if (y < yValMiddle)
		{
			if (x < xValMiddle)
			{
				double numerator = yValMiddle - y;
				double denominator = xValMiddle - x;
				double ratioOfOpptoAdj = (numerator / denominator);
				double psiVal = 0;
				if (ratioOfOpptoAdj < 1)
				{
					psiVal = Maclaurin.getArctanOfAngle(ratioOfOpptoAdj);
					
					out = Math.PI - psiVal;
				}
				
				else
				{
					//Switching x and y to make series converge
					ratioOfOpptoAdj = denominator/numerator;
					psiVal = Maclaurin.getArctanOfAngle(ratioOfOpptoAdj);
					
					out = (Math.PI / 2) + psiVal;
				}
			}
			
			else if (x > xValMiddle)
			{
				double numerator = yValMiddle - y;
				double denominator = x - xValMiddle;
				double ratioOfOpptoAdj = (numerator / denominator);
				double psiVal = 0;
				if (ratioOfOpptoAdj < 1)
				{
					out = Maclaurin.getArctanOfAngle(ratioOfOpptoAdj);
				}
				
				else
				{
					ratioOfOpptoAdj = denominator/numerator;
					psiVal = Maclaurin.getArctanOfAngle(ratioOfOpptoAdj);
					
					out = (Math.PI / 2) - psiVal;
				}
			}
			
			else if (x == xValMiddle)
			{
				out = Math.PI/2;
			}
		}
		
		else if (y > yValMiddle)
		{
			if (x < xValMiddle)
			{
				double numerator = y - yValMiddle;
				double denominator = xValMiddle - x;
				double ratioOfOpptoAdj = (numerator / denominator);
				double psiVal = 0;
				if (ratioOfOpptoAdj < 1)
				{
					psiVal = Maclaurin.getArctanOfAngle(ratioOfOpptoAdj);
					
					out = Math.PI + psiVal;
				}
				
				else
				{
					ratioOfOpptoAdj = denominator/numerator;
					psiVal = Maclaurin.getArctanOfAngle(ratioOfOpptoAdj);
					
					out = ((Math.PI * 3) / 2) - psiVal;
				}
			}
			
			else if (x > xValMiddle)
			{
				double numerator = y - yValMiddle;
				double denominator = x - xValMiddle;
				double ratioOfOpptoAdj = (numerator / denominator);
				double psiVal = 0;
				if (ratioOfOpptoAdj < 1)
				{
					psiVal = Maclaurin.getArctanOfAngle(ratioOfOpptoAdj);

					out = (2 * Math.PI) - psiVal;
				}
				
				else
				{
					ratioOfOpptoAdj = denominator/numerator;
					psiVal = Maclaurin.getArctanOfAngle(ratioOfOpptoAdj);
					
					out = ((Math.PI * 3) / 2) + psiVal;
				}
			}
			
			else if (x == xValMiddle)
			{
				out = (3 * Math.PI) / 2;
			}
		}
		
		return out;
	}

	/**
	 * Calculates the r value of the polar co-ordinates
	 * 
	 * @param x - the x co-ordinate to be converted into a polar radius
	 * @param y - the y co-ordinate to be converted into a polar radius
	 * @param image - the image view of the dial, needed to find size
	 * @return - the value of r in the polar co-ordinates
	 */
	public static double polarConverterRadius(double x, double y, ImageView image)
	{
		double out = 0;
		
		double xMid = image.getHeight()/2;
		double yMid = image.getWidth()/2;
		
		double xDiff = MathematicalModulus.getAbsoluteValue(xMid - x);
		double yDiff = MathematicalModulus.getAbsoluteValue(yMid - y);
		
		out = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
		
		return out;
	}

	/**
	 * Converts degrees to radians
	 * 
	 * @param angleIn - the angle being passed in in degrees
	 * @return - the equivalent value in radians
	 */
	public static double degreesToRad(double angleIn)
	{
		double ratio = angleIn / 360;
		double angleInRadians = 2 * Math.PI * ratio;
		
		return angleInRadians;
	}
	
	/**
	 * Converts radians to degrees
	 * 
	 * @param angleIn - the angle being passed in in radians
	 * @return - the equivalent value in degrees
	 */
	public static double radToDegrees(double angleIn)
	{
		double ratio = angleIn / (2*Math.PI);
		double angleInDegrees = ratio * 360;
		
		return angleInDegrees;
	}
}
