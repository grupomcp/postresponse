package com.mcptecnologia.postresponse.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mcptecnologia.postresponse.domain.Campaign;
import com.mcptecnologia.postresponse.domain.Contact;
import com.mcptecnologia.postresponse.domain.Custom;
import com.mcptecnologia.postresponse.service.GetResponse;

public class GetResponseTest {
	private Contact contact;
	private GetResponse getResponse;
	
	@Before
	public void setUp(){
		List<Custom>customs = new ArrayList<Custom>();
		customs.add(new Custom("Mazf", new String[]{"X Corporation"}));
		contact = new Contact("TestPV", "paulo@xxx.com", customs, new Campaign("VYh2j"));
		getResponse = new GetResponse();
	}

	@Test
	public void shouldAddContact(){
		getResponse.addContact(contact);
	}
}
