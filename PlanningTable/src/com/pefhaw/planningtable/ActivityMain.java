package com.pefhaw.planningtable;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityMain extends ListActivity {

//	private String NULL[] = {};
	private ArrayList<String> stringPersonNameList; 		// May use 'Person' array instead of 'String' 
	;// Define array names
	private DataAdapter mAdapter;
	EditText txtsearch;
	TextView textViewDetails;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		editTextPersonName = (EditText)findViewById(R.id.editTextPersonName);
//		textViewDetails = (TextView)findViewById(R.id.editTextDetails);
		
		txtsearch = (EditText) findViewById(R.id.txtsearch);
		ListView list = getListView();
		
//		mAdapter = new DataAdapter(NULL);
//		setListAdapter(mAdapter);
		list.setTextFilterEnabled(true);
		
		list.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long clickid) {
				// long click event/ to delete
				return true;}
		});

		txtsearch.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                    int count) {
              	mAdapter.getFilter().filter(s.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
            }

            public void afterTextChanged(Editable theWatchedText) {
            	mAdapter.getFilter().filter(theWatchedText);

            }
        });
		
	    try {
			// Get the stored preferences
			SharedPreferences SharedPreferences = getSharedPreferences("PersonNameList",Activity.MODE_PRIVATE);
			// Retrieve the saved values.
			int PersonCount = SharedPreferences.getInt("PersonCount",0);
			stringPersonNameList = new ArrayList<String>();
			for (int i = 0; i < PersonCount; i++) {
				stringPersonNameList.add(SharedPreferences.getString(String.valueOf(i+1),"Not available !"));
		
			}
		}
		catch (Exception e) {
			textViewDetails.append("Error Occurred !/n" + e.getMessage());
		}
	    mAdapter = new DataAdapter(this.getBaseContext(),stringPersonNameList); // Load initial array by referring PersonNameList to database
		setListAdapter(mAdapter);
		
	}
		@Override
		protected void onResume() {
		    // TODO Auto-generated method stub
		    super.onResume();
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

	
	private class DataAdapter extends BaseAdapter implements Filterable {
		
		private ArrayList<String> mOriginalValues; // Original Values
		private ArrayList<String> mDisplayedValues;    // Values to be displayed
		LayoutInflater inflater;

		public DataAdapter(Context context, ArrayList<String> stringNameList) {
		    this.mOriginalValues = stringNameList;
		    this.mDisplayedValues = stringNameList;
		    inflater = LayoutInflater.from(context);
		}

			public Filter getFilter() {
			Filter filter = new Filter() {

		 
				@SuppressWarnings("unchecked")
				@Override
		        protected void publishResults(CharSequence constraint,FilterResults results) {

		            mDisplayedValues = (ArrayList<String>) results.values; // has the filtered values
		            notifyDataSetChanged();  // notifies the data with new filtered values
		        }

		        @Override
		        protected FilterResults performFiltering(CharSequence constraint) {
		            FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
		            ArrayList<String> FilteredArrList = new ArrayList<String>();

		            if (mOriginalValues == null) {
		                mOriginalValues = new ArrayList<String>(mDisplayedValues); // saves the original data in mOriginalValues
		            }

		            /********
		             * 
		             *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
		             *  else does the Filtering and returns FilteredArrList(Filtered)  
		             *
		             ********/
		            if (constraint == null || constraint.length() == 0) {
		                // set the Original result to return  
		                results.count = mOriginalValues.size();
		                results.values = mOriginalValues;
		            } else {
		                constraint = constraint.toString().toLowerCase();
		                for (int i = 0; i < mOriginalValues.size(); i++) {
		                    String data = mOriginalValues.get(i);
		                    if (data.toLowerCase().startsWith(constraint.toString())) {
		                        FilteredArrList.add(mOriginalValues.get(i));
		                    }
		                }
		                // set the Filtered result to return
		                results.count = FilteredArrList.size();
		                results.values = FilteredArrList;
		            }
		            return results;
		        }

				public boolean onLoadClass(Class arg0) {
					// TODO Auto-generated method stub
					return false;
				}
		    };
		    return filter;
		}

		@Override
		public int getCount() {
		    return mDisplayedValues.size();
		}

		@Override
		public String getItem(int position) {
		    return mDisplayedValues.get(position);
		}

		@Override
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
