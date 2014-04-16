package models;

import java.util.Date;
/**
 * Represents a search log for an item in the stores database.
 * @author excelsior
 *
 */
public class SearchLog{
	private String itemSearchString;
	private int storeId;
	private Date searchTime;
	
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

	public String getItemSearchString() {
		return itemSearchString;
	}

	public void setItemSearchString(String itemSearchString) {
		this.itemSearchString = itemSearchString;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public Date getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(Date searchTime) {
		this.searchTime = searchTime;
	}
	
	public String getFormattedDate() {
		return Constants.DATE_FORMATTER.format(getSearchTime());
	}
}
