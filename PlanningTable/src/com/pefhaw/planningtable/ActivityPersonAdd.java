package com.pefhaw.planningtable;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityPersonAdd extends Activity {
	private PTDatabase aPTDatabase;
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
		
		aPTDatabase = new PTDatabase(this);
		
		buttonSave.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				
				Intent ReturnIntent = new Intent();
				ReturnIntent.putExtra("name",aPTDatabase.addPerson(
						editTextPersonName.getText().toString(), 
						editTextPersonID.getText().toString(), // handle integer pass exception
						editTextPhone.getText().toString(), 
						editTextEmail.getText().toString(), 
						editTextEmail.getText().toString()));				
        		setResult(RESULT_OK,ReturnIntent);
				finish();
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