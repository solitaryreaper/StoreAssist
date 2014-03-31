package models.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import models.ItemLocation;
import models.Store;
import models.utils.DBUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * A service API that searches entities related to stores - item locations, store locations etc.
 * @author excelsior
 *
 */
public class SearchService {

	private static Logger LOG = Logger.getLogger(SearchService.class.getSimpleName());
	
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
		List<ItemLocation> locations = Lists.newArrayList();
		
		String sql = 
				" select item.name AS name, item_location.location_id AS id, section.name AS section, aisle_shelf.aisle_name AS aisle, aisle_shelf.shelf_name AS shelf " +
				" from item_info item" +
				" join store store on (item.store_id = store.id)" +
				" join item_location item_location on(item.item_id = item_location.item_id)" +
				" join location location on (item_location.location_id = location.id)" +
				" join section section on (location.section_id = section.id)" +
				" join aisle_shelf aisle_shelf on (aisle_shelf.id = location.aisle_shelf_id)" +
				" where LOWER(item.name) like '%" + item.toLowerCase() + "%'" + 
				" and LOWER(store.name) like '%" + storeIdentifier.toLowerCase() + "%'";
		
		LOG.info("SQL : " + sql);
		
		Connection dbConn = DBUtils.getDBConnection();
		PreparedStatement prepStmt = null;
		
		try {
			prepStmt = dbConn.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				String section = rs.getString("section");
				String aisle = rs.getString("aisle");
				String shelf = rs.getString("shelf");
				long locationId = rs.getLong("id");
				
				locations.add(new ItemLocation(locationId, section, aisle, shelf));
			}
			
		}
		catch(Exception e) {
			LOG.severe("Failed to get locations. Reason : " + e.getMessage());
			e.printStackTrace();
		}
		
		return locations;
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
}
