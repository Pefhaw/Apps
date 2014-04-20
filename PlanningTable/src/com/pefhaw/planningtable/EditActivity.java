package com.pefhaw.planningtable;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class EditActivity extends Activity {
	TextView textMessages;
	TextView textPersonName;
	TextView textPersonID;
	TextView textPhone;
	TextView textEmailAddress;
	TextView textDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		textMessages = (TextView)findViewById(R.id.textMessages);
		textPersonName = (TextView)findViewById(R.id.textPersonName);
		textPersonID = (TextView)findViewById(R.id.textPersonID);
		textPhone = (TextView)findViewById(R.id.textPhone);
		textEmailAddress = (TextView)findViewById(R.id.textEmailAddress);
		textDetails = (TextView)findViewById(R.id.textDetails);
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public boolean onKeyUp(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish(); 			 
	    }   
	    return true;
	}
	public void buttonSaveClick(View view)  {
		String PersonName = textPersonName.getText().toString();
		// Create or retrieve the shared preference object.
		int mode = Activity.MODE_PRIVATE;
		SharedPreferences SharedPreferences = getSharedPreferences(PersonName,mode);
		// Retrieve an editor to modify the shared preferences.
		SharedPreferences.Editor editor = SharedPreferences.edit();
		// Store new primitive types in the shared preferences object.
		editor.putString("textPersonID", textPersonID.getText().toString());
		editor.putString("textPhone", textPhone.getText().toString());
		editor.putString("textEmailAddress", textEmailAddress.getText().toString());
		editor.putString("textDetails", textDetails.getText().toString());
		// Commit the changes.
		editor.commit();
		textMessages.setText(textPersonName.getText().toString() + "'S Details Saved successfuly !");
	}
	public void buttonBackClick(View view)  {
		setContentView(R.layout.activity_main);
	}
}