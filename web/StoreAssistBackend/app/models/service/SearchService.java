package models.service;

import java.util.List;
import java.util.Map;

import models.ItemLocation;
import models.Store;
import models.StoreSearchMetadata;

/**
 * A service API that searches entities related to stores - item locations, store locations etc.
 * @author excelsior
 *
 */
public interface SearchService {

	/**
	 * Searches all locations of items within a store.
	 * 
	 * @param store	- Identifier for store.
	 * @param items	- List of items to be searched.
	 * @return List of all locations for items in the specified store.
	 */
	public Map<String, List<ItemLocation>> searchItemsLocations(String storeIdentifier, List<String> items);
	
	/**
	 * Search all locations of  an item in a store.
	 * 
	 * @param store	- Identifier for store.
	 * @param item	- Item name.
	 * @return	List of all locations for an item in the specified store.
	 */
	public List<ItemLocation> searchItemLocations(String storeIdentifier, String item);
	
	/**
	 * Searches for stores based on the input store identifier.
	 * @param store	- Identifier for store.
	 * @return Store
	 */
	public List<Store> searchStore(StoreSearchMetadata store);
}
