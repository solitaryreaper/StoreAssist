package com.storeassist;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.storeassist.model.ItemLocation;
import com.storeassist.utils.AppConstants;

public class ItemLocationActivity extends Activity
{
	private ItemLocation[] mItemLocationArray = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_location);
		
		// Get Item Location Array from Parcables passed through intent.
		Parcelable[] allParcelables = getIntent().getExtras().getParcelableArray(AppConstants.ITEM_LOCATION_ARRAY);
		mItemLocationArray = new ItemLocation[allParcelables.length];
		for (int i = 0; i < allParcelables.length; i++) 
		{
			mItemLocationArray[i] = (ItemLocation)allParcelables[i];
		}
		
		// Debugging
		for(ItemLocation itemLoc : mItemLocationArray)
			Toast.makeText(this, "Se: " + itemLoc.getSection() + " Ai: " + itemLoc.getAisle() + " Sh: " + itemLoc.getShelf(), Toast.LENGTH_SHORT).show();

		// Now let's set the UI of the Activity.
		setUI();
				
	}
	
	/**
	 * Method to set up the UI of ItemLocationActivity.
	 */
	void setUI()
	{
		if(mItemLocationArray == null || mItemLocationArray.length == 0)
			return;
		
		int locationsCount = mItemLocationArray.length;
		String locationString = "location:";
		if(locationsCount > 1)
			locationString = locationsCount + " locations:";

		TextView startLabelTextView = (TextView) findViewById(R.id.text_start_label);
		startLabelTextView.setText("You can find " + "item " + "at following " + locationString);
		
		// Displaying the Location now
		
		TextView sectionValueTextView = (TextView) findViewById(R.id.text_section_value);
		TextView sectionTextView = (TextView) findViewById(R.id.text_section);
		displayItemLocationRow(mItemLocationArray[0].getSection(), sectionTextView, sectionValueTextView);
		
		TextView aisleValueTextView = (TextView) findViewById(R.id.text_aisle_value);
		TextView aisleTextView = (TextView) findViewById(R.id.text_aisle);
		displayItemLocationRow(mItemLocationArray[0].getAisle(), aisleTextView, aisleValueTextView);

		TextView shelfValueTextView = (TextView) findViewById(R.id.text_shelf_value);
		TextView shelfTextView = (TextView) findViewById(R.id.text_shelf);
		displayItemLocationRow(mItemLocationArray[0].getShelf(), shelfTextView, shelfValueTextView);

	}
	
	private void displayItemLocationRow(String itemLocationValue, TextView itemLocationTextView, TextView itemLocationValueTextView)
	{
		if(itemLocationValue != null && !itemLocationValue.equals(""))
		{
			itemLocationTextView.setVisibility(View.VISIBLE);
			itemLocationValueTextView.setVisibility(View.VISIBLE);
			itemLocationValueTextView.setText(itemLocationValue);
		}
		else
		{
			itemLocationTextView.setVisibility(View.GONE);
			itemLocationValueTextView.setVisibility(View.GONE);
		}
	}
	
}
