package eeecs.qub.ac.uk.gp13.particle;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * The majority of the code in the particle systems package is adapted from the demos particle code
 * Notable exceptions include the use of the Maclaurin method inside the calculations package
 * In this class there exists the different ParticleSettings
 * 
 * Bubbles - Own design - Aim to be attached to submarine prop? Or activate on button
 * Smoke - Demo design - Possible when life goes beneath a certain level
 * Explosion - Demo design - Possible upon losing the game
 * Particle - Demo design - Testing only, no game application
 * 
 */
public class ParticleSettings 
{
	public enum Systems
	{
		Bubbles, Smoke, Explosion, Particle 
	};
	
	public static ParticleSettings Bubbles = new ParticleSettings(
			"img/Particles/Bubble2.png",
			false, //Additive blend
			EmissionMode.Continuous, //Emission mode
			0.05f, //Min burst time
			0.05f, //Max burst time
			AccelerationMode.NonAligned, //Acceleration Mode
			85.0f, //Min acceleration Direction
			95.0f, //Max acceleration Direction
			3.0f, //Min acceleration Magnitude
			4.0f, //Max acceleration Magnitude
			-2, //Gravity x - goes slowly backwards from the tail of the submarine
			-2, //Gravity y - assuming a 1/4g (ish) upwards, exact formula given by (F=ma where m = [(4/3)PI*r^3d) where r = radius, d = density])
			10, //Min number of particles
			15, //Max number of particles
			50, //Min initial speed
			100, //Max initial speed
			0.0f, //Min angular velocity
			45.0f, //Max angular velocity
			-135, //Max orientation angle
			-45, //Max direction angle
			2.0f, //Min life span
			3.0f, // Max life span
			1.0f, //Min size
			2.0f, //Max size
			0.5f, //Min scale growth
			1.0f //Max scale growth
			);
	
	public static ParticleSettings Smoke = new ParticleSettings(
			"img/Particles/Smoke.png", //File name for image
			false, //Additive blend
			EmissionMode.Burst, //Emission mode
			0.5f, //Min burst time
			1.0f, //Max burst time
			AccelerationMode.NonAligned, //Acceleration Mode
			-110.0f, //Min acceleration Direction
			-70.0f, //Max acceleration Direction
			50.0f, //Min acceleration Magnitude
			50.0f, //Max acceleration Magnitude
			0, //Gravity x
			0, //Gravity y
			4, //Min number of particles
			16, //Max number of particles
			20, //Min initial speed
			100, //Max initial speed
			-22.5f, //Min angular velocity
			22.5f, //Max angular velocity
			-110, //Max orientation angle
			-70, //Max direction angle
			5, //Min life span
			7, // Max life span
			0.5f, //Min size
			1.0f, //Max size
			0.1f, //Min scale growth
			0.5f //Max scale growth
			);
	
	public static ParticleSettings Explosion = new ParticleSettings(
			"img/Particles/Explosion.png", //File name for image
			true, //Additive blend
			EmissionMode.Burst, //Emission mode
			1.0f, //Min burst time
			1.0f, //Max burst time
			AccelerationMode.Aligned, //Acceleration Mode
			0.0f, //Min acceleration Direction
			0.0f, //Max acceleration Direction
			-750.0f, //Min acceleration Magnitude
			-760.0f, //Max acceleration Magnitude
			0.0f, //Gravity x
			0.0f, //Gravity y
			50, //Min number of particles
			60, //Max number of particles
			400, //Min initial speed
			500, //Max initial speed
			-90.0f, //Min angular velocity
			90.0f, //Max angular velocity
			0, //Max orientation angle
			360, //Max direction angle
			0.5f, //Min life span
			1.0f, // Max life span
			0.3f, //Min size
			1.0f, //Max size
			0.1f, //Min scale growth
			0.2f //Max scale growth
			);
	
	public static ParticleSettings Particle = new ParticleSettings(
			"img/Particles/Particle.png", //File name for image
			false, //Additive blend
			EmissionMode.Continuous, //Emission mode
			0.05f, //Min burst time
			0.05f, //Max burst time
			AccelerationMode.NonAligned, //Acceleration Mode
			85.0f, //Min acceleration Direction
			95.0f, //Max acceleration Direction
			3.0f, //Min acceleration Magnitude
			4.0f, //Max acceleration Magnitude
			0, //Gravity x
			9.8f, //Gravity y
			15, //Min number of particles
			20, //Max number of particles
			50, //Min initial speed
			100, //Max initial speed
			0.0f, //Min angular velocity
			45.0f, //Max angular velocity
			-135, //Max orientation angle
			-45, //Max direction angle
			2.0f, //Min life span
			3.0f, // Max life span
			1.0f, //Min size
			2.0f, //Max size
			0.5f, //Min scale growth
			1.0f //Max scale growth
			);
	
