package com.storeassist;

import java.util.Locale;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.storeassist.model.ItemLocation;
import com.storeassist.utils.AppConstants;

public class ItemLocationActivity extends Activity
{
	private ItemLocation[] mItemLocationArray = null;
	private String mItemName = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_location);
		
		// Get Item Location Array from Parcables passed through intent.
		Intent intent = getIntent();
		mItemName = intent.getStringExtra(AppConstants.ITEM_NAME);
		Parcelable[] allParcelables = intent.getExtras().getParcelableArray(AppConstants.ITEM_LOCATION_ARRAY);
		mItemLocationArray = new ItemLocation[allParcelables.length];
		for (int i = 0; i < allParcelables.length; i++) 
		{
			mItemLocationArray[i] = (ItemLocation)allParcelables[i];
		}
		
		// Debugging
		if(AppConstants.SHOW_LOGS)
		{
			for(ItemLocation itemLoc : mItemLocationArray)
				Toast.makeText(this, "Se: " + itemLoc.getSection() + " Ai: " + itemLoc.getAisle() + " Sh: " + itemLoc.getShelf(), Toast.LENGTH_SHORT).show();
		}

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
		startLabelTextView.setText("Find \"" + mItemName.toUpperCase(Locale.US) + "\" at:");
//		startLabelTextView.setText("You can find " + "item " + "at following " + locationString);
		
		// Displaying the Location now
		
		TextView sectionValueTextView = (TextView) findViewById(R.id.text_section_value);
		TextView sectionTextView = null;// (TextView) findViewById(R.id.text_section);
		displayItemLocationRow("Section: " + mItemLocationArray[0].getSection(), sectionTextView, sectionValueTextView);
		
		TextView aisleValueTextView = (TextView) findViewById(R.id.text_aisle_value);
		TextView aisleTextView = null;//(TextView) findViewById(R.id.text_aisle);
		displayItemLocationRow("Aisle: " + mItemLocationArray[0].getAisle(), aisleTextView, aisleValueTextView);

//		TextView shelfValueTextView = (TextView) findViewById(R.id.text_shelf_value);
//		TextView shelfTextView = (TextView) findViewById(R.id.text_shelf);
//		displayItemLocationRow(mItemLocationArray[0].getShelf(), shelfTextView, shelfValueTextView);
		int aisleNum = Integer.parseInt(mItemLocationArray[0].getAisle());
		ImageView locMarker = (ImageView) findViewById(R.id.img_item_loc_marker);
		
		AbsoluteLayout.LayoutParams params = ((AbsoluteLayout.LayoutParams) locMarker.getLayoutParams());
		params.x = getLocX(aisleNum);//100;
		params.y = getLocY(aisleNum);//100;
		locMarker.setLayoutParams(params); 
		
//		locMarker.setX(getLocX(aisleNum));
//		locMarker.setY(getLocY(aisleNum));
	}
	
	private int getLocX(int aisleNum)
	{
		switch(aisleNum)
		{
		case 1:
			return 30;
			
		case 2:
			return 75;
			
		case 3:
			return 130;
			
		case 4:
			return 180;
			
		case 5:
			return 230;
			
		case 6:
			return 280;
			
		case 7:
			return 330;
			
		default:
			return 380;
		}
	}
	
	private int getLocY(int aisleNum)
	{
		return 90;
	}
	
	private void displayItemLocationRow(String itemLocationValue, TextView itemLocationTextView, TextView itemLocationValueTextView)
	{
		if(itemLocationValue != null && !itemLocationValue.equals(""))
		{
//			itemLocationTextView.setVisibility(View.VISIBLE);
			itemLocationValueTextView.setVisibility(View.VISIBLE);
			itemLocationValueTextView.setText(itemLocationValue);
		}
		else
		{
//			itemLocationTextView.setVisibility(View.GONE);
			itemLocationValueTextView.setVisibility(View.GONE);
		}
	}
	
	public void onButtonClick(View v)
	{
		switch(v.getId())
		{
		case R.id.btn_locate_new_item:
			
			// Just kill current activity.
			finish();
			
			break;
			
		default:
		}
	}
	
	public ItemLocation[] getItemLocationArray()
	{
		return mItemLocationArray;
	}
	
	public String getItemNameSearched()
	{
		return mItemName;
	}
	
}
