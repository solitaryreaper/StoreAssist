package models.service;

import java.util.Date;

import ch.qos.logback.core.util.AggregationType;
import models.ReportType;

/**
 * Service layer that generates the different analytics reports using item search log data.
 * 
 * @author excelsior
 *
 */
public class ReportingService {

	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param aggregationLevel
	 * @param reportType
	 */
	public void getReport(Date startTime, Date endTime, AggregationType aggregationLevel, ReportType reportType)
	{
		
	}
}
