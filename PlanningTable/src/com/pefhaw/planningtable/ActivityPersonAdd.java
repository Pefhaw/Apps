package com.pefhaw.planningtable;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityPersonAdd extends Activity {
	TextView textViewMessages;
	EditText editTextPersonName;
	EditText editTextPersonID;
	EditText editTextPhone;
	EditText editTextEmail;
	EditText editTextDetails;
	Button buttonSave;
	Button buttonBack;

	// Called at the start of the active lifetime.
	@Override
	public void onResume(){
		// TODO Auto-generated method stub
		super.onResume();
		setContentView(R.layout.activity_person_add);
		textViewMessages = (TextView)findViewById(R.id.textViewMessages);
		editTextPersonName = (EditText)findViewById(R.id.editTextPersonName);
		editTextPersonID = (EditText)findViewById(R.id.editTextPersonID);
		editTextPhone = (EditText)findViewById(R.id.editTextPhone);
		editTextEmail = (EditText)findViewById(R.id.editTextEmail);
		editTextDetails = (EditText)findViewById(R.id.editTextDetails);
		buttonSave = (Button)findViewById(R.id.buttonSave);
		buttonBack = (Button)findViewById(R.id.buttonBack);

		buttonSave.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				try {
					// Get the stored preferences
					SharedPreferences SharedPreferences = getSharedPreferences("PersonNameList",Activity.MODE_PRIVATE);
					// Retrieve an editor to modify the shared preferences.
					SharedPreferences.Editor editor = SharedPreferences.edit();
					// Retrieve the saved values.
					int PersonCount = SharedPreferences.getInt("PersonCount",0);
					PersonCount++;
					// Store new primitive types in the shared preferences object.
					editor.putInt("PersonCount", PersonCount);
					editor.putString(String.valueOf(PersonCount), editTextPersonName.getText().toString());
					// Commit the changes.
					editor.commit();

					String PersonName = editTextPersonName.getText().toString();

					// Create or retrieve the shared preference object.
					SharedPreferences = getSharedPreferences(PersonName,Activity.MODE_PRIVATE);
					// Retrieve an editor to modify the shared preferences.
					editor = SharedPreferences.edit();
					// Store new primitive types in the shared preferences object.
					editor.putString("PersonID", editTextPersonID.getText().toString());
					editor.putString("Phone", editTextPhone.getText().toString());
					editor.putString("Email", editTextEmail.getText().toString());
					editor.putString("Details", editTextDetails.getText().toString());
					editor.putInt("TicketCount", 0);
					// Commit the changes.
					editor.commit();
					textViewMessages.setText(PersonName + "'s details saved successfuly !");
				}
				catch (Exception e) {
					textViewMessages.setText("Error Occurred !" + e.getMessage());
				}
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

