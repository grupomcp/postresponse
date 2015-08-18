package com.mcptecnologia.postresponse.domain;

public class Campaign {
	private String campaignId;

	public Campaign(String campaignId) {
		super();
		this.campaignId = campaignId;
	}
	
	public String getCampaignId() {
		return campaignId;
	}
	
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}
}
