package eeecs.qub.ac.uk.gp13.controlobjects;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Load 
{
	public static Bitmap loadBitmap(AssetManager assetManager, String asset)
	{
		Bitmap bitmap = null;
		InputStream inputStream = null;
		
		try
		{
			inputStream = assetManager.open(asset);
			
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;
			
			bitmap = BitmapFactory.decodeStream(inputStream, null, options);
		}
		catch (IOException e)
		{
			
		}
		finally
		{
			if (inputStream != null)
			{
				try
				{
					inputStream.close();
				}
				catch(IOException e)
				{
					
				}
			}
		}
		return bitmap;
	}
}
