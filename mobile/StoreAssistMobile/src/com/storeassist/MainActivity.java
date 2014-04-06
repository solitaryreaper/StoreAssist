package com.storeassist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.storeassist.model.ItemLocation;

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
	{
		// TODO: Validate the item string 
		String itemName = ((EditText) findViewById(R.id.edittext_item)).getText().toString();
		
		// Generate URL
		String url = "http://" + AppConstants.ITEM_LOCATION_SERVER_IP + ":" + AppConstants.ITEM_LOCATION_SERVER_PORT + "/" +
					"api/" + AppConstants.WEBAPI_SEARCH_ITEM + 
					"?" + AppConstants.URLTAG_STORE_IDENTIFIER + "=Madison" +
					"&" + AppConstants.URLTAG_ITEM + "=" + itemName;
		
		if(AppConstants.SHOW_LOGS)
			Toast.makeText(this, url, Toast.LENGTH_LONG).show();
		
		// Example: "http://107.170.62.160:9000/api/searchItem?storeIdentifier=Madison&item=Pickle"
		String[] urls = new String[] {url};

		DownloadDataTask downloadDataTask = new DownloadDataTask(this);
		downloadDataTask.execute(urls);
	}
	
	public void displayItemLocation(ItemLocation itemLoc)
	{
		// TODO: Launch ItemLocationActivity passing in ItemLocation Object.
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
			break;
			
		}
		
		TextView locationTextView = (TextView) findViewById(R.id.text_error);
		locationTextView.setVisibility(View.VISIBLE);
		locationTextView.setText(errorString);
	}

}
