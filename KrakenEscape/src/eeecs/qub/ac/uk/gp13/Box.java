package eeecs.qub.ac.uk.gp13;

public class Box{
	public float x;
	public float y;
	
	public float width;
	public float height;
	
	//This is the constructor to make an empty box object
	//Y points up instead of down like a rect
	public Box() {
		x = 0;
		y = 0;
		width = 1;
		height = 1;
	}
	
	/**
	 * Constructor for Box
	 * 
	 * @param x x location of the box
	 * @param y y location of the box
	 * @param width width of the box
	 * @param height height of the box
	 */
	public Box(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}