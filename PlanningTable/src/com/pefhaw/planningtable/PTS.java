package com.pefhaw.planningtable;

public class PTS {
	   private String ID, Name, email, detail;
	   int id;
	   
	   
	   public String setValues(int id,String Name, String ID, String email, String detail) { 
		  
		  this.id=id;
		  this.Name = Name;
		  this.ID = ID;
		  this.email = email;
		  this.detail = detail;
		 
		   
	        return this.Name;
	        
	    }

	    public String getValue() { 
	        return Name;
	    }

}
