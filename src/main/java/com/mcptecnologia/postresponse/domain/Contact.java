package com.mcptecnologia.postresponse.domain;

import java.util.List;


public class Contact {
	private String contactId;
	private String name;
	private String email;
	private List<Custom>customFieldValues;
	private Campaign campaign;
	
	public Contact(String contactId, String name, String email, List<Custom> customFieldValues, Campaign campaign) {
		this(name, email, customFieldValues, campaign);
		this.contactId = contactId;
	}
	
	public Contact(String name, String email, List<Custom> customFieldValues, Campaign campaign) {
		super();
		this.name = name;
		this.email = email;
		this.customFieldValues = customFieldValues;
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
	
	public List<Custom> getCustomFieldValues() {
		return customFieldValues;
	}
	
	public Campaign getCampaign() {
		return campaign;
	}
	
	@Override
	public String toString() {
		return getName() + " - " + getEmail();
	}
	
}
