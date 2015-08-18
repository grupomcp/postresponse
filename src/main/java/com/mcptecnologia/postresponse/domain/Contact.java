package com.mcptecnologia.postresponse.domain;

import java.util.List;


public class Contact {
	private String contactId;
	private String name;
	private String email;
	private List<Company>customs;
	private Campaign campaign;
	
	public Contact(String contactId, String name, String email, List<Company> customs, Campaign campaign) {
		this(name, email, customs, campaign);
		this.contactId = contactId;
	}
	
	public Contact(String name, String email, List<Company> customs, Campaign campaign) {
		super();
		this.name = name;
		this.email = email;
		this.customs = customs;
		this.campaign = campaign;
	} 
	
	public String getContactId() {
		return contactId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public List<Company> getCustoms() {
		return customs;
	}
	
	public Campaign getCampaign() {
		return campaign;
	}
	
	@Override
	public String toString() {
		return getName() + " - " + getEmail();
	}
	
}
