package com.storeassist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemLocation implements Parcelable
{
	// Member Variables
	// TODO: Add Item Name 
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
	
	public ItemLocation(Parcel in)
	{
		mSection = in.readString();
		mAisle = in.readString();
		mShelf = in.readString();
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

	@Override
	public int describeContents()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeString(mSection);
		dest.writeString(mAisle);
		dest.writeString(mShelf);
	}
	
	public static final Parcelable.Creator<ItemLocation> CREATOR = new Creator<ItemLocation>()
	{

		@Override
		public ItemLocation[] newArray(int size)
		{
			return new ItemLocation[size];
		}

		@Override
		public ItemLocation createFromParcel(Parcel source)
		{
			return new ItemLocation(source);
		}
	};
	
	
	
	
	
}
