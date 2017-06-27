package com.zingcrm.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.zingcrm.forms.LeadForms;
import com.zingcrm.jms.JmsProducer;

@Component
public class IntegrationService {
	@Autowired
	private JmsProducer jmsProducer;
	@Autowired
	private RestTemplate restTemplate;
	@Value("${bus.rest.upgradebp.url}")
	private String esbUrlUpdateBp;
	

	public void updateBPIntegration(LeadForms lead){
		try {
			restTemplate.postForObject(esbUrlUpdateBp, lead, String.class);
		} catch (Throwable e) {
			e.printStackTrace();
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

	public String getEsbUrlUpdateBp() {
		return esbUrlUpdateBp;
	}


	public void setEsbUrlUpdateBp(String esbUrlUpdateBp) {
		this.esbUrlUpdateBp = esbUrlUpdateBp;
	}
}
