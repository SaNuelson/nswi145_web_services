package com.novelins;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contact {
	private int ID;
	private String fullname;
	private String email;
	
	public Contact() {
		this.ID = -1;
		this.fullname = "INVALID";
		this.email = "INVALID";
	}
	public Contact(int ID, String fullname, String email) {
		this.ID = ID;
		this.fullname = fullname;
		this.email = email;
	}
	
	@XmlElement(name="contactId")
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	@XmlElement(name="fullName")
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	@XmlElement(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
