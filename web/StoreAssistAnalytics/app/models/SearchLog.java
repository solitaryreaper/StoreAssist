package models;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
/**
 * Represents a search log for an item in the stores database.
 * @author excelsior
 *
 */
@Entity
@Table(name="search_log_new")
public class SearchLog extends Model {
	private static final long serialVersionUID = 1L;

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
		// TODO
		return null;
	}
	
	public static List<SearchLog> getAllSearchLogs()
	{
		return find.all();
	}
}
