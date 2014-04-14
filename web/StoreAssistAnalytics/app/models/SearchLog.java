package models;

import java.util.Date;
import java.util.Map;

import javax.persistence.*;

import play.db.ebean.Model;

/**
 * Represents a search log for an item in the stores database.
 * @author excelsior
 *
 */
@Entity
public class SearchLog {
	@Id
	public Long id;
	
	public String itemSearchString;
	public int storeId;
	public Date searchTime;
	
	public SearchLog(String searchString, int storeId, Date searchTime)
	{
		this.itemSearchString = searchString;
		this.storeId = storeId;
		this.searchTime = searchTime;
	}
	
	public static Model.Finder<Long,SearchLog> find = new Model.Finder<Long,SearchLog>(Long.class, SearchLog.class);
	
	/**
	 * Returns the frequency of most searched items.
	 * @param numItemsToSearch
	 * @return
	 */
	public static Map<String, Double> findTopNSearchItems(int numItemsToSearch)
	{
		
	}
}
