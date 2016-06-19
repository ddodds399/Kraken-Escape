package eeecs.qub.ac.uk.gp13.particle;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * The majority of the code in the particle systems package is adapted from the demos particle code
 * Notable exceptions include the use of the Maclaurin method inside the calculations package
 * The particle constructor is used to create a particle object
 * The particles only support constant acceleration, possible room for expansion? 
 *
 */
public class Particle 
{
	public Vector2 position = new Vector2();
	
	public Vector2 velocity = new Vector2();
	
	public Vector2 acceleration = new Vector2();
	
	public float orientation;
	
	public float angularVelocity;
	
	public float scale;
	
	public float scaleGrowth;
	
	public float lifeSpan;
	
	public float timeSinceBirth;
	
	/**
	 * This method returns true if the particle is alive and false if it is not
	 * 
	 * @return - whether the particle is alive or not
	 */
	public boolean isAlive()
	{
		boolean isAlive = timeSinceBirth < lifeSpan;
		
		return isAlive;
	}
	
	/**
	 * This method initialises the values of a particle, this method is called every time the particle is recycled
	 * This is used to allow the particles to spawn in the correct location
	 * 
	 * @param position - A Vector2 object which contains the position the particle is to start from
	 * @param velocity - A Vector2 object which contains the initial velocity of the particle
	 * @param acceleration - A Vector2 object which contains the initial acceleration of the particle
	 * @param orientation - The initial angle of orientation of the particle
	 * @param angularVelocity - The initial rate of change of the angle of orientation of the particle
	 * @param scale - The initial scale of the particle in relation to its original size, e.g scale of 2 will give a particle twice as big as the standard
	 * @param scaleGrowth - The rate of change of the scale of the particle
	 * @param lifeSpan - How long the particle should remain 'alive' for from the moment of its conception
	 */
	public void initialize(Vector2 position, Vector2 velocity, Vector2 acceleration, float orientation, float angularVelocity, float scale, float scaleGrowth, float lifeSpan)
	{
		this.position.x = position.x;
		this.position.y = position.y;
		
		this.velocity.x = velocity.x;
		this.velocity.y = velocity.y;
		
		this.acceleration.x = acceleration.x;
		this.acceleration.y = acceleration.y;
		
		this.orientation = orientation;
		this.angularVelocity = angularVelocity;
		
		this.scale = scale;
		this.scaleGrowth = scaleGrowth;
		
		this.lifeSpan = lifeSpan;
		this.timeSinceBirth = 0.0f;
	}
	
	/**
	 * This method updates the actions of the particle over a time period delta T
	 * 
	 * @param dt - Delta T, the change in time since the last update
	 */
	public void update(float dt)
	{
		velocity.x += acceleration.x * dt;
		velocity.y += acceleration.y * dt;
		
		position.x += velocity.x * dt;
		position.y += velocity.y * dt;
		
		orientation += angularVelocity * dt;
		
		scale += scaleGrowth * dt;
		
		timeSinceBirth += dt;
	}
}
