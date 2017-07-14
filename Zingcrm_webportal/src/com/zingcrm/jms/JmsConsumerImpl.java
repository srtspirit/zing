package com.zingcrm.jms;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import com.zingcrm.forms.LeadForms;
import com.zingcrm.integration.IntegrationService;

public class JmsConsumerImpl  {
	@Autowired
	private IntegrationService integrationService;


	
	public void updateBpIntegration(LeadForms lead){
		System.out.println(lead.getAddress());
		integrationService.updateBPIntegration(lead);
	}
	
	public IntegrationService getIntegrationService() {
		return integrationService;
	}

	public void setIntegrationService(IntegrationService integrationService) {
		this.integrationService = integrationService;
	}
}
