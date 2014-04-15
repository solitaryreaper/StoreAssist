package models;

import java.util.Date;

import play.db.ebean.Model;
/**
 * Represents a search log for an item in the stores database.
 * @author excelsior
 *
 */
public class SearchLog extends Model {
	private static final long serialVersionUID = 1L;

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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchLog [itemSearchString=").append(itemSearchString)
				.append(", storeId=").append(storeId).append(", searchTime=")
				.append(searchTime).append("]");
		return builder.toString();
	}
}
