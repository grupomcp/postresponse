package com.mcptecnologia.postresponse.domain;

import java.util.List;

public class Request {
	private String method;
	private Integer id;
	private List<? extends Object> params;
	
	public Request(String method, Integer id, List<? extends Object> params) {
		super();
		this.method = method;
		this.id = id;
		this.params = params;
	}
	
	public String getMethod() {
		return method;
	}
	
	public Integer getId() {
		return id;
	}
	
	public List<? extends Object> getParams() {
		return params;
	}
}
