package com.storeassist;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.storeassist.model.ItemLocation;
import com.storeassist.model.ItemLocationQuery;
import com.storeassist.utils.AppConstants;

public class DownloadDataTask extends AsyncTask<Void, Void, String>
{
	// Member Variables
	ProgressDialog mProgressDialog = null;
	ItemLocationQuery mItemLocationQuery = null;
	Context mContext = null;
	
	// Methods
	
	/**
	 * Constructor
	 * 
	 * @param c		Context of MainActivity
	 */
	public DownloadDataTask(Context c, ItemLocationQuery itemLocationQuery)
	{
		mContext = c;
		mItemLocationQuery = itemLocationQuery;
	}
	
	/**
	 * Method called in background thread. We're basically downloading result from the searchItem Web API. 
	 */
	@Override
	protected String doInBackground(Void... params)
	{ 
		String response = "";
		String url = mItemLocationQuery.get_WebAPI_SearchItem_URL(); 
		 
		if(AppConstants.SHOW_LOGS)
			Toast.makeText(mContext, url, Toast.LENGTH_LONG).show();
		
		// HTTP CODE
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try
		{
			HttpResponse execute = client.execute(httpGet);
			InputStream content = execute.getEntity().getContent();
	 
			BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
			String s = "";
			while ((s = buffer.readLine()) != null)
			{
				response += s;
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return response;
	}

	@Override
	protected void onPreExecute()
	{
		mProgressDialog = new ProgressDialog(mContext);
		 
		mProgressDialog.setTitle("Please wait");
		mProgressDialog.setMessage("Locating Item...");
		mProgressDialog.setCancelable(false);
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.show();
	}

	@Override
	protected void onPostExecute(String response)
	{
		int locationsCount = -1;
		
		// Dismiss the Progress Dialog first.
		if (mProgressDialog != null)
			mProgressDialog.dismiss();
		
		if(response == null || response.equals(""))
			((MainActivity)mContext).displayErrorText(AppConstants.ERROR_ITEM_INVALID);
			
		Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
		
		// Check if the response is valid JSON
		try
		{
			JSONObject responseJSONObject = new JSONObject(response);
			
			JSONArray itemJSONArray = responseJSONObject.getJSONArray(mItemLocationQuery.getItemName());
			
			locationsCount = itemJSONArray.length();
			ItemLocation[] itemLocationArray = new ItemLocation[locationsCount];
			
			if(locationsCount > 0)
			{
				for(int i = 0; i < locationsCount; i++)
				{
					JSONObject itemLocationObject = itemJSONArray.getJSONObject(0);
					
					String shelfVal = null, sectionVal = null, aisleVal = null;
							
					if(itemLocationObject.has(AppConstants.JSONTAG_SECTION))
						sectionVal = itemLocationObject.getString(AppConstants.JSONTAG_SECTION);
					if(itemLocationObject.has(AppConstants.JSONTAG_AISLE))
						aisleVal = itemLocationObject.getString(AppConstants.JSONTAG_AISLE);
					if(itemLocationObject.has(AppConstants.JSONTAG_SHELF))
						shelfVal = itemLocationObject.getString(AppConstants.JSONTAG_SHELF);
					
					ItemLocation itemLocation = new ItemLocation(sectionVal, aisleVal, shelfVal);
					itemLocationArray[i] = itemLocation;
				}
				
				// If yes, launch another activity with the result location
				((MainActivity)mContext).displayItemLocation(itemLocationArray);
			}
		}
		catch (JSONException ex)
		{
			// If no, depending on the code, show 
			//	TODO:- if item is not present in the store, OR,
			//		- if item item entered is not valid.
		}
		finally
		{
			if(locationsCount == 0)
			{
				((MainActivity)mContext).displayErrorText(AppConstants.ERROR_ITEM_NOT_PRESENT);
			}
			else
				((MainActivity)mContext).displayErrorText(AppConstants.ERROR_ITEM_INVALID);
		}
	}
}
