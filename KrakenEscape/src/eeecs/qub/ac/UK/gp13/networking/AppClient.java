package eeecs.qub.ac.uk.gp13.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.Socket;
import java.util.ArrayList;

import android.os.StrictMode;

/**
 * 
 * @author Josh McConnell 40105917
 * 
 * The AppClient class handles all relations with the server
 * Included is a method to receive the latest information from the server
 * Also included is a method to send a highscore to the server
 *
 */
public class AppClient 
{
	private static Socket clientSocket;
	private static ObjectOutputStream dataOut;
	private static ObjectInputStream dataIn;
	
	/**
	 * This method gets an array list which contains the 10 top highscores from the server
	 * 
	 * @return - an arraylist containing the highscores in string form
	 */
	public static ArrayList<String> AppClientRun()
	{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		getConnections();
		ArrayList<String> out = getHighScores();
		
		try 
		{
			clientSocket.close();
			dataOut.close();
			dataIn.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
		return out;
	}
	
	/**
	 * This method establishes a socket to connect to the server used for getting highscores
	 */
	private static void getConnections()
	{
		try
		{
			//Ygritte
			//clientSocket = new Socket("82.14.86.172", 7500);
			//Serenity
			clientSocket = new Socket("82.14.86.172", 9000);
			
			dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
			dataIn = new ObjectInputStream(clientSocket.getInputStream());
		}
		catch(IOException e)
		{
			System.out.println("Error binding socket");
		}
	}
	
	/**
	 * This method establishes a socket to connect to the server and is used for sending a single new highscore to the server
	 * 
	 * @param in - the highscore to be sent
	 */
	private static void getSendConnections(int in)
	{
		try
		{
			//Ygritte
			//clientSocket = new Socket("82.14.86.172", 7501);
			//Serenity
			clientSocket = new Socket("82.14.86.172", 9001);
			
			dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
			dataIn = new ObjectInputStream(clientSocket.getInputStream());
			String objectToWrite = Integer.toString(in);
			dataOut.writeObject(objectToWrite);
			
			dataIn.close();
			dataOut.close();
			clientSocket.close();
		}
		
		catch(IOException e)
		{
			System.out.println("Error binding socket");
		}
	}
	
	/**
	 * This method recieves a series of strings from the server, which are then put into an arraylist
	 * 
	 * @return - the arraylist which contains the highscores in string format
	 */
	private static ArrayList<String> getHighScores()
	{
		ArrayList<String> out = new ArrayList<String>();
		boolean finished = false;
		
		while (finished == false)
		{
			String in = "";
			try 
			{
				in = (String) dataIn.readObject();
			}
			catch (OptionalDataException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			if (in.equals("END"))
			{
				finished = true;
			}
			
			else
			{
				out.add(in);
			}
		}
		
		try
		{
			clientSocket.close();
			dataOut.close();
			dataIn.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return out;
	}
	
	/**
	 * This method sends a new highscore to the server
	 * 
	 * @param newHighScore - the highscore to be sent
	 */
	public static void sendNewHighScore(int newHighScore)
	{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		getSendConnections(newHighScore);
		
		try 
		{
			clientSocket.close();
			dataOut.close();
			dataIn.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
