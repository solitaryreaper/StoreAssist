package com.storeassist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.storeassist.model.ItemLocation;
import com.storeassist.model.ItemLocationQuery;
import com.storeassist.utils.AppConstants;

/**
 * MainActivity
 * 
 * Defines the main screen of the StoreAssist application. It should include the logic
 * of getting the address of the place where the user is (using, Google Maps, GPS, etc, I 
 * don't know). It should include a direct edit text for user to put in the item name
 * and search it in the store. Cool, huh?
 * 
 * @author prakhar
 *
 */
public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		IdentifyStoreTask identifyStoreTask = new IdentifyStoreTask(this);
		identifyStoreTask.execute();
		
//		setUI();
	}
	
	public void setUI()
	{
		
		if(AppConstants.DEMO_BUILD)
		{
			TextView storeInfoTextView = (TextView) findViewById(R.id.text_store_info);
			storeInfoTextView.setText("Fresh Madison Market\n704 University Avenue, Madison, WI");
		}
		else
		{
			// BIG TODOs:
			// 1. Get Lat and Lon
			// 2. Get the Exact Address (or may approximate one)
			// 3. Send the user's address (incl. zip) to the server to get the Store Name(s)from the address
			// 4. If multiple, let the user identify the store, otherwise just display the single store
			
			// Fresh Madison Market (704 University Ave): 43.073012, -89.397511
			// 2110 University Avenue: Lat= 43.073156, Lon= -89.422204
//			List<Address> validAddresses = AppUtils.getAddress(this, 43.073012, -89.397511);
//			
//			if(validAddresses.size() == 1)
//			{
//				// Show the Store Name and Address on the TextView.
//			}
//			else
//			{
//				// TODO: Show a dropdown. Ask user to select the store.
//			}
		}
		
		// Make the 'Welcome to' textview visible.
		findViewById(R.id.text_welcome_to).setVisibility(View.VISIBLE);
		
		// Get the image for the Store Name/address
		populateMapImage();
		
		// Set up the listener for the 'Done' button on virtual keyboard
		EditText itemNameEditText = (EditText) findViewById(R.id.edittext_item);
		itemNameEditText.setOnEditorActionListener(new OnEditorActionListener()
		{
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
			{
				if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
						|| (actionId == EditorInfo.IME_ACTION_DONE))
				{
					locateItem();
				}
				return false;
			}
		});

	}

	public void populateMapImage()
	{
		// TODO: Get it using Google Maps Image API
		ImageView streetMapImage = (ImageView) findViewById(R.id.image_street_map);
		streetMapImage.setImageResource(R.drawable.demo_street_map);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onButtonClick(View v)
	{
		switch(v.getId())
		{
		case R.id.btn_locate:
			
			// Start an Async Task to download json given the specific URL
			locateItem();
			
			break;
			
		default:
		}
	}
	
	/**
	 * Method to locate an item mentioned by the user in the EditText.
	 */
	private void locateItem()
	{ locateItem(((EditText) findViewById(R.id.edittext_item)).getText().toString()); }
	
	private void locateItem(String itemName)
	{
		if(itemName == null || itemName.equals(""))
		{
			displayErrorText(AppConstants.ERROR_ITEM_INVALID);
			return;
		}
			
		// TODO: Validate the item string 
		
		ItemLocationQuery itemLocationQuery = new ItemLocationQuery("1", itemName);	// "1" for Madison
		
//		// Generate URL
//		String url = "http://" + AppConstants.ITEM_LOCATION_SERVER_IP + ":" + AppConstants.ITEM_LOCATION_SERVER_PORT + "/" +
//					"api/" + AppConstants.WEBAPI_SEARCH_ITEM + 
//					"?" + AppConstants.URLTAG_STORE_IDENTIFIER + "=Madison" +
//					"&" + AppConstants.URLTAG_ITEM + "=" + itemName;
		
		// Example: "http://107.170.62.160:9000/api/searchItem?storeIdentifier=Madison&item=Pickle"
//		String[] urls = new String[] {url};

		DownloadDataTask downloadDataTask = new DownloadDataTask(this, itemLocationQuery);
		downloadDataTask.execute();
	}
	
	/**
	 * TODO: 
	 * 1. Should accept an array of ItemLocation
	 * 2. Should launch ItemLocationActivity passing in Array of ItemLocation Objects
	 * 
	 * @param itemLoc
	 */
	public void displayItemLocation(ItemLocation[] itemLocArray)
	{
		Intent i = new Intent(this, ItemLocationActivity.class);
		i.putExtra(AppConstants.ITEM_LOCATION_ARRAY, itemLocArray);
		startActivity(i);
	}
	
	/**
	 * Method to display any error that comes up while searching location for the item.
	 * 
	 * @param errorCode
	 */
	public void displayErrorText(int errorCode)
	{
		String errorString = "";
		
		switch(errorCode)
		{
		case AppConstants.ERROR_ITEM_INVALID:
			
			errorString = "Sorry, item entered is invalid.";
			break;
			
		case AppConstants.ERROR_ITEM_NOT_PRESENT:
			
			// TODO : Add implementation here.
			String itemName = ((EditText) findViewById(R.id.edittext_item)).getText().toString();
			errorString = "Sorry, '" + itemName  +"' is not present in the store.";
			break;
			
		}
		
		TextView locationTextView = (TextView) findViewById(R.id.text_error);
		locationTextView.setVisibility(View.VISIBLE);
		locationTextView.setText(errorString);
	}

}
