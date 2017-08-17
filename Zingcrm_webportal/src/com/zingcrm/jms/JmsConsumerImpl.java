package com.zingcrm.jms;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import com.zingcrm.forms.LeadForms;
import com.zingcrm.integration.IntegrationService;

public class JmsConsumerImpl implements MessageListener  {
	@Autowired
	private IntegrationService integrationService;


	
	public void updateBpIntegration(Message message){
		try {
			System.out.println(message.getJMSMessageID());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		integrationService.updateBPIntegration(lead);
	}
	
	public void updateBpIntegration(String message){
		
			System.out.println(message);
		
//		integrationService.updateBPIntegration(lead);
	}
	
	public IntegrationService getIntegrationService() {
		return integrationService;
	}

	public void setIntegrationService(IntegrationService integrationService) {
		this.integrationService = integrationService;
	}

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		try {
			System.out.println(arg0.getJMSMessageID());
			System.out.println( ((LeadForms)((ObjectMessage)arg0).getObject()).getAddress() ) ;
			LeadForms lead = (LeadForms)((ObjectMessage)arg0).getObject();
			lead.setMessageId(arg0.getJMSMessageID());
			integrationService.updateBPIntegration(lead);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
