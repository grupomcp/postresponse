package com.mcptecnologia.postresponse.domain;

import java.util.List;


public class Contact {
	private String contactId;
	private String name;
	private String email;
	private Integer dayOfCycle;
	private List<Custom>customFieldValues;
	private Campaign campaign;
	
	public Contact(String contactId, String name, String email, List<Custom> customFieldValues, Campaign campaign, Integer dayOfCycle) {
		this(name, email, customFieldValues, campaign, dayOfCycle);
		this.contactId = contactId;
	}
	
	public Contact(String name, String email, List<Custom> customFieldValues, Campaign campaign, Integer dayOfCycle) {
		super();
		this.name = name;
		this.email = email;
		this.customFieldValues = customFieldValues;
		this.campaign = campaign;
		this.dayOfCycle = dayOfCycle;
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
	
	public void setCustomFieldValues(List<Custom> customFieldValues) {
		this.customFieldValues = customFieldValues;
	}
	
	public Campaign getCampaign() {
		return campaign;
	}
	
	public Integer getDayOfCycle() {
		return dayOfCycle;
	}
	
	@Override
	public String toString() {
		return getName() + " - " + getEmail();
	}
	
}
