package com.storeassist;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
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
		
		TextView sectionValueTextView = (TextView) findViewById(R.id.text_section_value);
		sectionValueTextView.setText(mItemLocationArray[0].getSection());
		
		TextView aisleValueTextView = (TextView) findViewById(R.id.text_aisle_value);
		aisleValueTextView.setText(mItemLocationArray[0].getAisle());
		
		TextView shelfValueTextView = (TextView) findViewById(R.id.text_shelf_value);
		shelfValueTextView.setText(mItemLocationArray[0].getShelf());
		
	}
}
