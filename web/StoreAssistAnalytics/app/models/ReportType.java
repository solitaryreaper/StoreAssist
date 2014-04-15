package models;

/**
 * All the reports applicable to the search log data.
 * 
 * @author excelsior
 *
 */
public enum ReportType {
	// Summary of the overall nature (topN) of search queries over a period of time
	SEARCH_SUMMARY_REPORT,
	
	// Search history for a particular item over a period of time
	SEARCH_BY_ITEM_REPORT,
	
	// Time based search query report
	SEARCH_BY_TIME_REPORT
}
