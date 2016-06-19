package eeecs.qub.ac.uk.gp13.particle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import eeecs.qub.ac.uk.gp13.calculations.Maclaurin;
import eeecs.qub.ac.uk.gp13.calculations.PolarConverter;

/**
 * 
 * @author Josh McConnell 40105917
 *
 * The majority of the code in the particle systems package is adapted from the demos particle code
 * Notable exceptions include the use of the Maclaurin method inside the calculations package
 * The particle emitter constructor is used to create a particle emitter, that is the point from which the particles are emitted
 *  
 */
public class ParticleEmitter 
{
	private static Random random = new Random();
	
	private static float randomBetween(float min, float max)
	{
		float randomBetween = min + random.nextFloat() * (max - min);
		
		return randomBetween;
	}
	
	/**
	 * This method takes in a minimum value, a maximum value and a Vector2, in which the output is to be stored
	 * The minimum and maximum values are take in in degree form
	 * A random number generator picks a number between the minimum and maximum values
	 * The PolarConverter class is used to convert from degres into radians
	 * The Maclaurin class is then used to convert the form in radians into a 2-Dimensional Vector
	 * 
	 * @param min - the minimum desired direction in degrees
	 * @param max - the maximum desired direction in degrees
	 * @param outputVector - the 2-Dimenstional vector in which the output is stored
	 */
	private static void pickRandomDirection(float min, float max, Vector2 outputVector)
	{
		float angle = randomBetween(min, max);
		angle = (float) PolarConverter.degreesToRad(angle);
		outputVector.set((float) Maclaurin.getCosOfAngle(angle), (float) Maclaurin.getSinOfAngle(angle));
	}
	
	private Context context;
	
	private Bitmap texture;
	
	private Vector2 textureCentre;
	
	private Paint paint;
	
	private float mTimeToBurst = 0.0f;
	
	private Vector2 lastLocation= new Vector2();
	
	private Vector<Particle> activeParticles;
	
	private Vector<Particle> freeParticles;
	
	private ParticleSettings mParticleSettings;
	
	public ParticleEmitter(Context context, ParticleSettings particleSettings)
	{
		this.context = context;
		mParticleSettings = particleSettings;
		
		activeParticles = new Vector<Particle>();
		freeParticles = new Vector<Particle>();
		
		int initialSize = 100;
		for (int i = 0; i < initialSize; i++)
		{
			Particle particle = new Particle();
			freeParticles.add(particle);
		}
		
		configure();
	}
	
	/**
	 * This method sets the ParticleSettings to a particular type
	 * 
	 * @param particleSettings - the settings to be applied
	 */
	public void setParticleSettings(ParticleSettings particleSettings)
	{
		mParticleSettings = particleSettings;
		
		Iterator<Particle> iterator = activeParticles.iterator();
		while(iterator.hasNext())
		{
			Particle particle = iterator.next();
			iterator.remove();
			freeParticles.add(particle);
		}
		
		configure();
	}
	
	/**
	 * This method loads in the bitmaps for the particles and then paints them
	 */
	protected void configure()
	{
		try
		{
			AssetManager assetManager = context.getAssets();
			InputStream inputStream = assetManager.open(mParticleSettings.textureFilename);
			texture = BitmapFactory.decodeStream(inputStream);
			inputStream.close();
		}
		catch(IOException e)
		{
			
		}
		
		textureCentre = new Vector2(texture.getWidth() / 2.0f, texture.getHeight() / 2.0f);
		
		paint = new Paint();
		paint.setAntiAlias(true);
		if (mParticleSettings.additiveBlend)
		{
			paint.setXfermode(new PorterDuffXfermode(Mode.ADD));
		}
	}
	
	private Vector2 particlePosition = new Vector2();
	private Vector2 particleOffset = new Vector2();
	
	/**
	 * This methods starts off a particle emmiter with a number of particles inside the range specified by the type of particle to be displayed
	 * 
	 * @param location - used if a burst type of particle is being displayed, only displays at one location
	 * @param lastLocation - used if a continuous particle is being displayed, allows for location to change
	 */
	public void addParticles(Vector2 location, Vector2 lastLocation)
	{
		int numParticles = (int) randomBetween(mParticleSettings.minNumParticles, mParticleSettings.maxNumParticles);
		
		switch (mParticleSettings.emissionMode)
		{
		case Burst:
			particlePosition.set(location);
			particleOffset.set(Vector2.Zero);
			break;
		case Continuous:
			particlePosition.set(lastLocation);
			particleOffset.set((location.x - lastLocation.x) / numParticles, (location.y - lastLocation.y) / numParticles);
			break;
		}
		
		for (int i = 0; i < numParticles; i++)
		{
			Particle p;
			if(freeParticles.size() == 0)
			{
				p = new Particle();
			}
			
			else
			{
				p = freeParticles.remove(freeParticles.size() - 1);
			}
			
			initialiseParticle(p, particlePosition);
			activeParticles.add(p);
			
			particlePosition.x += particleOffset.x;
			particlePosition.y += particleOffset.y;
		}
	}
	
