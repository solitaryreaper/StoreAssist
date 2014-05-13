package models;

import java.text.SimpleDateFormat;

public class Constants {

	public static final int NUM_RESULTS_AUTO_COMPLETE = 4;
	
	// DB entities
	public static final String ITEM_DB = "storeassist_fmm";
	
	public static final String DB_ITEM_TABLE = "item_info";
	public static final String DB_STORE_TABLE = "store";
	public static final String DB_LOG_TABLE = "search_log";
	
	// Store entities
	public static final int FRESH_MADISON_MARKET = 1;
	
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final int NUM_SEARCH_SUMMARY_RESULTS = 10;
	
	public static final String REMAINING_ITEMS = "Others";
	
	public static final String DEFAULT_DATETIME_SUFFIX = " 00:00:00";
	
	public static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(Constants.DATE_FORMAT);	
}
