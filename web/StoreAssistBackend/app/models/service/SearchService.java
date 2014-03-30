package models.service;

import java.util.List;

import com.google.common.collect.Lists;

import models.ItemLocation;

public class SearchService {

	public static List<ItemLocation> searchItemLocation(String storeIdentifier, String item)
	{
		return getDummyLocation();
	}
	
	private static List<ItemLocation> getDummyLocation()
	{
		List<ItemLocation> locations = Lists.newArrayList();
		locations.add(new ItemLocation(1, "Fruits", "A1", "2"));
		return locations;
	}
}
