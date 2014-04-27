package com.pefhaw.planningtable;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ActivityTicketAdd extends Activity {
	TextView textViewMessages;
	EditText editTextPersonName;
	EditText editTextTicketNumber;
	EditText editTextSymptoms;
	EditText editTextDiagnoses;
	EditText editTextDetails;
	Button buttonSave;
	Button buttonAddPerson;
	Button buttonBack;

	// Called at the start of the active lifetime.
	@Override
	public void onResume(){
		// TODO Auto-generated method stub
		super.onResume();
		setContentView(R.layout.activity_ticket_add);
		textViewMessages = (TextView)findViewById(R.id.textViewMessages);
		editTextPersonName = (EditText)findViewById(R.id.editTextPersonName);
		editTextTicketNumber = (EditText)findViewById(R.id.editTextTicketNumber);
		editTextSymptoms = (EditText)findViewById(R.id.editTextSymptoms);
		editTextDiagnoses = (EditText)findViewById(R.id.editTextDiagnoses);
		editTextDetails = (EditText)findViewById(R.id.editTextDetails);
		buttonSave = (Button)findViewById(R.id.buttonSave);
		buttonAddPerson = (Button)findViewById(R.id.buttonAddPerson);
		buttonBack = (Button)findViewById(R.id.buttonBack);
		try {
			// Get the stored preferences
			SharedPreferences SharedPreferences = getSharedPreferences("TicketCount",Activity.MODE_PRIVATE);
			// Retrieve the saved values.
			int TicketCount = SharedPreferences.getInt("TicketCount",0);
			TicketCount++;
			editTextTicketNumber.setText(String.valueOf(TicketCount));
		}
		catch (Exception e) {
			textViewMessages.setText("Error Occurred !" + e.getMessage());
		}

		buttonSave.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
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

					String PersonName = editTextPersonName.getText().toString();

					// Create or retrieve the shared preference object.
					SharedPreferences = getSharedPreferences(PersonName,Activity.MODE_PRIVATE);
					// Retrieve an editor to modify the shared preferences.
					editor = SharedPreferences.edit();
					// Retrieve the saved values.
					TicketCount = SharedPreferences.getInt("TicketCount",0);
					TicketCount++;
					// Store new primitive types in the shared preferences object.
					editor.putInt("TicketCount", TicketCount);
					editor.putString(String.valueOf(TicketCount), editTextTicketNumber.getText().toString());
					// Commit the changes.
					editor.commit();

					textViewMessages.setText(PersonName + "'s details saved successfuly !");
				}
				catch (Exception e) {
					textViewMessages.setText("Error Occurred !" + e.getMessage());
				}
			}
		});

		buttonAddPerson.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent ActivityPersonAdd = new Intent(getBaseContext(),ActivityPersonAdd.class);
				startActivity(ActivityPersonAdd);
			}
		});

		buttonBack.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