	public enum AccelerationMode
	{
		Aligned, NonAligned
	};
	
	public enum EmissionMode
	{
		Burst, Continuous
	};
	
	public String textureFilename;
	
	public boolean additiveBlend;
	
	public EmissionMode emissionMode;
	
	public float minBurstTime;
	public float maxBurstTime;
	
	public int minNumParticles;
	public int maxNumParticles;
	
	public AccelerationMode accelerationMode;
	
	public float minAccelerationDirection;
	public float maxAccelerationDirection;
	
	public float minAccelerationMagnitude;
	public float maxAccelerationMagnitude;
	
	public float gravityX;
	public float gravityY;
	
	public float minInitialSpeed;
	public float maxInitialSpeed;
	
	public float minAngularVelocity;
	public float maxAngularVelocity;
	
	public float minOrientationAngle;
	public float maxOrientationAngle;
	
	public float minLifespan;
	public float maxLifespan;
	
	public float minScale;
	public float maxScale;
	
	public float minScaleGrowth;
	public float maxScaleGrowth;
	
	/**
	 * Params taken from enums, comments above outline those below
	 * This method initialises the settings from the enum
	 * 
	 * @param textureFilename
	 * @param additiveBlend
	 * @param emissionMode
	 * @param minBurstTime
	 * @param maxBurstTime
	 * @param accelerationMode
	 * @param minAccelerationDirection
	 * @param maxAccelerationDirection
	 * @param minAccelerationMagnitude
	 * @param maxAccelerationMagnitude
	 * @param gravityX
	 * @param gravityY
	 * @param minNumParticles
	 * @param maxNumParticles
	 * @param minInitialSpeed
	 * @param maxInitialSpeed
	 * @param minAngularVelocity
	 * @param maxAngularVelocity
	 * @param minOrientationAngle
	 * @param maxOrientationAngle
	 * @param minLifespan
	 * @param maxLifespan
	 * @param minScale
	 * @param maxScale
	 * @param minScaleGrowth
	 * @param maxScaleGrowth
	 */
	public ParticleSettings(String textureFilename, boolean additiveBlend, EmissionMode emissionMode, float minBurstTime, float maxBurstTime, AccelerationMode accelerationMode, float minAccelerationDirection, float maxAccelerationDirection, float minAccelerationMagnitude, float maxAccelerationMagnitude, float gravityX, float gravityY, int minNumParticles, int maxNumParticles, float minInitialSpeed, float maxInitialSpeed, float minAngularVelocity, float maxAngularVelocity, float minOrientationAngle, float maxOrientationAngle, float minLifespan,float maxLifespan, float minScale, float maxScale, float minScaleGrowth, float maxScaleGrowth)
	{
		this.textureFilename = textureFilename;
		this.additiveBlend = additiveBlend;
		this.emissionMode = emissionMode;
		
		this.minBurstTime = minBurstTime;
		this.maxBurstTime = maxBurstTime;
		
		this.accelerationMode = accelerationMode;
		
		this.minAccelerationDirection = minAccelerationDirection;
		this.maxAccelerationDirection = maxAccelerationDirection;
		this.minAccelerationMagnitude = minAccelerationMagnitude;
		this.maxAccelerationMagnitude = maxAccelerationMagnitude;
		
		this.gravityX = gravityX;
		this.gravityY = gravityY;
		
		this.minNumParticles = minNumParticles;
		this.maxNumParticles = maxNumParticles;
		
		this.minInitialSpeed = minInitialSpeed;
		this.maxInitialSpeed = maxInitialSpeed;
		
		this.minAngularVelocity = minAngularVelocity;
		this.maxAngularVelocity = maxAngularVelocity;
		
		this.minOrientationAngle = minOrientationAngle;
		this.maxOrientationAngle = maxOrientationAngle;
		
		this.minLifespan = minLifespan;
		this.maxLifespan = maxLifespan;
		
		this.minScale = minScale;
		this.maxScale = maxScale;
		
		this.minScaleGrowth = minScaleGrowth;
		this.maxScaleGrowth = maxScaleGrowth;
	}
}
