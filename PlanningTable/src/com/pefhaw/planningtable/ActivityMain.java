package com.pefhaw.planningtable;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityMain extends ListActivity {

	private String NULL[] = {};
	private String stringPersonNameList[];// Define array names
	private DataAdapter mAdapter;
	EditText editTextPersonName;
	TextView textViewDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editTextPersonName = (EditText)findViewById(R.id.editTextPersonName);
		textViewDetails = (TextView)findViewById(R.id.editTextDetails);

		mAdapter = new DataAdapter(NULL);
		setListAdapter(mAdapter);

		try {
			// Get the stored preferences
			SharedPreferences SharedPreferences = getSharedPreferences("PersonNameList",Activity.MODE_PRIVATE);
			// Retrieve the saved values.
			int PersonCount = SharedPreferences.getInt("PersonCount",0);
			stringPersonNameList = new String[PersonCount];
			for (int i = 0; i < PersonCount; i++) {
				stringPersonNameList[i] = SharedPreferences.getString(String.valueOf(i+1),"Not available !");
			}
		}
		catch (Exception e) {
			textViewDetails.append("Error Occurred !/n" + e.getMessage());
		}

		mAdapter = new DataAdapter(stringPersonNameList); // Load initial array by referring PersonNameList to database
		setListAdapter(mAdapter);

		ListView list = getListView();
		list.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long clickid) {
				// long click event/ to delete
				return true;}
		});
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {

		Intent ActivityTicketAdd = new Intent(getBaseContext(),ActivityTicketAdd.class);
		startActivityForResult(ActivityTicketAdd,position);
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		//update the data DATA[] here 
		//changeData(String[] data)
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// keep a common menu for all activities
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void buttonAddNewClick(View view)  {
		Intent ActivityTicketAdd = new Intent(getBaseContext(),ActivityTicketAdd.class);
		startActivity(ActivityTicketAdd);	 		
	}

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			// Intent settingsActivity = new Intent(getBaseContext(), Preferences.class);
			// startActivity(settingsActivity);
		}
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return true;
	}

	private class DataAdapter extends BaseAdapter {
		private String[] mData;

		public DataAdapter(String[] data) {
			mData = data;
		}

		public void changeData(String[] data) {
			mData = data;
			notifyDataSetChanged();
		}

		public int getCount() {
			return mData.length;
		}

		public String getItem(int position) {
			return mData[position];
		}

		public long getItemId(int position) {
			return position;
		}

		public String getTicketCount(int position) {
			// Get the stored preferences
			SharedPreferences SharedPreferences = getSharedPreferences(getItem(position),Activity.MODE_PRIVATE);
			// Retrieve the saved values.
			return String.valueOf(SharedPreferences.getInt("TicketCount", 0));
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			View rowView = getLayoutInflater().inflate(R.layout.text_item, parent, false);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
			TextView textView = (TextView) rowView.findViewById(R.id.label);
			TextView subtextView = (TextView) rowView.findViewById(R.id.subtext);        

			imageView.setImageResource(R.drawable.ic_launcher);    
			textView.setText("Patient : " + getItem(position));		           
			subtextView.setText("Ticket Count : " + getTicketCount(position));
			return rowView;
		}
	}
}