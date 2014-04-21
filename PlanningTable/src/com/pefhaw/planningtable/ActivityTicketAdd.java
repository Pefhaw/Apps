package com.pefhaw.planningtable;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class ActivityTicketAdd extends Activity {
	TextView textViewMessages;
	EditText editTextPersonName;
	EditText editTextTicketNumber;
	EditText editTextSymptoms;
	EditText editTextDiagnoses;
	EditText editTextDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ticket_add);
		textViewMessages = (TextView)findViewById(R.id.textViewMessages);
		editTextPersonName = (EditText)findViewById(R.id.editTextPersonName);
		editTextTicketNumber = (EditText)findViewById(R.id.editTextTicketNumber);
		editTextSymptoms = (EditText)findViewById(R.id.editTextSymptoms);
		editTextDiagnoses = (EditText)findViewById(R.id.editTextDiagnoses);
		editTextDetails = (EditText)findViewById(R.id.editTextDetails);
		try {
			// Get the stored preferences
			SharedPreferences SharedPreferences = getSharedPreferences("TicketCount",Activity.MODE_PRIVATE);
			// Retrieve the saved values.
			int TicketCount = SharedPreferences.getInt("TicketCount",0);
			editTextTicketNumber.setText(String.valueOf(TicketCount++));
		}
		catch (Exception e) {
			textViewMessages.setText("Error Occurred !" + e.getMessage());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_ticket_add, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void buttonSaveClick(View view)  {
		try {
			// Get the stored preferences
			SharedPreferences SharedPreferences = getSharedPreferences("TicketCount",Activity.MODE_PRIVATE);
			// Retrieve an editor to modify the shared preferences.
			SharedPreferences.Editor editor = SharedPreferences.edit();
			// Retrieve the saved values.
			int TicketCount = SharedPreferences.getInt("TicketCount",0);
			TicketCount++;
			// Store new primitive types in the shared preferences object.
			editor.putInt("TicketCount", TicketCount);
			// Commit the changes.
			editor.commit();
			
			editTextTicketNumber.setText(String.valueOf(TicketCount));
			
			// Get the stored preferences
			SharedPreferences = getSharedPreferences(String.valueOf(TicketCount),Activity.MODE_PRIVATE);
			// Retrieve an editor to modify the shared preferences.
			editor = SharedPreferences.edit();
			// Store new primitive types in the shared preferences object.
			editor.putString("PersonName", editTextPersonName.getText().toString());
			editor.putString("Symptoms", editTextSymptoms.getText().toString());
			editor.putString("Diagnoses", editTextDiagnoses.getText().toString());
			editor.putString("Details", editTextDetails.getText().toString());
			// Commit the changes.
			editor.commit();
			textViewMessages.setText(editTextPersonName.getText().toString() + "'s details saved successfuly !");
		}
		catch (Exception e) {
			textViewMessages.setText("Error Occurred !" + e.getMessage());
		}
	}
		
	public void buttonAddPersonClick(View view)  {
  		Intent ActivityPersonAdd = new Intent(getBaseContext(),ActivityPersonAdd.class);
  		startActivity(ActivityPersonAdd);
	}
		
	public void buttonBackClick(View view)  {
		finish();
	}
}
