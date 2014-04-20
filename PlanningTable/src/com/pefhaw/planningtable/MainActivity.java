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
	EditText editTextPersonName;
	TextView textViewDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editTextPersonName = (EditText)findViewById(R.id.editTextPersonName);
		textViewDetails = (TextView)findViewById(R.id.editTextDetails);
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
		SharedPreferences SharedPreferences = getSharedPreferences(editTextPersonName.getText().toString(),mode);
		// Retrieve the saved values.
		String stringPersonID = SharedPreferences.getString("PersonID", "");
		String stringPhone = SharedPreferences.getString("Phone", "");
		String stringEmail = SharedPreferences.getString("Email", "");
		String stringDetails = SharedPreferences.getString("Details", "");
		textViewDetails.setText("Person Name : " + editTextPersonName.getText().toString() + "\nPerson ID : " + stringPersonID + "\nPhone : " + stringPhone + "\nEmail : " + stringEmail + "\nDetails : " + stringDetails);
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
