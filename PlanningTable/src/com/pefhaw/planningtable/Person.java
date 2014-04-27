package com.pefhaw.planningtable;

//// not used at the program

public class Person {
	int PersonID;
	String PersonName;
	int Phone;
	String Email;
	String Details;

	public Person(String name, int id) {
		super();
		// TODO Auto-generated constructor stub
		this.PersonID=id;
		this.PersonName=name;	 
	}

	public void setName(String name)
	{
		this.PersonName=name;
	}

	public void setID(int id)
	{
		this.PersonID=id;
	}

	public String getName()
	{
		return this.PersonName;
	}

	public int getID()
	{
		return this.PersonID;
	}
}
