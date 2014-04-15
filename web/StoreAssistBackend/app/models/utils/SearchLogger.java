package models.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import models.Constants;

/**
 * Logs the search queries to power the analytics platform.
 * @author excelsior
 *
 */
public class SearchLogger {

	private static Logger LOG = Logger.getLogger(SearchLogger.class.getSimpleName());
	
	public static void insertSearchQuery(String searchString, int storeId)
	{
		StringBuilder searchLogInsertSQL = new StringBuilder();
		searchLogInsertSQL.append("INSERT INTO " + Constants.DB_LOG_TABLE);
		searchLogInsertSQL.append("(search_token, store_id, search_time)");
		searchLogInsertSQL.append(" values (?, ?, ?)");
		
		Connection dbConn = DBUtils.getDBConnection();
		PreparedStatement prepStmt = null;
		
		PreparedStatement preparedSQL = null;
		try {
			preparedSQL = dbConn.prepareStatement(searchLogInsertSQL.toString());

			preparedSQL.setString(1, searchString);
			preparedSQL.setInt(2, storeId);
			preparedSQL.setString(3, getCurrentTime());

			LOG.info("Prepared Statement : " + preparedSQL.toString());

			int rowsUpdated = preparedSQL.executeUpdate();
			if(rowsUpdated == 0) {
				LOG.severe("Failed to insert the search log in database for SQL : " + preparedSQL.toString());
			}

		} catch (SQLException e) {
			LOG.severe("SQL query failed !! " + e.getStackTrace().toString());
			e.printStackTrace();
		}
		
		DBUtils.closeDBConnection(dbConn);		
		DBUtils.cleanDBResources(preparedSQL);
	}
	
	// Get current time in MySQL datetime format
	private static String getCurrentTime()
	{
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(dt);		
	}	
}
