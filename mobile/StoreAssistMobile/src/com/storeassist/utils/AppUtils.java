package com.storeassist.utils;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

public class AppUtils
{

	/**
	 * Method to get valid addresses using GeoCoder, given the user's current 
	 * location in terms of latitude and longitude.
	 * 
	 * @param c				Context
	 * @param latitude		Latitude
	 * @param longitude		Longitude
	 * @return				List of valid addresses
	 */
	public static List<Address> getAddress(Context c, double latitude, double longitude)
	{
		Log.d("AppUtils", "getAddress() :: Method called");
		
		Geocoder geocoder;
		List<Address> addresses = null;
		geocoder = new Geocoder(c);
		
		try
		{
			if (latitude != 0 || longitude != 0)
			{
				addresses = geocoder.getFromLocation(latitude, longitude, 100);
				printAddresses(addresses, "Original Address", false);
				
				addresses = filterAddress(addresses);
				printAddresses(addresses, "Filtered Address", true);
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
	
	/**
	 * Method to filter the addresses returned by the GeoCoder.
	 * 
	 * @param addresses		List of addresses returned by the GeoCoder
	 * @return				Valid Addresses
	 */
	public static List<Address> filterAddress(List<Address> addresses)
	{
		if(addresses == null || addresses.size() == 0)
			return null;
		
		ArrayList<Address> filteredAddress = new ArrayList<Address>();
				
		for(Address address : addresses)
		{
			if(!isValidCountryCode(address.getCountryCode()))
				continue;
			
			if(!isValidPostalCode(address.getPostalCode()))
				continue;
			
			if(!isValidAddressLine3(address.getAddressLine(2)))
				continue;
			
//			String featureName = addresses.get(i).getFeatureName(); // May be Store Name
//			String street 	= address.getAddressLine(0);
//			String city 	= address.getAddressLine(1);
			
			// If everything looks fine, just add it to the new list.
			filteredAddress.add(address);	
			
		}
		
		addresses = null;	// Check: Should free up the memory?

		return filteredAddress;
	}
	
	/**
	 * Method to check if the postal code is valid by running it through a regular expression.
	 * 
	 * @param inputPostalCode	Postal Code in question		
	 * @return					Validity of Postal Code
	 */
	public static boolean isValidPostalCode(String inputPostalCode)
	{
		/*
		 * StackOverflow Links:
		 * (1) "US"=>"^\d{5}([\-]?\d{4})?$" -- http://stackoverflow.com/questions/578406/what-is-the-ultimate-postal-code-and-zip-regex
		 * (2) "US"=>"^\d{5}(?:[-\s]\d{4})?$"  -- http://stackoverflow.com/questions/2577236/regex-for-zip-code
		 */
		if(isValidNonEmptyString(inputPostalCode) &&
				inputPostalCode.matches("^[0-9]{5}(?:[ \t\n\r\f-][0-9]{4})?$"))
			return true;
			
		return false;
	}
	
	/**
	 * Method to check if the address line-3 is non-null and not-empty.
	 * 
	 * @param inputAddressLine3		
	 * @return	Validity of Address Line-3
	 */
	public static boolean isValidAddressLine3(String inputAddressLine3)
	{
		if(isValidNonEmptyString(inputAddressLine3))
			return true;
			
		return false;
	}
	
	/**
	 * Method to check if the country code is valid or not.
	 * 
	 * @param inputCountryCode	Country code in question
	 * @return					Validity of the country code
	 */
	public static boolean isValidCountryCode(String inputCountryCode)
	{
		if(isValidNonEmptyString(inputCountryCode))
		{
			for(String countryCode : AppConstants.CUSTOMER_COUNTRY_CODE_LIST)
			{
				if(countryCode.equals(inputCountryCode))
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Method to check if the string is non-null and not empty.
	 * 
	 * @param inputStr		Input String
	 * @return				result if valid or not
	 */
	public static boolean isValidNonEmptyString(String inputStr)
	{
		if(inputStr != null && !inputStr.equals(""))
			return true;
		
		return false;
	}
	
	/**
	 * Method to print the addresses on logcat.
	 * 
	 * @param addresses		List of addresses to print
	 * @param tag			TAG String
	 * @param inDetail		Print in detail?
	 */
	public static void printAddresses(List<Address> addresses, String tag, boolean inDetail)
	{
		String strCompAdd = null;
		
//		if(AppConstants.SHOW_LOGS)	// TODO: Remove the comment.
		{
			for(Address fullAddress : addresses)
			{
				String address 	= fullAddress.getAddressLine(0);
				String city 	= fullAddress.getAddressLine(1);
				String country 	= fullAddress.getAddressLine(2);
				
				
				if(inDetail)
				{
					strCompAdd = address + ", " + city + ", " + country;
					strCompAdd = strCompAdd.replaceAll("null", "");
					strCompAdd = strCompAdd.replaceAll("Unnamed", "");
					Log.d("AppUtils", "Complete Address String = " + strCompAdd);
					
					Log.d("AppUtils", tag + 
							" CountryName= " + fullAddress.getCountryName() + ", " +
							" CountryCode= " + fullAddress.getCountryCode() + ", " +
							" PostalCode= " + fullAddress.getPostalCode() + ", " +
							" FeatureName= " + fullAddress.getFeatureName() + ", " +
							" ThoroughFare= " + fullAddress.getThoroughfare() + ", " +
							" SubThoroughFare= " + fullAddress.getSubThoroughfare() + ", " +
							" Locality= " + fullAddress.getLocality() + ", " +
							" AddressLine1= " + fullAddress.getAddressLine(0) + ", " +
							" AddressLine2= " + fullAddress.getAddressLine(1) + ", " +
							" AddressLine3= " + fullAddress.getAddressLine(2) + ", " +
							" AddressLine4= " + fullAddress.getAddressLine(3)
							);
				}
				else
				{
					Log.d("AppUtils", tag + " = " + address + ", city =" + city + ", country = " + country);
				
				}
				
			}
		}
	}
}
