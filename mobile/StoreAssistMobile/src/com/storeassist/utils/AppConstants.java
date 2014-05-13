package com.storeassist.utils;

public class AppConstants
{
	// App Related
	public static final boolean SHOW_LOGS = false;
	public static final boolean DEMO_BUILD = true;
	public static final String ITEM_LOCATION_ARRAY = "item_location_array";
	public static final String ITEM_NAME = "item_name";
	
	// Error Codes
	public static final int ERROR_ITEM_INVALID = 1;
	public static final int ERROR_ITEM_NOT_PRESENT = 2;
	public static final int ERROR_STORE_INVALID = 11;
	public static final int ERROR_STORE_NOT_FOUND = 12;
	public static final int ERROR_SERVER_NOT_REACHABLE = 21;
	public static final int ERROR_PROBLEM_WITH_SERVER_IP = 22;
	
	// Item Location Server
	public static final String ITEM_LOCATION_SERVER_IP = "173.255.117.89"; // "107.170.62.160";
	public static final String ITEM_LOCATION_SERVER_PORT = "9000";
	
	// Web APIs
	public static final String WEBAPI_SEARCH_ITEM = "searchItem";						// http://107.170.62.160:9000/api/searchItem?storeId=1&item=vegetable
	public static final String WEBAPI_SEARCH_STORE_BY_ZIP = "searchStoreByZip";			// http://107.170.62.160:9000/api/searchStoreByZip?zip=53715
	public static final String WEBAPI_SEARCH_STORE_BY_ADDRESS = "searchStoreByAddress";	// http://107.170.62.160:9000/api/searchStoreByAddress?address=703%20university%20avenue 
	public static final String WEBAPI_GET_SIMILAR_ITEM_NAMES= "getItemNameProposals";	// http://107.170.62.160:9000/api/getItemNameProposals?itemRegex=vegetable&storeId=1
	
	// URL Tags
	public static final String URLTAG_STORE_IDENTIFIER = "storeId";
	public static final String URLTAG_ITEM = "item";
	
	// RESPONSE JSON TAGS
	public static final String JSONTAG_SECTION = "section";
	public static final String JSONTAG_AISLE = "aisle";
	public static final String JSONTAG_SHELF = "shelf";
	
	// Valid Country Codes (Countries with our customers)
	public static final String[] CUSTOMER_COUNTRY_CODE_LIST = {"US", "IN"};
	
	
}
