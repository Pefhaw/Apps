package com.pefhaw.planningtable;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PTDatabase{
	Context context = null;
	private SharedPreferences mPrefs;
	private Editor editor;
	private ArrayList<PTS> PersonList;

public PTDatabase(Context context) {
		this.context = context;
}
	
	public String addPerson(String stringPersonName,String stringPersonID, String stringPhone, String stringEmail, String stringDetails) {
//		try {
/*			// Get the stored preferences
			SharedPreferences SharedPreferences = this.context.getSharedPreferences("PersonNameList",Activity.MODE_PRIVATE);
			// Retrieve an editor to modify the shared preferences.
			SharedPreferences.Editor editor = SharedPreferences.edit();
			// Retrieve the saved values.
			int TotalPersonCount = SharedPreferences.getInt("TotalPersonCount",0);
			TotalPersonCount++;
			// Store new primitive types in the shared preferences object.
			editor.putInt("TotalPersonCount", TotalPersonCount);
			editor.putString(String.valueOf(TotalPersonCount), stringPersonName);
			// Commit the changes.
			editor.commit();

			// Create or retrieve the shared preference object.
			SharedPreferences = this.context.getSharedPreferences(stringPersonName,Activity.MODE_PRIVATE);
			// Retrieve an editor to modify the shared preferences.
			editor = SharedPreferences.edit();
			// Store new primitive types in the shared preferences object.
			editor.putString("PersonID", stringPersonID);
			editor.putString("Phone", stringPhone);
			editor.putString("Email", stringEmail);
			editor.putString("Details", stringDetails);
			editor.putInt("PersonTicketCount", 0);
			// Commit the changes.
			editor.commit();

			setTemporaryVariable("SelectedPersonName", stringPersonName);
		}
			
		catch (Exception e) {
			return "Error Occurred ! " + e.getMessage();
		}
		*/
				mPrefs = this.context.getSharedPreferences("ObjectList",Activity.MODE_PRIVATE);
				editor = mPrefs.edit();

			
				int id=1;/// integer ID
				PTS myObject = new PTS();
	            myObject.setValues(
	            		id
	            		,stringPersonName
	            		,stringPersonID
	            		,stringEmail
	            		,stringDetails);
	            
            	PersonList = getObject();	
	        	
	             if(PersonList!= null){
	            	 PersonList.add(myObject);
	             }else{
	            	 PersonList = new ArrayList<PTS>();	
	            	 PersonList.add(myObject);
	             }
	         	
			if (saveObject(PersonList)){             
			return "Person '" + stringPersonName + "' is added !";
		}else{
			return "error occured";
		}
	}

	
	public boolean deletePerson(int position) {
	
			// this will delete the 'position' detail in the share preferences 
		return true;
	}

	public String addTicket(String stringPersonName,String stringSymptoms, String stringDiagnoses, String stringDetails) {
		try {
			// Get the stored preferences
			SharedPreferences SharedPreferences = this.context.getSharedPreferences("TotalTicketCount",Activity.MODE_PRIVATE);
			// Retrieve an editor to modify the shared preferences.
			SharedPreferences.Editor editor = SharedPreferences.edit();
			// Retrieve the saved values.
			int TotalTicketCount = SharedPreferences.getInt("TotalTicketCount",0);
			TotalTicketCount++;
			// Store new primitive types in the shared preferences object.
			editor.putInt("TotalTicketCount", TotalTicketCount);
			// Commit the changes.
			editor.commit();

			// Get the stored preferences
			SharedPreferences = this.context.getSharedPreferences(String.valueOf(TotalTicketCount),Activity.MODE_PRIVATE);
			// Retrieve an editor to modify the shared preferences.
			editor = SharedPreferences.edit();
			// Store new primitive types in the shared preferences object.
			editor.putString("PersonName", stringPersonName);
			editor.putString("Symptoms", stringSymptoms);
			editor.putString("Diagnoses", stringDiagnoses);
			editor.putString("Details", stringDetails);
			// Commit the changes.
			editor.commit();

			// Create or retrieve the shared preference object.
			SharedPreferences = this.context.getSharedPreferences(stringPersonName,Activity.MODE_PRIVATE);
			// Retrieve an editor to modify the shared preferences.
			editor = SharedPreferences.edit();
			// Retrieve the saved values.
			int PersonTicketCount = SharedPreferences.getInt("PersonTicketCount",0);
			PersonTicketCount++;
			// Store new primitive types in the shared preferences object.
			editor.putInt("PersonTicketCount", PersonTicketCount);
			editor.putString(String.valueOf(PersonTicketCount), String.valueOf(TotalTicketCount));
			// Commit the changes.
			editor.commit();
		}
		catch (Exception e) {
			return "Error Occurred ! " + e.getMessage();
		}
		return stringPersonName + "'s ticket is added !";
	}
	
	public String setTemporaryVariable(String stringTemporaryVariable, String stringTemporaryVariableValue) {
		try {
			// Get the stored preferences
			SharedPreferences SharedPreferences = this.context.getSharedPreferences("TemporaryVariable",Activity.MODE_PRIVATE);
			// Retrieve an editor to modify the shared preferences.
			SharedPreferences.Editor editor = SharedPreferences.edit();
			// Store new primitive types in the shared preferences object.
			editor.putString(stringTemporaryVariable, stringTemporaryVariableValue);
			// Commit the changes.
			editor.commit();
		}
		catch (Exception e) {
			return "Error Occurred ! " + e.getMessage();
		}
		return stringTemporaryVariable + "'s details saved successfuly !";
	}

	public String getTemporaryVariable(String stringTemporaryVariable) {
		String stringresult = "";
		try {
			// Get the stored preferences
			SharedPreferences SharedPreferences = this.context.getSharedPreferences("TemporaryVariable",Activity.MODE_PRIVATE);
			// Retrieve the saved values.
			stringresult = SharedPreferences.getString(stringTemporaryVariable,"");
		}
		catch (Exception e) {
			return "Error Occurred ! " + e.getMessage();
		}
		return stringresult;
	}

	public ArrayList<String> getPersonNameList()
	{
/*		ArrayList<String> stringPersonNameList;
			try {
			// Get the stored preferences
			SharedPreferences SharedPreferences = this.context.getSharedPreferences("PersonNameList",Activity.MODE_PRIVATE);
			// Retrieve the saved values.
			int TotalPersonCount = SharedPreferences.getInt("TotalPersonCount",0);
			stringPersonNameList = new ArrayList<String>();
			for (int i = 0; i < TotalPersonCount; i++) {
				stringPersonNameList.add(SharedPreferences.getString(String.valueOf(i+1),"Not available !"));
			}
		}
		catch (Exception e) {
			return new ArrayList<String>();//Error Occurred.
		}
		return stringPersonNameList;
*/
		
		ArrayList<String> stringPersonNameList;
		try {
		// Get the stored preferences
		mPrefs = this.context.getSharedPreferences("ObjectList",Activity.MODE_PRIVATE);
		// Retrieve the saved values.
		 ArrayList<PTS> mygetObject = getObject();
		 stringPersonNameList = new ArrayList<String>();
	     if(mygetObject != null){	    	 				/// now this can directly pass the object array list too
	    	 for (int i = 0; i <mygetObject.size() ; i++) {
	    		 stringPersonNameList.add(mygetObject.get(i).getValue());	
	    	 }
	     }
		}
	catch (Exception e) {
		return new ArrayList<String>();//Error Occurred.
	}
	return stringPersonNameList;


	}
	
	public int getPersonTicketCount(String stringPersonName)
	{
		int PersonTicketCount = 0;
		try {
			// Get the stored preferences
			SharedPreferences SharedPreferences = this.context.getSharedPreferences(stringPersonName,Activity.MODE_PRIVATE);
			// Retrieve the saved values.
			PersonTicketCount = SharedPreferences.getInt("PersonTicketCount",0);
		}
		catch (Exception e) {
			return -1;//Error Occurred.
		}
		return PersonTicketCount;
	}
	
	public int getTotalTicketCount()
	{
		int TotalTicketCount = 0;
		try {
			// Get the stored preferences
			SharedPreferences SharedPreferences = this.context.getSharedPreferences("TotalTicketCount",Activity.MODE_PRIVATE);
			// Retrieve the saved values.
			TotalTicketCount = SharedPreferences.getInt("TotalTicketCount",0);
		}
		catch (Exception e) {
			return -1;//Error Occurred.
		}
		return TotalTicketCount;
	}
	
	
    public ArrayList<PTS> getObject(){				//returns saved array list
    	Gson gson = new Gson();
        String json = mPrefs.getString("MyArray", "");
        Type listType = new TypeToken<ArrayList<PTS>>() {
        }.getType();
        ArrayList<PTS> obj = gson.fromJson(json, listType);
        return obj ;
    }
    
    public boolean saveObject(ArrayList<PTS> array){			/// save array lisy
    	 Gson gson = new Gson();
         String json = gson.toJson(array);
         editor.putString("MyArray", json);
         editor.commit();
         return true;
    }
	
}






