package com.storeassist.utils;

import java.util.List;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.print.PrintAttributes;
import android.util.Log;

public class AppUtils
{

	public static List<Address> getAddress(Context c, double latitude, double longitude)
	{
		String strCompAdd = null;
		
		Log.d("AppUtils", "getAddress() :: Method called");
		
		Geocoder geocoder;
		List<Address> addresses = null;
		geocoder = new Geocoder(c);
		
		try
		{
			if (latitude != 0 || longitude != 0)
			{
				addresses = geocoder.getFromLocation(latitude, longitude, 100);
				printAddresses(addresses, "Original Address");
				
				addresses = filterAddress(addresses);
				printAddresses(addresses, "Filtered Address");
			}

		} 
		catch (Exception e)
		{
			e.printStackTrace();
			// Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		
		return addresses;
//		return strCompAdd;
	}
	
	public static List<Address> filterAddress(List<Address> addresses)
	{
		if(addresses == null || addresses.size() == 0)
			return null;
		
		
		
		return addresses;
	}
	
	public static void printAddresses(List<Address> addresses, String tag)
	{
		String strCompAdd = null;
		
		if(AppConstants.SHOW_LOGS)
		{
			for(Address fullAddress : addresses)
			{
				String address 	= fullAddress.getAddressLine(0);
				String city 	= fullAddress.getAddressLine(1);
				String country 	= fullAddress.getAddressLine(2);
				
				Log.d("AppUtils", tag + " = " + address + ", city =" + city + ", country = " + country);
				
				strCompAdd = address + ", " + city + ", " + country;
				strCompAdd = strCompAdd.replaceAll("null", "");
				strCompAdd = strCompAdd.replaceAll("Unnamed", "");
				
				Log.d("AppUtils", "Complete Address String = " + strCompAdd);
			}
		}
	}
}
