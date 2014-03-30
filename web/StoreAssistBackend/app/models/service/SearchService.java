package models.service;

import java.util.List;
import java.util.Map;

import models.ItemLocation;
import models.Store;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * A service API that searches entities related to stores - item locations, store locations etc.
 * @author excelsior
 *
 */
public class SearchService {

	private enum StoreIdentifier
	{
		STORE_NAME, // Name of the store like Madison Fresh
		ADDRESS,	// Address of the store like 2110 University Avenue
		ZIP_CODE,	// Zip Code of store like 53726
		EXTERNAL_STORE_ID,	// Any external known unique store id
		INTERNAL_STORE_ID	// Internal unique store id
	}
	
	/**
	 * Searches all locations of items within a store.
	 * 
	 * @param storeIdentifier	- Identifier for store - store name/address/zip/store id
	 * @param items				- List of items to be searched.
	 * @return List of all locations for items in the specified store.
	 */
	public static Map<String, List<ItemLocation>> searchItemsLocations(String storeIdentifier, List<String> items)
	{
		Map<String, List<ItemLocation>> itemsLocations = Maps.newHashMap();
		for(String item : items) {
			itemsLocations.put(item, searchItemLocations(storeIdentifier, item));
		}
		
		return itemsLocations;
	}
	
	
	/**
	 * Search all locations of  an item in a store.
	 * 
	 * @param storeIdentifier	- Identifier for store - store name/address/zip/store id
	 * @param item				- Item name.
	 * @return	List of all locations for an item in the specified store.
	 */
	private static List<ItemLocation> searchItemLocations(String storeIdentifier, String item)
	{
		return getDummyLocation();
	}
	
	/**
	 * 
	 * @param storeIdentifier
	 * @return
	 */
	public static Store searchStore(String storeIdentifier)
	{
		return null;
	}
	
	private static List<ItemLocation> getDummyLocation()
	{
		List<ItemLocation> locations = Lists.newArrayList();
		locations.add(new ItemLocation(1, "Fruits", "A1", "2"));
		return locations;
	}
}
