package eeecs.qub.ac.uk.gp13.controlobjects;

import android.widget.ImageView;

public class PolarConverter 
{
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
}