	private Vector2 direction = new Vector2();
	private Vector2 velocity = new Vector2();
	private Vector2 acceleration = new Vector2();
	
	/**
	 * This method initialises a particle with a random set of initial conditions which lies somewhere in the range of available values
	 * 
	 * @param particle - the particle to be initialised
	 * @param position - the postion the particle is to be initialised to
	 */
	private void initialiseParticle(Particle particle, Vector2 position)
	{
		pickRandomDirection(mParticleSettings.minOrientationAngle, mParticleSettings.maxOrientationAngle, direction);
		float speed = randomBetween(mParticleSettings.minInitialSpeed, mParticleSettings.maxInitialSpeed);
		
		velocity.x = direction.x * speed;
		velocity.y = direction.y * speed;
		
		float lifeSpan = randomBetween(mParticleSettings.minLifespan, mParticleSettings.maxLifespan);
		
		float orientation = randomBetween(0.0f, (float) Math.PI * 2.0f);
		float angularVelocity = randomBetween(mParticleSettings.minAngularVelocity, mParticleSettings.maxAngularVelocity);
		
		float scale = randomBetween(mParticleSettings.minScale, mParticleSettings.maxScale);
		float scaleGrowth = randomBetween(mParticleSettings.minScaleGrowth, mParticleSettings.maxScaleGrowth);
		
		switch(mParticleSettings.accelerationMode)
		{
		case Aligned:
			float accelerationMagnitude = randomBetween(mParticleSettings.minAccelerationMagnitude, mParticleSettings.maxAccelerationMagnitude);
			acceleration.x = direction.x * accelerationMagnitude;
			acceleration.y = direction.y * accelerationMagnitude;
			break;
			
		case NonAligned:
			pickRandomDirection(mParticleSettings.minAccelerationDirection, mParticleSettings.maxAccelerationDirection, acceleration);
			accelerationMagnitude = randomBetween(mParticleSettings.minAccelerationMagnitude, mParticleSettings.maxAccelerationMagnitude);
			acceleration.x = direction.x * accelerationMagnitude;
			acceleration.y = direction.y * accelerationMagnitude;
			break;
			
		default:
			break;
		}
		
		particle.initialize(position, velocity, acceleration, orientation, angularVelocity, scale, scaleGrowth, lifeSpan);
	}
	
	/**
	 * This method carries out the update calculations for the particles 
	 * 
	 * @param elapsedTime - the time which has elapsed since the last update operation
	 * @param location - the current location input, (only necessary for particles of the continuous variety)
	 */
	public void update(float elapsedTime, Vector2 location)
	{
		if (mTimeToBurst > 0.0f)
		{
			mTimeToBurst -= elapsedTime;
		}
		
		else
		{
			mTimeToBurst = randomBetween(mParticleSettings.minBurstTime, mParticleSettings.maxBurstTime);
			addParticles(location, lastLocation);
			lastLocation.set(location);
		}
		
		Iterator<Particle> iterator = activeParticles.iterator();
		while (iterator.hasNext())
		{
			Particle particle = iterator.next();
			
			particle.velocity.add(mParticleSettings.gravityX, mParticleSettings.gravityY);
			
			particle.update(elapsedTime);
			
			if (!particle.isAlive())
			{
				iterator.remove();
				freeParticles.add(particle);
			}
		}
	}
	
	private Matrix matrix = new Matrix();
	
	/**
	 * This method draws the all live particles to the canvas
	 * 
	 * @param canvas - the canvas the live particles are to be drawn to
	 * @param gameTime - the current time active
	 */
	public void draw(Canvas canvas, float gameTime)
	{
		for (Particle p : activeParticles)
		{
			float normalizedLifeTime = p.timeSinceBirth / p.lifeSpan;
			float alpha = 4.0f * normalizedLifeTime * (1 - normalizedLifeTime);
			paint.setAlpha((int) (alpha * 255));
			
			matrix.reset();
			matrix.setScale(p.scale,  p.scale);
			matrix.postRotate(p.orientation, textureCentre.x, textureCentre.y);
			matrix.postTranslate(p.position.x - textureCentre.x, p.position.y - textureCentre.y);
			
			canvas.drawBitmap(texture, matrix, paint);
		}
	}
}
