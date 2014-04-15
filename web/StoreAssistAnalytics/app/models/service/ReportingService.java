package models.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import models.Constants;
import models.SearchFilter;
import models.utils.DBUtils;
import play.Logger;

import com.google.common.collect.Maps;

/**
 * Service layer that generates the different analytics reports using item search log data.
 * 
 * @author excelsior
 *
 */
public class ReportingService {

	private SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
	
	/**
	 * Retrieves the summary report for all the items searched within a time range.
	 * 
	 * Results is always a percentage wise split of each item's search history in the
	 * specified time range.
	 */
	public Map<String, Double> fetchSearchSummaryReport(int numResults, SearchFilter filter)
	{
		Date startTime = filter.getStartTime();
		Date endTime = filter.getEndTime();
		
		StringBuilder searchSQL = new StringBuilder();
		searchSQL.append(" SELECT item_search_string, COUNT(1) AS cnt");
		searchSQL.append(" FROM ").append(Constants.DB_LOG_TABLE);
		searchSQL.append(" WHERE search_time >= '" + formatter.format(startTime) + "'");
		searchSQL.append(" AND search_time < '" + formatter.format(endTime) + "'");
		searchSQL.append(" GROUP BY item_search_string");
		searchSQL.append(" ORDER BY cnt DESC");
		searchSQL.append(" LIMIT " + numResults);
		
		Logger.error("SQL : " + searchSQL.toString());
		
		Connection dbConn = DBUtils.getDBConnection();
		PreparedStatement prepStmt = null;
		
		Map<String, Double> itemSearchSummary = Maps.newHashMap();
		try {
			prepStmt = dbConn.prepareStatement(searchSQL.toString());
			
			int totalLogs = getTotalLogs();
			ResultSet rs = prepStmt.executeQuery();
			double cumulativePercent = 0.0;
			while(rs.next()) {
				String searchItem = rs.getString("item_search_string");
				int count = rs.getInt("cnt");
				
				double searchPercentage = (count*100)/(double)totalLogs;
				cumulativePercent += searchPercentage;
				itemSearchSummary.put(searchItem, searchPercentage);
			}
			
			itemSearchSummary.put(Constants.REMAINING_ITEMS, 100 - cumulativePercent);
		}
		catch(Exception e) {
			Logger.error("Failed to get search summary results. Reason : " + e.getMessage());
			e.printStackTrace();
		}		
		
		DBUtils.closeDBConnection(dbConn);
		DBUtils.cleanDBResources(dbConn, prepStmt);

		return itemSearchSummary;
	}

	/**
	 * Get the total number of log entries in database
	 */
	public int getTotalLogs()
	{
		int totalSearchLogs = 0;
		String totalLogsSQL = "SELECT COUNT(1) AS cnt FROM " + Constants.DB_LOG_TABLE;
		
		Connection dbConn = DBUtils.getDBConnection();
		PreparedStatement prepStmt = null;
		try {
			prepStmt = dbConn.prepareStatement(totalLogsSQL);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				totalSearchLogs = rs.getInt("cnt");
			}
		}
		catch(Exception e) {
			Logger.error("Failed to get total search logs. Reason : " + e.getMessage());
			e.printStackTrace();
		}		
		
		DBUtils.closeDBConnection(dbConn);
		DBUtils.cleanDBResources(dbConn, prepStmt);	
		
		return totalSearchLogs;
	}
}
