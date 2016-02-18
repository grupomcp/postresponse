package com.mcptecnologia.postresponse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.validator.routines.EmailValidator;
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
	private Boolean enabledPostresponse;
	private String apiURL;
	
	public GetResponse(){
		template = new RestTemplate();
		template.setErrorHandler(new ResponseErrorHandler());
		enabledPostresponse = Boolean.valueOf(ResourceBundle.getBundle("config").getString("postresponse.enabled"));
		apiURL = "https://api.getresponse.com/v3/";
	}
	
	public void addContact(Contact contact){
		if(enabledPostresponse){
			List<Object>contatos = new ArrayList<Object>();
			contatos.add(ResourceBundle.getBundle("config").getString("getresponse.key"));
			contatos.add(contact);
			
			String jsonContato = gson.toJson(contact);
			logger.debug(jsonContato);
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("X-Auth-Token", "api-key " + ResourceBundle.getBundle("config").getString("getresponse.key"));
			headers.set("Content-Type", "application/json");
			HttpEntity<String> entity = new HttpEntity<String>(jsonContato, headers);
			HttpEntity<String> retornoHttp = template.exchange(apiURL + "contacts", HttpMethod.POST, entity, String.class);
			String retorno = retornoHttp.getBody();
			logger.debug(retorno);
		}else{
			logger.info("POSTRESPONSE IS DISABLED");
		}
		
	}
	
	public void updateContact(Contact contact){
		if(enabledPostresponse){
			String jsonContato = gson.toJson(contact);
			logger.debug(jsonContato);
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("X-Auth-Token", "api-key " + ResourceBundle.getBundle("config").getString("getresponse.key"));
			headers.set("Content-Type", "application/json");
			HttpEntity<String> entity = new HttpEntity<String>(jsonContato, headers);
			HttpEntity<String> retornoHttp = template.exchange(apiURL + "contacts/" + contact.getContactId(), HttpMethod.POST, entity, String.class);
			String retorno = retornoHttp.getBody();
			logger.debug(retorno);
		}else{
			logger.info("POSTRESPONSE IS DISABLED");
		}
	}
	
	public void moveContactToNewCampaign(String contactMail, String campaignId){
		if(enabledPostresponse){
			List<Contact> contacts = this.findContactWith(contactMail);
			
			if(contacts != null && contacts.size() > 0){
				for(Contact contact: contacts){
					this.removeContact(contact.getEmail());
					contact.getCampaign().setCampaignId(campaignId);
					this.addContact(contact);
				}
			}
			logger.debug("contact has been moved to another campaign");
		}else{
			logger.info("POSTRESPONSE IS DISABLED");
		}
		
	}
	
	public void removeContact(String contactEmail){
		if(enabledPostresponse){
			HttpHeaders headers = new HttpHeaders();
			headers.set("X-Auth-Token", "api-key " + ResourceBundle.getBundle("config").getString("getresponse.key"));
			headers.set("Content-Type", "application/json");
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			
			List<Contact> contacts = this.findContactWith(contactEmail);
			
			if(contacts != null && contacts.size() > 0){
				for(Contact contato: contacts){
					HttpEntity<String>retornoHttp = template.exchange(apiURL + "contacts/" + contato.getContactId(), HttpMethod.DELETE, entity, String.class);
					String retorno = retornoHttp.getBody();
					logger.debug(retorno);
				}
			}
		}else{
			logger.info("POSTRESPONSE IS DISABLED");
		}
	}
	
	public List<Contact>findContactWith(String email){
		List<Contact> contacts = new ArrayList<Contact>();
		if(enabledPostresponse){
			if(EmailValidator.getInstance().isValid(email)){
				HttpHeaders headers = new HttpHeaders();
				headers.set("X-Auth-Token", "api-key " + ResourceBundle.getBundle("config").getString("getresponse.key"));
				headers.set("Content-Type", "application/json");
				HttpEntity<String> entity = new HttpEntity<String>(headers);
				HttpEntity<String> retornoHttp = template.exchange(apiURL + "contacts?query[email]=" + email, HttpMethod.GET, entity, String.class);
				String retorno = retornoHttp.getBody();
				logger.debug(retorno);
				contacts = gson.fromJson(retorno, new TypeToken<List<Contact>>(){}.getType());
			}
		}else{
			logger.info("POSTRESPONSE IS DISABLED");
		}
		return contacts;
	}
	
	public List<Contact>findAllContacts(Integer page){
		List<Contact> contacts = new ArrayList<Contact>();
		if(enabledPostresponse){
			HttpHeaders headers = new HttpHeaders();
			headers.set("X-Auth-Token", "api-key " + ResourceBundle.getBundle("config").getString("getresponse.key"));
			headers.set("Content-Type", "application/json");
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			HttpEntity<String> retornoHttp = template.exchange(apiURL + "contacts?page=" + page, HttpMethod.GET, entity, String.class);
			String retorno = retornoHttp.getBody();
			logger.debug(retorno);
			contacts = gson.fromJson(retorno, new TypeToken<List<Contact>>(){}.getType());
		}else{
			logger.info("POSTRESPONSE IS DISABLED");
		}
		return contacts;
	}
	
}
