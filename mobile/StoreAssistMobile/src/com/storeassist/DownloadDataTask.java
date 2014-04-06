package com.storeassist;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.storeassist.model.ItemLocation;
import com.storeassist.utils.AppConstants;

public class DownloadDataTask extends AsyncTask<String, Void, String>
{
	ProgressDialog mProgressDialog = null;
	Context mContext = null;
	
	/**
	 * Constructor
	 * 
	 * @param c		Context of MainActivity
	 */
	public DownloadDataTask(Context c)
	{
		mContext = c;
	}
	
	@Override
	protected String doInBackground(String... params)
	{ 
		String response = "";
		 
		for (String url : params)
		{
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
		// Dismiss the Progress Dialog first.
		if (mProgressDialog != null)
			mProgressDialog.dismiss();
		
		if(response == null || response.equals(""))
			((MainActivity)mContext).displayErrorText(AppConstants.ERROR_ITEM_INVALID);
			
		// Check if the response is valid JSON
		try
		{
			JSONObject responseJSONObject = new JSONObject(response);
			
			String sectionVal = responseJSONObject.getString(AppConstants.JSONTAG_SECTION);
			String aisleVal = responseJSONObject.getString(AppConstants.JSONTAG_AISLE);
			String shelfVal = responseJSONObject.getString(AppConstants.JSONTAG_SHELF);
			
			((MainActivity)mContext).displayItemLocation(new ItemLocation(sectionVal, aisleVal, shelfVal));
			
			
			// If yes, launch another activity with the result location
			
		}
		catch (JSONException ex)
		{
			// If no, depending on the code, show 
			//	TODO:- if item is not present in the store, OR,
			//		- if item item entered is not valid.
			
			((MainActivity)mContext).displayErrorText(AppConstants.ERROR_ITEM_INVALID);
		}
		
		Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
	}
}
