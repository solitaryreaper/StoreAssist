package com.storeassist.model;

import android.widget.Toast;
import com.storeassist.utils.AppConstants;

public class ItemLocationQuery
{
	protected String mStoreIdentifier;
	protected String mItemName;
	
	/**
	 * @param storeIdentifier
	 * @param itemName
	 */
	public ItemLocationQuery(String storeIdentifier, String itemName)
	{
		super();
		mStoreIdentifier = storeIdentifier;
		mItemName = itemName;
	}
	/**
	 * @return the storeIdentifier
	 */
	public String getStoreIdentifier()
	{
		return mStoreIdentifier;
	}
	/**
	 * @param storeIdentifier the storeIdentifier to set
	 */
	public void setStoreIdentifier(String storeIdentifier)
	{
		mStoreIdentifier = storeIdentifier;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName()
	{
		return mItemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName)
	{
		mItemName = itemName;
	}
	
	/**
	 * Generate URL for the ItemLocationQuery
	 * 
	 * @return WebAPI URL
	 */
	public String get_WebAPI_SearchItem_URL()
	{
		// Generate URL
		// Example: "http://107.170.62.160:9000/api/searchItem?storeIdentifier=Madison&item=Pickle"
		String url = "http://" + AppConstants.ITEM_LOCATION_SERVER_IP + ":" + AppConstants.ITEM_LOCATION_SERVER_PORT + "/" +
					"api/" + AppConstants.WEBAPI_SEARCH_ITEM + 
					"?" + AppConstants.URLTAG_STORE_IDENTIFIER + "=" + mStoreIdentifier +
					"&" + AppConstants.URLTAG_ITEM + "=" + mItemName;
		
		return url;
	}
}
