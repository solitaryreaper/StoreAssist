package com.storeassist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

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
	
	private void locateItem()
	{
		
		String[] urls = new String[] { "http://localhost:9000/api/searchItem?storeIdentifier=Madison&item=Toothpaste" };

		DownloadDataTask downloadDataTask = new DownloadDataTask(this);
		downloadDataTask.execute(urls);
	}
	
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
		locationTextView.setText(errorString);
	}

}
