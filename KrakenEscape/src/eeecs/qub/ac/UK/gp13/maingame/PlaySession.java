package eeecs.qub.ac.uk.gp13.maingame;

public class PlaySession {
	
	//player health, think of it more as the distance to kraken, as every correct instruction "heals" the player.
	private int subhp;
	private int score;
	private int combocounter;
	// factor by which all score is multiplied
	private int multiplier;
	private final int BASE_SCORE;
	
	
	/*
	 * A new Play Session is created whenever the main game fragment is created, this class will return
	 * information about the current play session and calculate the score gained from each successful instruction
	 */
	/**
	 * 	Constructor for the PlaySession, initialises variables
	 */
	public PlaySession()
	{
		BASE_SCORE = 5;
		subhp = 10;
		score = 0;
		combocounter=0;
		multiplier=1;
	}
	/**
	 * @param nTime
	 * 	Calculates the score according to the time remaining(nTime) upon completion of the command and 
	 * multiplies it based on the combocounter
	 */
	public void addScore(int nTime)
	{
		if(combocounter >= 5)
		{
			multiplier = 2;
		}
		if(combocounter >=10)
		{
			multiplier = 3;
		}
		if(combocounter >= 20)
		{
			multiplier = 4;
		}
		score += (BASE_SCORE*nTime) * multiplier;
		
	}
	

	/**
	 * 	Increments the combocounter by 1
	 */
	public void addCounter()
	{
		combocounter++;
	}
	/**
	 * Resets the coumbocounter to 0
	 */
	public void resetCounter()
	{
		combocounter = 0;
	}
	
	/**
	 * @param nHP
	 * Sets the sub hp to a paramater passed in as an integer value
	 */
	public void setSubHP(int nHP)
	{
		subhp = nHP;
	}
	
	/**
	 * @return
	 * returns the private subhp
	 */
	public int getSubHP()
	{
		return subhp;
	}
	
	/**
	 * @param nScore
	 * Sets the score to an integer parameter(nScore) would be primarily be used for reseting the score
	 */
	public void setScore(int nScore)
	{
		score = nScore;
	}
	/**
	 * @return
	 * returns the private score
	 */
	public int getScore()
	{
		return score;
	}
}
