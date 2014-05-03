package com.pefhaw.planningtable;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PTDatabase{
	Context context = null;

	public PTDatabase(Context context) {
		this.context = context;
	}

	public String addPerson(String stringPersonName,String stringPersonID, String stringPhone, String stringEmail, String stringDetails) {
		try {
			// Get the stored preferences
			SharedPreferences SharedPreferences = this.context.getSharedPreferences("PersonNameList",Activity.MODE_PRIVATE);
			// Retrieve an editor to modify the shared preferences.
			SharedPreferences.Editor editor = SharedPreferences.edit();
			// Retrieve the saved values.
			int PersonCount = SharedPreferences.getInt("PersonCount",0);
			PersonCount++;
			// Store new primitive types in the shared preferences object.
			editor.putInt("PersonCount", PersonCount);
			editor.putString(String.valueOf(PersonCount), stringPersonName);
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
			editor.putInt("TicketCount", 0);
			// Commit the changes.
			editor.commit();

			// Get the stored preferences
			SharedPreferences = this.context.getSharedPreferences("Temp",Activity.MODE_PRIVATE);
			// Retrieve an editor to modify the shared preferences.
			editor = SharedPreferences.edit();
			// Store new primitive types in the shared preferences object.
			editor.putString("SelectedPersonName", stringPersonName);
			// Commit the changes.
			editor.commit();
		}
		catch (Exception e) {
			return "Error Occurred !" + e.getMessage();
		}
		return stringPersonName + "'s details saved successfully !";
	}

	public String addTicket(String stringPersonName,String stringSymptoms, String stringDiagnoses, String stringDetails) {
		try {
			// Get the stored preferences
			SharedPreferences SharedPreferences = this.context.getSharedPreferences("TicketCount",Activity.MODE_PRIVATE);
			// Retrieve an editor to modify the shared preferences.
			SharedPreferences.Editor editor = SharedPreferences.edit();
			// Retrieve the saved values.
			int TicketCount = SharedPreferences.getInt("TicketCount",0);
			TicketCount++;
			// Store new primitive types in the shared preferences object.
			editor.putInt("TicketCount", TicketCount);
			// Commit the changes.
			editor.commit();

			// Get the stored preferences
			SharedPreferences = this.context.getSharedPreferences(String.valueOf(TicketCount),Activity.MODE_PRIVATE);
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
			TicketCount = SharedPreferences.getInt("TicketCount",0);
			TicketCount++;
			// Store new primitive types in the shared preferences object.
			editor.putInt("TicketCount", TicketCount);
			editor.putString(String.valueOf(TicketCount), String.valueOf(TicketCount));
			// Commit the changes.
			editor.commit();
		}
		catch (Exception e) {
			return "Error Occurred ! " + e.getMessage();
		}
		return stringPersonName + "'s details saved successfuly !";
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
		return "Succesfull";
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

	public int getTotalTicketCount()
	{
		int TotalTicketCount = 0;
		try {
			// Get the stored preferences
			SharedPreferences SharedPreferences = this.context.getSharedPreferences("TicketCount",Activity.MODE_PRIVATE);
			// Retrieve the saved values.
			TotalTicketCount = SharedPreferences.getInt("TicketCount",0);
		}
		catch (Exception e) {
			return -1;//Error Occurred.
		}
		return TotalTicketCount;
	}
}
