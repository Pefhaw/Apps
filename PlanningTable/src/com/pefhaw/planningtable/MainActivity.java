package com.pefhaw.planningtable;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText textPersonName;
	TextView textDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textPersonName = (EditText)findViewById(R.id.textPersonName);
		textDetails = (TextView)findViewById(R.id.textDetails);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void buttonAddNewClick(View view)  {
  		Intent EditActivity = new Intent(getBaseContext(),EditActivity.class);
  		startActivity(EditActivity);	 		
    }
	
	public void buttonSearchClick(View view)  {
		// Get the stored preferences
		int mode = Activity.MODE_PRIVATE;
		SharedPreferences SharedPreferences = getSharedPreferences(textPersonName.getText().toString(),mode);
		// Retrieve the saved values.
		String textPersonID = SharedPreferences.getString("textPersonID", "");
		String textPhone = SharedPreferences.getString("textPhone", "");
		String textEmailAddress = SharedPreferences.getString("textEmailAddress", "");
		String stringtextDetails = SharedPreferences.getString("textDetails", "");
		textDetails.setText(textPersonName.getText().toString() + textPersonID + textPhone + textEmailAddress + stringtextDetails);
		}
		
  
	public boolean onKeyUp(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_MENU) {
	//       			           Intent settingsActivity = new Intent(getBaseContext(),
	//                                          Preferences.class);
	//                          startActivity(settingsActivity);
	    }
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
				 	finish(); 			 
	 	}   
	 return true;
	}
}
