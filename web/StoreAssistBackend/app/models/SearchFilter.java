package models;

import java.util.Date;
import java.util.List;

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

	private List<String> items;
	
	public SearchFilter()
	{
		
	}
	
	public SearchFilter(Date startDate, Date endDate)
	{
		this.startTime = startDate;
		this.endTime = endDate;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}
	
	
}
