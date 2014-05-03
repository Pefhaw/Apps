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

public class ActivityTicketAdd extends Activity {
	private PTDatabase aPTDatabase;
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

		aPTDatabase = new PTDatabase(this);

		editTextPersonName.setText(aPTDatabase.getTemporaryVariable("SelectedPersonName"));
		editTextTicketNumber.setText(String.valueOf(aPTDatabase.getTotalTicketCount()+1));

		buttonSave.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				textViewMessages.setText(aPTDatabase.addTicket(editTextPersonName.getText().toString(), editTextSymptoms.getText().toString(), editTextDiagnoses.getText().toString(), editTextDetails.getText().toString()));
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
