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

public class ActivityMain extends Activity {
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
  		Intent ActivityTicketAdd = new Intent(getBaseContext(),ActivityTicketAdd.class);
  		startActivity(ActivityTicketAdd);	 		
    }
	
	public void buttonSearchClick(View view)  {
		try {
		String stringPersonName = editTextPersonName.getText().toString();
		// Get the stored preferences
		SharedPreferences SharedPreferences = getSharedPreferences(stringPersonName,Activity.MODE_PRIVATE);
		// Retrieve the saved values.
		String stringPersonID = SharedPreferences.getString("PersonID", "Not available !");
		String stringPhone = SharedPreferences.getString("Phone", "Not available !");
		String stringEmail = SharedPreferences.getString("Email", "Not available !");
		String stringDetails = SharedPreferences.getString("Details", "Not available !");
		
		int TicketCount = SharedPreferences.getInt("TicketCount", 0);
		String Temp = "";
        for (int i = 1; i <= TicketCount; i++) {
        	Temp = Temp + "\nTicket Number : " + SharedPreferences.getString(String.valueOf(i),"0");
        }
        textViewDetails.setText("Person Name : " + stringPersonName 
				+ "\nPerson ID : " + stringPersonID 
				+ "\nPhone : " + stringPhone 
				+ "\nEmail : " + stringEmail 
				+ "\nDetails : " + stringDetails
				+ "\nNumber of Tickets : " + TicketCount
				+ Temp
				+ "\n\nTicket Numbers details are as follows,"
				);
		}
		catch (Exception e) {
			//textViewMessages.setText("Error Occurred !" + e.getMessage());
		}
	}
		
  
	public boolean onKeyUp(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_MENU) {
//	    	Intent settingsActivity = new Intent(getBaseContext(),Preferences.class);
//	    	startActivity(settingsActivity);
	    }
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
				 	finish(); 			 
	 	}   
	 return true;
	}
}
