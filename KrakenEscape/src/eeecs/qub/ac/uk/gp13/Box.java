package eeecs.qub.ac.uk.gp13;

class Box{
	public float x;
	public float y;
	
	public float width;
	public float height;
	
	public Box() {
		x = 0;
		y = 0;
		width = 1;
		height = 1;
	}
	
	public Box(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}