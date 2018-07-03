package com.afreeca.course.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User 
{
	@Id
	private String _id;
	private String firstname;
	private String lastname;
	
	public User() {}
	
	public User(String fn, String ln)
	{
		this.firstname = fn;
		this.lastname = ln;
	}

	public User(String _id, String firstname, String lastname) 
	{
		this._id = _id;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}
}
