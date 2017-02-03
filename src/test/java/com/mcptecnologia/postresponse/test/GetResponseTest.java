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
	private Contact oldContact;
	private Contact newContact;
	private GetResponse getResponse;
	private Logger logger = Logger.getLogger(GetResponseTest.class);
	
	@Before
	public void setUp(){
		List<Custom>customs = new ArrayList<Custom>();
		customs.add(new Custom("Mazf", new String[]{"X Corporation"}));
		oldContact = new Contact("ouTrY8", "TestPV", "juridico@sinjus.org.br", customs, new Campaign("VYh2j"), 0);
		newContact = new Contact("testefinal", "atendimento@castorweb.com.br", customs, new Campaign("paOMC"), 0);
		getResponse = new GetResponse();
	}

	@Test
	public void shouldAddContact(){
		getResponse.addContact(newContact);
	}
	
	@Test
	public void shouldUpdateContact(){
		newContact.setContactId("LzxWni");
		getResponse.updateContact(newContact);
	}
	
	@Test
	public void shouldRemoveContact(){
		getResponse.removeContact("foxpv213@gmail.com");
	}
	
	@Test
	public void shouldMoveCampaign(){
		getResponse.moveContactToNewCampaign(newContact.getEmail(), "p8wVM");
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
