package models.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import models.Constants;
import models.ItemLocation;
import models.Store;
import models.StoreSearchMetadata;
import models.utils.DBUtils;
import models.utils.SearchLogger;

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
	public Map<String, List<ItemLocation>> searchItemsLocations(int storeId, List<String> items, boolean isExactMatchReqd)
	{
		Map<String, List<ItemLocation>> itemsLocations = Maps.newHashMap();
		for(String item : items) {
			itemsLocations.put(item, searchItemLocations(storeId, item, isExactMatchReqd));
		}
		
		return itemsLocations;
	}

	@Override
	public List<ItemLocation> searchItemLocations(int storeId, String item, boolean isExactMatchReqd)
	{
		List<ItemLocation> locations = Lists.newArrayList();
		
		StringBuilder itemLocatorSQL = new StringBuilder();
		itemLocatorSQL.append(" SELECT item.name AS name, item.section AS section, item.aisle AS aisle");
		itemLocatorSQL.append(" FROM " + Constants.DB_ITEM_TABLE + " item");
		itemLocatorSQL.append(" JOIN " + Constants.DB_STORE_TABLE + " store on (item.store_id = store.id)");
		itemLocatorSQL.append(" WHERE store.id = " + storeId);
		if(isExactMatchReqd) {
			itemLocatorSQL.append(" AND LOWER(item.name) = '" + item.toLowerCase() + "'");
		}
		else {
			itemLocatorSQL.append(" AND LOWER(item.name) LIKE '%" + item.toLowerCase() + "%'");
		}
		
		LOG.info("SQL : " + itemLocatorSQL.toString());
		
		Connection dbConn = DBUtils.getDBConnection();
		PreparedStatement prepStmt = null;
		
		try {
			prepStmt = dbConn.prepareStatement(itemLocatorSQL.toString());
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				String section = rs.getString("section");
				String aisle = rs.getString("aisle");
				
				locations.add(new ItemLocation(section, aisle));
			}
		}
		catch(Exception e) {
			LOG.severe("Failed to get locations. Reason : " + e.getMessage());
			e.printStackTrace();
		}
		
		SearchLogger.insertSearchQuery(item, storeId);
		
		DBUtils.cleanDBResources(prepStmt);
		
		return locations;
	}
	
	@Override
	public List<String> searchItemsByName(String itemName, int storeId)
	{
		List<String> items = Lists.newArrayList();
		String sql = 
			"SELECT name FROM " + Constants.DB_ITEM_TABLE + " WHERE LOWER(name) LIKE '%" + 
			itemName.toLowerCase() + "%'";
		
		Connection dbConn = DBUtils.getDBConnection();
		PreparedStatement prepStmt = null;
		try {
			prepStmt = dbConn.prepareStatement(sql.toString());
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				items.add(name);
			}
		}
		catch(Exception e) {
			LOG.severe("Failed to get locations. Reason : " + e.getMessage());
			e.printStackTrace();
		}		
		
		// TODO : show only a subset of most relevant items. Need to come up with a simple ranking
		// function for the same.
		
		DBUtils.closeDBConnection(dbConn);
		DBUtils.cleanDBResources(prepStmt);
		
		return items;
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
		sql.append("SELECT id, name, address, zip, city, state FROM " + Constants.DB_STORE_TABLE + " WHERE 1=0 ");

		/**
		 * If store id or name is specified, they can uniquely identify a store.
		 * Else, base the search results on remaining parameters.
		 */
		if(searchStoreId != null) {
			sql.append(" OR id = ").append(searchStoreId);
		}
		else if(searchName != null) {
			sql.append(" OR LOWER(name) LIKE '%").append(searchName.toLowerCase()).append("%'");
		}
		else if(searchAddress != null) {
			sql.append(" OR LOWER(address) LIKE '%").append(searchAddress.toLowerCase()).append("%'");
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

		DBUtils.closeDBConnection(dbConn);
		DBUtils.cleanDBResources(prepStmt);
		
		return stores;
	}
}
