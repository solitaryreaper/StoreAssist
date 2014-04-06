package com.storeassist.model;

public class ItemLocation
{
	// Member Variables
	protected String mSection;
	protected String mAisle;
	protected String mShelf;
	
	// Methods
	
	/**
	 * @param section	Item Section Name
	 * @param aisle	Item Aisle Location
	 * @param shelf	Item Shelf Location
	 */
	public ItemLocation(String section, String aisle, String shelf)
	{
		mSection = section;
		mAisle = aisle;
		mShelf = shelf;
	}

	/**
	 * @return the mSection
	 */
	public String getSection()
	{
		return mSection;
	}

	/**
	 * @param section the mSection to set
	 */
	public void setSection(String section)
	{
		mSection = section;
	}

	/**
	 * @return the mAisle
	 */
	public String getAisle()
	{
		return mAisle;
	}

	/**
	 * @param aisle the mAisle to set
	 */
	public void setAisle(String aisle)
	{
		mAisle = aisle;
	}

	/**
	 * @return the mShelf
	 */
	public String getShelf()
	{
		return mShelf;
	}

	/**
	 * @param shelf the mShelf to set
	 */
	public void setShelf(String shelf)
	{
		mShelf = shelf;
	}
	
	
	
	
	
	
	
}
