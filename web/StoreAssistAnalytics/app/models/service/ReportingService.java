package models.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import models.Constants;
import models.SearchFilter;
import models.SearchFilter.AggregativeLevel;
import models.SearchLog;
import models.utils.DBUtils;
import play.Logger;

import com.google.common.collect.Lists;
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
	public Map<String, Double> getOverallItemSearchSummaryReport(int numResults, SearchFilter filter)
	{
		Date startTime = filter.getStartTime();
		Date endTime = filter.getEndTime();
		
		StringBuilder searchSQL = new StringBuilder();
		searchSQL.append(" SELECT search_token, COUNT(1) AS cnt");
		searchSQL.append(" FROM ").append(Constants.DB_LOG_TABLE);
		searchSQL.append(" WHERE search_time >= '" + formatter.format(startTime) + "'");
		searchSQL.append(" AND search_time < '" + formatter.format(endTime) + "'");
		searchSQL.append(" GROUP BY search_token");
		searchSQL.append(" ORDER BY cnt DESC");
		searchSQL.append(" LIMIT " + numResults);
		
		Logger.error("SQL : " + searchSQL.toString());
		
		Connection dbConn = DBUtils.getDBConnection();
		PreparedStatement prepStmt = null;
		
		Map<String, Double> itemSearchSummary = Maps.newHashMap();
		try {
			prepStmt = dbConn.prepareStatement(searchSQL.toString());
			
			int totalLogs = getTotalLogs(startTime, endTime);
			ResultSet rs = prepStmt.executeQuery();
			double cumulativePercent = 0.0;
			while(rs.next()) {
				String searchItem = rs.getString("search_token");
				int count = rs.getInt("cnt");
				
				double searchPercentage = (count*100)/(double)totalLogs;
				cumulativePercent += searchPercentage;
				itemSearchSummary.put(searchItem, searchPercentage);
			}
			
			if(Double.compare(cumulativePercent, 100.0) != 0) {
				itemSearchSummary.put(Constants.REMAINING_ITEMS, 100 - cumulativePercent);				
			}
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
	 * Retrieves the summary report for all the categories searched within a time range.
	 * 
	 * Results is always a percentage wise split of each category's search history in the
	 * specified time range.
	 */
	public Map<String, Double> getOverallCategorySearchSummaryReport(int numResults, SearchFilter filter)
	{
		Date startTime = filter.getStartTime();
		Date endTime = filter.getEndTime();
		
		StringBuilder searchSQL = new StringBuilder();
		searchSQL.append(" SELECT item.section AS category, COUNT(1) AS cnt");
		searchSQL.append(" FROM ").append(Constants.DB_LOG_TABLE).append(" log");
		searchSQL.append(" LEFT OUTER JOIN ").append(Constants.DB_ITEM_TABLE).append(" item ON (item.name = log.search_token)");
		searchSQL.append(" WHERE log.search_time >= '" + formatter.format(startTime) + "'");
		searchSQL.append(" AND log.search_time < '" + formatter.format(endTime) + "'");
		searchSQL.append(" GROUP BY item.section");
		searchSQL.append(" ORDER BY cnt DESC");
		searchSQL.append(" LIMIT " + numResults);
		
		Logger.error("SQL : " + searchSQL.toString());
		
		Connection dbConn = DBUtils.getDBConnection();
		PreparedStatement prepStmt = null;
		
		Map<String, Double> itemSearchSummary = Maps.newHashMap();
		try {
			prepStmt = dbConn.prepareStatement(searchSQL.toString());
			
			int totalLogs = getTotalLogs(startTime, endTime);
			ResultSet rs = prepStmt.executeQuery();
			double cumulativePercent = 0.0;
			while(rs.next()) {
				String category = rs.getString("category");
				if(category == null || category.toLowerCase().equals("null")) {
					continue;
				}
				
				int count = rs.getInt("cnt");
				
				double searchPercentage = (count*100)/(double)totalLogs;
				cumulativePercent += searchPercentage;
				itemSearchSummary.put(category, searchPercentage);
			}
			
			if(Double.compare(cumulativePercent, 100.0) != 0) {
				itemSearchSummary.put(Constants.REMAINING_ITEMS, 100 - cumulativePercent);				
			}
		}
		catch(Exception e) {
			Logger.error("Failed to get category search summary results. Reason : " + e.getMessage());
			e.printStackTrace();
		}		
		
		DBUtils.closeDBConnection(dbConn);
		DBUtils.cleanDBResources(dbConn, prepStmt);

		return itemSearchSummary;
	}
	
	public Map<String, Integer> getItemBasedSearchSummaryReport(String item, SearchFilter filter)
	{
		filter.setItems(Lists.newArrayList(item));
		return getTimeBasedSearchSummaryReport(filter);
	}
	
	public Map<String, Integer> getTimeBasedSearchSummaryReport(SearchFilter filter)
	{
		String commonSQL = getQueryCommonSQL(filter);
		
		StringBuilder searchSQL = new StringBuilder();
		if(filter.getSummaryLevel().equals(AggregativeLevel.TIME_HOUR)) {
			searchSQL.append(" SELECT HOUR(search_time) AS time_token, COUNT(1) AS cnt");
		}
		else if(filter.getSummaryLevel().equals(AggregativeLevel.TIME_DAY)) {
			searchSQL.append(" SELECT DAYOFMONTH(search_time) AS time_token, COUNT(1) AS cnt");
		}
		else if(filter.getSummaryLevel().equals(AggregativeLevel.TIME_YEAR_MONTH)) {
			searchSQL.append(" SELECT MONTH(search_time) AS time_token, COUNT(1) AS cnt");
		}
		
		searchSQL.append(commonSQL);
		searchSQL.append(" GROUP BY time_token");
		searchSQL.append(" ORDER BY time_token");
		
		Logger.error("SQL : " + searchSQL.toString());
		
		Connection dbConn = DBUtils.getDBConnection();
		PreparedStatement prepStmt = null;
		
		Map<String, Integer> timeBasedSearchSummary = Maps.newHashMap();
		try {
			prepStmt = dbConn.prepareStatement(searchSQL.toString());

			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				String searchItem = rs.getString("time_token");
				int count = rs.getInt("cnt");

				timeBasedSearchSummary.put(searchItem, count);
			}
		}
		catch(Exception e) {
			Logger.error("Failed to get time-based search summary results. Reason : " + e.getMessage());
			e.printStackTrace();
		}		
		
		DBUtils.closeDBConnection(dbConn);
		DBUtils.cleanDBResources(dbConn, prepStmt);

		return timeBasedSearchSummary;		
	}
	
	private String getQueryCommonSQL(SearchFilter filter)
	{
		Date startTime = filter.getStartTime();
		Date endTime = filter.getEndTime();
		
		StringBuilder searchSQL = new StringBuilder();
		
		searchSQL.append(" FROM ").append(Constants.DB_LOG_TABLE);
		searchSQL.append(" WHERE search_time >= '" + formatter.format(startTime) + "'");
		searchSQL.append(" AND search_time < '" + formatter.format(endTime) + "'");
		if(filter.getItems() != null) {
			searchSQL.append(" AND LOWER(search_token) IN (");
			for(String item : filter.getItems()) {
				searchSQL.append("'" + item.toLowerCase() + "'");
			}
			searchSQL.append(" )");
		}
		
		return searchSQL.toString();
	}
	
	/**
	 * Get the total number of log entries in database between the specified time intervals
	 */
	public int getTotalLogs(Date startTime, Date endTime)
	{
		int totalSearchLogs = 0;
		StringBuilder totalLogsSQL = new StringBuilder();
		totalLogsSQL.append(" SELECT COUNT(1) AS cnt FROM " + Constants.DB_LOG_TABLE);
		totalLogsSQL.append(" WHERE search_time >= '" + formatter.format(startTime) + "'");
		totalLogsSQL.append(" AND search_time < '" + formatter.format(endTime) + "'");		
		
		Connection dbConn = DBUtils.getDBConnection();
		PreparedStatement prepStmt = null;
		try {
			prepStmt = dbConn.prepareStatement(totalLogsSQL.toString());
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
	
	public List<String> getAllItemNames()
	{
		List<String> itemNames = Lists.newArrayList();
		String distintItemsSQL = "SELECT DISTINCT(name) AS name FROM " + Constants.DB_ITEM_TABLE + " ORDER BY name";
		
		Connection dbConn = DBUtils.getDBConnection();
		PreparedStatement prepStmt = null;
		try {
			prepStmt = dbConn.prepareStatement(distintItemsSQL);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				itemNames.add(rs.getString("name"));
			}
		}
		catch(Exception e) {
			Logger.error("Failed to get distinct item names. Reason : " + e.getMessage());
			e.printStackTrace();
		}		
		
		DBUtils.closeDBConnection(dbConn);
		DBUtils.cleanDBResources(dbConn, prepStmt);	
		
		return itemNames;		
	}
	
	public List<SearchLog> getSearchLogs(int numLogs)
	{
		List<SearchLog> searchLogs = Lists.newArrayList();
		
		String logFetchSQL = 
			"SELECT search_token, store_id, search_time FROM " + Constants.DB_LOG_TABLE + " ORDER BY search_time DESC LIMIT " + numLogs; 
		
		Connection dbConn = DBUtils.getDBConnection();
		PreparedStatement prepStmt = null;
		try {
			prepStmt = dbConn.prepareStatement(logFetchSQL);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				String searchToken = rs.getString("search_token");
				int storeId = rs.getInt("store_id");
				String dateString = rs.getString("search_time").substring(0, 19);
				Date searchDate = DateUtils.parseDate(dateString, Constants.DATE_FORMAT);
				
				searchLogs.add(new SearchLog(searchToken, storeId, searchDate));
			}
		}
		catch(Exception e) {
			Logger.error("Failed to get search logs. Reason : " + e.getMessage());
			e.printStackTrace();
		}		
		
		DBUtils.closeDBConnection(dbConn);
		DBUtils.cleanDBResources(dbConn, prepStmt);	
		
		return searchLogs;
	}
}
