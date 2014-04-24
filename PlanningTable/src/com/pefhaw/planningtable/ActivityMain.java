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
	private String DATA[]={"Patient A", "Patient B"};/// to define array names
	private DataAdapter mAdapter;
    EditText editTextPersonName;
    TextView textViewDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mAdapter = new DataAdapter(NULL);
        setListAdapter(mAdapter);
    	
     
        mAdapter = new DataAdapter(DATA); // load initial array, by referring to database
        setListAdapter(mAdapter);
        
        editTextPersonName = (EditText)findViewById(R.id.editTextPersonName);
        textViewDetails = (TextView)findViewById(R.id.editTextDetails);
	
		   ListView list = getListView();
			list.setOnItemLongClickListener(new OnItemLongClickListener() {

				public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long clickid) {
		/// long click event/ to delete		 
						return true;
				}
			});
	
	}
	
	  protected void onListItemClick(ListView l, View v, int position, long id) {
	      	
	      	Intent ActivityTicketAdd = new Intent(getBaseContext(),ActivityTicketAdd.class);
	  		startActivityForResult(ActivityTicketAdd,position);	
	  }
	   public void onActivityResult(int requestCode, int resultCode, Intent data)
	    {
		
		  // update the data DATA[] here 
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
	
	public void buttonSearchClick(View view)  {
		try {
		String stringPersonName = editTextPersonName.getText().toString();
		// Get the stored preferences
		SharedPreferences SharedPreferences = getSharedPreferences(stringPersonName,Activity.MODE_PRIVATE);
		// Retrieve the saved values.
		String stringPersonID = SharedPreferences.getString("PersonID", "Not available !");
		String stringPhone = SharedPreferences.getString("Phone", "Not available !");
		String stringEmail = SharedPreferences.getString("Email", "Not available !");
		String stringDetails = SharedPreferences.getString("Details", "Not available !");

		int TicketCount = SharedPreferences.getInt("TicketCount", 0);
		String stringTemp = "";
        for (int i = 1; i <= TicketCount; i++) {
        	String stringTicketNumber = SharedPreferences.getString(String.valueOf(i),"0");
        	stringTemp = stringTemp + "\n\nTicket Number : " + stringTicketNumber;
			// Get the stored preferences
        	SharedPreferences SharedPreferencesTemp = getSharedPreferences(stringTicketNumber,Activity.MODE_PRIVATE);
			// Retrieve the saved values.
        	stringTemp = stringTemp + "\nPersonName : " + SharedPreferencesTemp.getString("PersonName", "Not available !");
        	stringTemp = stringTemp + "\nSymptoms : " + SharedPreferencesTemp.getString("Symptoms", "Not available !");
        	stringTemp = stringTemp + "\nDiagnoses : " + SharedPreferencesTemp.getString("Diagnoses", "Not available !");
        	stringTemp = stringTemp + "\nDetails : " + SharedPreferencesTemp.getString("Details", "Not available !");
        }
        textViewDetails.setText("Person Name : " + stringPersonName 
				+ "\nPerson ID : " + stringPersonID 
				+ "\nPhone : " + stringPhone 
				+ "\nEmail : " + stringEmail 
				+ "\nDetails : " + stringDetails
				+ "\nNumber of Tickets : " + TicketCount
				+ "\n\nTicket Numbers details are as follows,"
				+ stringTemp
				);
		}
		catch (Exception e) {
			//textViewMessages.setText("Error Occurred !" + e.getMessage());
		}
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

	        public View getView(int position, View convertView, ViewGroup parent) {
	        	View rowView = getLayoutInflater().inflate(R.layout.text_item, parent, false);
		        TextView textView = (TextView) rowView.findViewById(R.id.label);
		        TextView subtextView = (TextView) rowView.findViewById(R.id.subtext);        
		        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		        textView.setText(getItem(position));
			    	imageView.setImageResource(R.drawable.ic_launcher);
					textView.setText("patinet x");
				    
			    subtextView.setText("detail x");
			    return rowView;	
			}
	        	
	      }
	   
	    
	    
}
