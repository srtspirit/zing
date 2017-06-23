package com.zingcrm.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zingcrm.forms.LeadForms;
import com.zingcrm.jms.JmsProducer;

@Component
public class IntegrationService {
	@Autowired
	private JmsProducer jmsProducer;
	
	public void updateBPIntegration(LeadForms lead){
		jmsProducer.sendObjectMessage("DELAYED", lead);
	}

	public JmsProducer getJmsProducer() {
		return jmsProducer;
	}

	public void setJmsProducer(JmsProducer jmsProducer) {
		this.jmsProducer = jmsProducer;
	}
}
