package models.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import play.Logger;
import play.db.DB;

/**
 * Utility class for managing all database operations.
 * 
 * @author excelsior
 * 
 */
public class DBUtils {

	/**
	 * Creates a JDBC connection to the PostgreSQL database
	 */
	public static Connection getDBConnection() {
		DataSource ds = DB.getDataSource();
		Connection dbConn = null;
        try {
            dbConn = ds.getConnection();
        } catch (SQLException e) {
            Logger.error("Failed to get connection to database.");
            System.exit(1);
        }

		return dbConn;
	}

	/**
	 * Important to close the database connection, so that database resources
	 * are not tied up.
	 * 
	 * @param dbConn
	 *            JDBC connection to the database
	 */
	public static void closeDBConnection(Connection dbConn) {
		try {
			dbConn.close();
			//Logger.debug("Closed db connection ..");
		} catch (SQLException e) {
			System.err.println("Failed to close connection to the PostgreSQL database ..");
			e.printStackTrace();
		}
	}

	/**
	 * Clean up the database resources after the query has completed.
	 * 
	 * @param dbConn
	 * @param prepStmt
	 */
	public static void cleanDBResources(Connection dbConn, PreparedStatement prepStmt) {
		closeDBConnection(dbConn);
		try {
			prepStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}