package com.pefhaw.planningtable;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	ListView listViewDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listViewDetails = (ListView)findViewById(R.id.listViewDetails);
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
		SharedPreferences SharedPreferences = getSharedPreferences("ak",mode); //getSharedPreferences(PersonName,mode);
		// Retrieve the saved values.
		String textPersonID = SharedPreferences.getString("textPersonID", "");
		String textPhone = SharedPreferences.getString("textPhone", "");
		String textEmailAddress = SharedPreferences.getString("textEmailAddress", "");
		String textDetails = SharedPreferences.getString("textDetails", "");

		String  Details[]={"Searched successfuly !",textPersonID, textPhone, textEmailAddress, textDetails};
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, Details);
		listViewDetails.setAdapter(adapter);
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
