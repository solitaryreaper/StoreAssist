package models.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import models.ItemLocation;
import models.Store;
import models.StoreSearchMetadata;
import models.utils.DBUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * A service API that searches entities related to stores - item locations, store locations etc.
 * @author excelsior
 *
 */
public class SearchServiceImpl implements SearchService {

	private static Logger LOG = Logger.getLogger(SearchService.class.getSimpleName());

	@Override
	public Map<String, List<ItemLocation>> searchItemsLocations(int storeId, List<String> items)
	{
		Map<String, List<ItemLocation>> itemsLocations = Maps.newHashMap();
		for(String item : items) {
			itemsLocations.put(item, searchItemLocations(storeId, item));
		}
		
		return itemsLocations;
	}

	@Override
	public List<ItemLocation> searchItemLocations(int storeId, String item)
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
				" and store.id = " + storeId;
		
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
	
	@Override
	public List<Store> searchStore(StoreSearchMetadata storeMeta)
	{
		Integer searchStoreId = storeMeta.getStoreId();
		String searchName = storeMeta.getName();
		String searchAddress = storeMeta.getAddress();
		Integer searchZip = storeMeta.getZip();
		String searchCity = storeMeta.getCity();
		
		StringBuilder sql = new StringBuilder(); 
		sql.append("SELECT id, name, address, zip, city, state FROM store WHERE 1=0 OR ");

		/**
		 * If store id or name is specified, they can uniquely identify a store.
		 * Else, base the search results on remaining parameters.
		 */
		if(searchStoreId != null) {
			sql.append(" id = ").append(searchStoreId);
		}
		else if(searchName != null) {
			sql.append(" LOWER(name) LIKE '%").append(searchName.toLowerCase()).append("%'");
		}
		else if(searchAddress != null) {
			sql.append(" LOWER(address) LIKE '%").append(searchAddress.toLowerCase()).append("%'");
		}
		
		if(searchZip != null) {
			sql.append(" OR zip = ").append(searchZip);
		}
		if(searchCity != null) {
			sql.append(" OR city = '").append(searchCity).append("'");
		}
		
		LOG.info("SQL : " + sql);
		
		List<Store> stores = Lists.newArrayList();
		Connection dbConn = DBUtils.getDBConnection();
		PreparedStatement prepStmt = null;
		try {
			prepStmt = dbConn.prepareStatement(sql.toString());
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				int zip = rs.getInt("zip");
				String city = rs.getString("city");
				
				stores.add(new Store(id, name, address, zip, city));
			}
		}
		catch(Exception e) {
			LOG.severe("Failed to get locations. Reason : " + e.getMessage());
			e.printStackTrace();
		}

		return stores;
	}
}
