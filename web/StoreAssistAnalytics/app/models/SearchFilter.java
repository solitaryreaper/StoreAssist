package models;

import java.util.Date;

/**
 * Filter parameters to be used for generating various reports.
 * @author excelsior
 *
 */
public class SearchFilter {
	
	/**
	 * Level at which the items have to be aggregated.
	 * @author excelsior
	 *
	 */
	public enum AggregativeLevel {
		TIME_HOUR,
		TIME_DAY,
		TIME_YEAR_MONTH,
		TIME_MONTH_ONLY,
		TIME_YEAR_ONLY,
		ITEM // Show summary results at item level.
	}
	
	private Date startTime;
	private Date endTime = new Date();
	
	private ReportType reportType;
	
	
}
