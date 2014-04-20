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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		textMessages = (TextView)findViewById(R.id.textMessages);
		textPersonName = (TextView)findViewById(R.id.textPersonName);
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
		editor.putString("textPersonID", "123456789");
		editor.putString("textPhone", "123456789");
		editor.putString("textEmailAddress", "123456789@123456789");
		editor.putString("textDetails", "ABCDEFGHI");
		// Commit the changes.
		editor.commit();
		textMessages.setText("Saved successfuly !");
	}
}