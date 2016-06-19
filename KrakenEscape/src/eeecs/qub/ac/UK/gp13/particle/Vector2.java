package eeecs.qub.ac.uk.gp13.particle;

/**
 * 
 * @author Josh McConnell 40105917
 *
 * The majority of the code in the particle systems package is adapted from the demos particle code
 * The Vector2 constructor is used to create a 2-Dimensional vector object
 * 
 */
public class Vector2 
{
	public final static Vector2 Zero = new Vector2(0, 0);
	
	public float x;
	public float y;
	
	public Vector2()
	{
		
	}
	
	public Vector2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2(Vector2 other)
	{
		this.x = other.x;
		this.y = other.y;
	}
	
	/**
	 * This method checks to see if the vector is a 0 vector, and if so it returns true
	 * 
	 * @return - Whether the vector is a zero vector or not
	 */
	public boolean isZero()
	{
		boolean returnable = (x == 0.0f && y == 0.0f);
		
		return returnable;
	}
	
	/**
	 * This method calculates the length of the vector using a Pythagorean method, e.g |V|
	 * 
	 * @return - the absolute length of the vector
	 */
	public float length()
	{
		float xSquared = (float) Math.pow(x, 2);
		float ySquared = (float) Math.pow(y, 2);
		
		float length = (float) Math.sqrt(xSquared + ySquared);
		
		return length;
	}
	
	/**
	 * This method returns the length of the vector squared e.g |V|^2
	 * 
	 * @return - the absolute length of the vector squared
	 */
	public float lengthSqaured()
	{
		float xSquared = (float) Math.pow(x, 2);
		float ySquared = (float) Math.pow(y, 2);
		
		float lengthSquared = xSquared + ySquared;
		
		return lengthSquared;
	}
	
	/**
	 * This method sets the x and y values of the vector to a new set of values
	 * 
	 * @param x - The new x value for the Vector2
	 * @param y - The new y value for the Vector2
	 */
	public void set(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void set(Vector2 other)
	{
		this.x = other.x;
		this.y = other.y;
	}
	
	/**
	 * This method adds one vector to another
	 * 
	 * @param x - The x value of the vector to add
	 * @param y - The y value of the vector to add
	 */
	public void add(float x, float y)
	{
		this.x += x;
		this.y += y;
	}
	
	public void add(Vector2 other)
	{
		this.x += other.x;
		this.y += other.y;
	}
	
	/**
	 * This method subtracts one vector from another
	 * 
	 * @param x - The x value of the vector to be subtracted
	 * @param y - The y value of the vector to be subtracted
	 */
	public void subtract(float x, float y)
	{
		this.x -= x;
		this.y -= y;
	}
	
	public void subtract(Vector2 other)
	{
		this.x -= other.x;
		this.y -= other.y;
	}
	
	/**
	 * This method multiplies the vector by a scalar value
	 *   
	 * @param scalar - the scalar to multiply the vector by
	 */
	public void multiply(float scalar)
	{
		x *= scalar;
		y *= scalar;
	}
	
	/**
	 * This method divides the vector by a scalar value
	 * 
	 * @param scalar - the scalar to divide the vector by
	 */
	public void divide(float scalar)
	{
		x /= scalar;
		y /= scalar;
	}
	
	/**
	 * This method normalises the vector 
	 */
	public void normalise()
	{
		float length = length();
		if (length != 0)
		{
			x /= length;
			y /= length;
		}
	}
}
