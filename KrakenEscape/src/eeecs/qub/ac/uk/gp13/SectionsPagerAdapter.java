package eeecs.qub.ac.uk.gp13;

import eeecs.qub.ac.uk.gp13.fragments.HTPFragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class SectionsPagerAdapter extends FragmentStatePagerAdapter 
{
	//http://developer.android.com/reference/android/support/v4/app/FragmentPagerAdapter.html
	//Connor: I used this in an old android application that I toyed with making and I am not sure where all the code came from
	
	public SectionsPagerAdapter(FragmentManager fm) 
	{
		super(fm);
	}

	@Override
	public Fragment getItem(int position) 
	{
		Fragment fragment = new Fragment();
		switch (position)	
		{
		case 0:
			return fragment = new HTPFragmentActivity.Help1();
		case 1:
			return fragment = new HTPFragmentActivity.Help2();
		case 2:
			return fragment = new HTPFragmentActivity.Help3();
		default:
			break;
		}
		return fragment;
	}

	@Override
	public int getCount()
	{
		// Show 3 total pages.
		return 3;
	}
	
	@Override
	public CharSequence getPageTitle(int position) 
	{
		return "SECTION_NUMBER" + (position + 1);
	}
}