package com.pefhaw.planningtable;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends Activity {
	TextView textViewMessages;
	EditText editTextPersonName;
	EditText editTextPersonID;
	EditText editTextPhone;
	EditText editTextEmail;
	EditText editTextDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		textViewMessages = (TextView)findViewById(R.id.textViewMessages);
		editTextPersonName = (EditText)findViewById(R.id.editTextPersonName);
		editTextPersonID = (EditText)findViewById(R.id.editTextPersonID);
		editTextPhone = (EditText)findViewById(R.id.editTextPhone);
		editTextEmail = (EditText)findViewById(R.id.editTextEmail);
		editTextDetails = (EditText)findViewById(R.id.editTextDetails);
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
		String PersonName = editTextPersonName.getText().toString();
		// Create or retrieve the shared preference object.
		int mode = Activity.MODE_PRIVATE;
		SharedPreferences SharedPreferences = getSharedPreferences(PersonName,mode);
		// Retrieve an editor to modify the shared preferences.
		SharedPreferences.Editor editor = SharedPreferences.edit();
		// Store new primitive types in the shared preferences object.
		editor.putString("PersonID", editTextPersonID.getText().toString());
		editor.putString("Phone", editTextPhone.getText().toString());
		editor.putString("Email", editTextEmail.getText().toString());
		editor.putString("Details", editTextDetails.getText().toString());
		// Commit the changes.
		editor.commit();
		textViewMessages.setText(editTextPersonName.getText().toString() + "'s details saved successfuly !");
	}
	public void buttonBackClick(View view)  {
		setContentView(R.layout.activity_main);
	}
}