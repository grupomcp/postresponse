package com.mcptecnologia.postresponse.domain;

public class Custom {
	private String customFieldId;
	private String value[];

	public Custom(String customFieldId, String value[]) {
		super();
		this.customFieldId = customFieldId;
		this.value = value;
	}
	
	public String getCustomFieldId() {
		return customFieldId;
	}
	
	public String[] getValue() {
		return value;
	}
}
