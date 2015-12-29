package com.mcptecnologia.postresponse.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.mcptecnologia.postresponse.domain.Campaign;
import com.mcptecnologia.postresponse.domain.Contact;
import com.mcptecnologia.postresponse.domain.Custom;
import com.mcptecnologia.postresponse.service.GetResponse;

public class GetResponseTest {
	private Contact contact;
	private GetResponse getResponse;
	private Logger logger = Logger.getLogger(GetResponseTest.class);
	
	@Before
	public void setUp(){
		List<Custom>customs = new ArrayList<Custom>();
		customs.add(new Custom("Mazf", new String[]{"X Corporation"}));
		contact = new Contact("ouTrY8", "TestPV", "juridico@sinjus.org.br", customs, new Campaign("VYh2j"));
		getResponse = new GetResponse();
	}

	@Test
	public void shouldAddContact(){
		getResponse.addContact(contact);
	}
	
	@Test
	public void shouldUpdateContact(){
		getResponse.updateContact(contact);
	}
	
	@Test
	public void shouldRemoveContact(){
		getResponse.removeContact("juridico@sinjus.org.br");
	}
	
	@Test
	public void shouldFindaContact(){
		List<Contact> contacts = getResponse.findContactWith("juridico@sinjus.org.br");
		if(contacts != null){
			for(Contact contact: contacts){
				logger.info(contact);
			}
		}
	}
}
