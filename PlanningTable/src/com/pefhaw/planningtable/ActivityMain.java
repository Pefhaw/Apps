package com.pefhaw.planningtable;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityMain extends ListActivity {
	private PTDatabase aPTDatabase;
	private DataAdapter mAdapter;
	EditText editTextSearchText;
	Button buttonAddNew;
	private int recordposition;

    @Override
    public void onCreate(Bundle savedInstanceState) {			/// on create used for initiation activities 
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
		editTextSearchText = (EditText) findViewById(R.id.editTextSearchText);
		buttonAddNew = (Button)findViewById(R.id.buttonAddNew);
		aPTDatabase = new PTDatabase(this);
		ListView listViewDetailList = getListView();
		listViewDetailList.setTextFilterEnabled(true);
		
		
		buttonAddNew.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent ActivityPersonAdd = new Intent(getBaseContext(),ActivityPersonAdd.class);
				startActivity(ActivityPersonAdd);
			}
		});
		
		editTextSearchText.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence charSequenceSearchText, int start, int before, int count) {
				mAdapter.getFilter().filter(charSequenceSearchText.toString());
			}

			public void beforeTextChanged(CharSequence charSequenceSearchText, int start, int count,	int after) {
			}

			public void afterTextChanged(Editable theWatchedText) {
				mAdapter.getFilter().filter(theWatchedText);
			}
		});
		
		listViewDetailList.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long clickid) {				

				// Add Delete message, and delete an entry		
				recordposition=position;
	
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		  	    public void onClick(DialogInterface dialog, int which) {
			        switch (which){
			        case DialogInterface.BUTTON_POSITIVE:
			        {
			            //Yes button clicked
			        	aPTDatabase.deletePerson(recordposition);
						
						//setResult(RESULT_CANCELED,AddIntent);
			        }
			            break;

			        case DialogInterface.BUTTON_NEGATIVE:
			            //No button clicked
			            break;
			        default: 
			            return;	// Return true to consume the click event. In this case the
						// onListItemClick listener is not called anymore.
			        }
			    }
			};
			
		    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMain.this);
			builder.setMessage(getString(R.string.msg1)).setPositiveButton(getString(R.string.yes), dialogClickListener)
			    .setNegativeButton(getString(R.string.no), dialogClickListener).show();		//"Delete this reminder?"		
				return true;
				
			}
		});
	   }
	
	
	@Override
	public void onResume(){			/// refresh information up on return to the activity
		super.onResume();   
		aPTDatabase.setTemporaryVariable("SelectedPersonName", ""); // Reset previously clicked PersonName of List View.
		mAdapter = new DataAdapter(this.getBaseContext(),aPTDatabase.getPersonNameList()); // Load initial array by referring to database
		setListAdapter(mAdapter);
	
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		aPTDatabase.setTemporaryVariable("SelectedPersonName", mAdapter.getItem(position));
		Intent ActivityPersonAdd = new Intent(getBaseContext(),ActivityPersonAdd.class);
		startActivityForResult(ActivityPersonAdd,position);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		//Update changed Data event here up on the close of the add/edit screen // handled at onresume
		//if (requestCode==endofdata){
    		if (resultCode==RESULT_OK){
    			Toast.makeText(this, data.getExtras().getString("person")+ "added", Toast.LENGTH_LONG).show();
    			
    		}
    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// keep a common menu for all activities
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
		private ArrayList<String> mDisplayedValues;// Values to be displayed
		public DataAdapter(Context context, ArrayList<String> arrayList) {
			this.mOriginalValues = arrayList;
			this.mDisplayedValues = arrayList;
			LayoutInflater.from(context);
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
						constraint = constraint.toString().toLowerCase();		///check localization issue
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

		public View getView(int position, View convertView, ViewGroup parent) {

			View rowView = getLayoutInflater().inflate(R.layout.text_item, parent, false);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
			TextView textView = (TextView) rowView.findViewById(R.id.label);
			TextView subtextView = (TextView) rowView.findViewById(R.id.subtext);        

			imageView.setImageResource(R.drawable.ic_launcher);
			textView.setText("Patient : " + getItem(position));	
			subtextView.setText("Ticket Count : " + String.valueOf(aPTDatabase.getPersonTicketCount(getItem(position))));
			return rowView;
		}
	}
}