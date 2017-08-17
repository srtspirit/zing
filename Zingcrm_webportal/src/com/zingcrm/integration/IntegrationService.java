package com.zingcrm.integration;

import javax.jms.ObjectMessage;
import javax.ws.rs.core.MediaType;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.zingcrm.forms.LeadForms;
import com.zingcrm.jms.JmsProducer;

@Component
public class IntegrationService {
	@Autowired
	private JmsProducer jmsProducer;
	@Autowired
	private RestTemplate restTemplate;
	
	public void updateBPIntegration(LeadForms lead){
		ResponseEntity<String> response = null;
		try {
			HttpEntity<LeadForms> requestEntity = new HttpEntity<LeadForms>(lead);
			//requestEntity.getHeaders().setContentType(new org.springframework.http.MediaType(MediaType.APPLICATION_XML));
			response = restTemplate.postForEntity("http://localhost:8161/api/message/ESB.IN.CRM.BUSINESSPARTNER.UPDATE?type=queue", requestEntity, String.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			jmsProducer.sendObjectMessage("DELAYED", lead);
		}
		if (response.getStatusCode() != HttpStatus.OK){
			jmsProducer.sendObjectMessage("DELAYED", lead);
		}
	}

	public JmsProducer getJmsProducer() {
		return jmsProducer;
	}

	public void setJmsProducer(JmsProducer jmsProducer) {
		this.jmsProducer = jmsProducer;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
