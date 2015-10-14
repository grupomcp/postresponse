package com.mcptecnologia.postresponse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mcptecnologia.postresponse.common.ResponseErrorHandler;
import com.mcptecnologia.postresponse.domain.Contact;

@Component
public class GetResponse {
	private Logger logger = Logger.getLogger(GetResponse.class);
	
	private Gson gson = new Gson();
	private RestTemplate template;
	
	public GetResponse(){
		template = new RestTemplate();
		template.setErrorHandler(new ResponseErrorHandler());
	}
	
	public void addContact(Contact contact){
		List<Object>contatos = new ArrayList<Object>();
		contatos.add(ResourceBundle.getBundle("config").getString("getresponse.key"));
		contatos.add(contact);
		
		String jsonContato = gson.toJson(contact);
		logger.debug(jsonContato);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Auth-Token", "api-key " + ResourceBundle.getBundle("config").getString("getresponse.key"));
		headers.set("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(jsonContato, headers);
		HttpEntity<String> retornoHttp = template.exchange("http://api.getresponse.com/v3/contacts", HttpMethod.POST, entity, String.class);
		String retorno = retornoHttp.getBody();
		logger.debug(retorno);
		
	}
	
	public void updateContact(Contact contact){
		List<Object>contatos = new ArrayList<Object>();
		contatos.add(ResourceBundle.getBundle("config").getString("getresponse.key"));
		contatos.add(contact);
		
		String jsonContato = gson.toJson(contact);
		logger.debug(jsonContato);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Auth-Token", "api-key " + ResourceBundle.getBundle("config").getString("getresponse.key"));
		headers.set("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(jsonContato, headers);
		HttpEntity<String> retornoHttp = template.exchange("http://api.getresponse.com/v3/contacts/" + contact.getContactId(), HttpMethod.POST, entity, String.class);
		String retorno = retornoHttp.getBody();
		logger.debug(retorno);
	}
	
	public void moveContactToNewCampaign(String contactMail, String campaignId){
		List<Contact> contacts = this.findContactWith(contactMail);
		
		if(contacts != null && contacts.size() > 0){
			for(Contact contact: contacts){
				this.removeContact(contact.getEmail());
				contact.getCampaign().setCampaignId(campaignId);
				this.addContact(contact);
			}
		}
		logger.debug("contact has been moved to another campaign");
		
	}
	
	public void removeContact(String contactEmail){
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Auth-Token", "api-key " + ResourceBundle.getBundle("config").getString("getresponse.key"));
		headers.set("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		List<Contact> contacts = this.findContactWith(contactEmail);
		
		if(contacts != null && contacts.size() > 0){
			for(Contact contato: contacts){
				HttpEntity<String>retornoHttp = template.exchange("http://api.getresponse.com/v3/contacts/" + contato.getContactId(), HttpMethod.DELETE, entity, String.class);
				String retorno = retornoHttp.getBody();
				logger.debug(retorno);
			}
		}
	}
	
	public List<Contact>findContactWith(String email){
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Auth-Token", "api-key 9c7e475fff2827d50be1c25df126fd7d");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		HttpEntity<String> retornoHttp = template.exchange("http://api.getresponse.com/v3/contacts?query[email]=" + email, HttpMethod.GET, entity, String.class);
		String retorno = retornoHttp.getBody();
		logger.debug(retorno);
		List<Contact> contacts = gson.fromJson(retorno, new TypeToken<List<Contact>>(){}.getType());
		
		return contacts;
	}
	
}
