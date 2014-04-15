package models;

/**
 * All the reports applicable to the search log data.
 * 
 * @author excelsior
 *
 */
public enum ReportType {
	// Summary of the overall nature (topN) of search queries over a period of time
	SEARCH_SUMMARY_REPORT("overall_search"),
	
	// Search history for a particular item over a period of time
	SEARCH_BY_ITEM_REPORT("item_search"),
	
	// Time based search query report
	SEARCH_BY_TIME_REPORT("time_search");
	
	private String reportName;
	
	private ReportType(String name)
	{
		this.reportName = name;
	}
	
	public static ReportType getReportType(String reportName)
	{
		ReportType report = null;
		for(ReportType reportType : ReportType.values()) {
			if(reportType.reportName.equals(reportName)) {
				report = reportType;
				break;
			}
		}
		
		return report;
	}
	
}
