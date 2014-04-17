package com.storeassist;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class IdentifyStoreTask extends AsyncTask<Void, Void, Void>
{
	// Member Variables
	ProgressDialog mProgressDialog = null;
	Context mContext = null;
	
	// Methods
	
	/**
	 * Constructor
	 * 
	 * @param c		Context of MainActivity
	 */
	public IdentifyStoreTask(Context c)
	{
		mContext = c;
	}
	
	@Override
	protected Void doInBackground(Void... params)
	{
		try
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected void onPreExecute()
	{
		mProgressDialog = new ProgressDialog(mContext);
		 
//		mProgressDialog.setTitle("Please wait");
		mProgressDialog.setMessage("Getting Your Location...");
		mProgressDialog.setCancelable(false);
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.show();
	}
	
	@Override
	protected void onPostExecute(Void response)
	{
		// Dismiss the Progress Dialog first.
		if (mProgressDialog != null)
			mProgressDialog.dismiss();
		
		((MainActivity)mContext).setStoreRelatedUI();
	}

}
