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
	 * @param storeId	- Identifier for store.
	 * @param items	- List of items to be searched.
	 * @param isExactMatchReqd - Searches only for exact item names. If set to false, does a regex
	 * 							search using parameter item name.
	 * @return List of all locations for items in the specified store.
	 */
	public Map<String, List<ItemLocation>> searchItemsLocations(int storeId, List<String> items, boolean isExactMatchReqd);
	
	/**
	 * Search all locations of  an item in a store.
	 * 
	 * @param storeId	- Identifier for store.
	 * @param item	- Item name.
	 * @param isExactMatchReqd - Searches only for exact item names. If set to false, does a regex
	 * 							search using parameter item name.
	 * @return	List of all locations for an item in the specified store.
	 */
	public List<ItemLocation> searchItemLocations(int storeId, String item, boolean isExactMatchReqd);
	
	/**
	 * Suggests item names for a store, based on the partial item name inputted by the user. This
	 * would server the auto-complete feature of the application.
	 * 
	 * @param itemName	-	a partial item name
	 * @param storeId	- 	id for the store where items have to be searched.
	 * @return	List of items that match the partial item name regex.
	 */
	public List<String> searchItemsByName(String itemName, int storeId);
	
	/**
	 * Searches for stores based on the input store identifier.
	 * @param store	- Identifier for store.
	 * @return Store
	 */
	public List<Store> searchStore(StoreSearchMetadata store);
}
