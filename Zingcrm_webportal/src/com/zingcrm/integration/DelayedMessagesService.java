package com.zingcrm.integration;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;

import com.zingcrm.jms.JmsConsumer;
import com.zingcrm.jms.JmsProducer;

public class DelayedMessagesService {
	@Autowired
	private JmsConsumer jmsConsumer;
	@Autowired
	private JmsProducer jmsProducer;
	
	//TODO get it through properties
	public static final String DELAYED_QUEUE_NAME = "DELAYED";
	public static final int REDELIVERY_MAX_COUNT = 3;
	public static final String DEAD_LETTER_QUEUE_NAME = "DLQ";
	
	public void findAndHandleDelayedMessages(){
		//TODO wrap all this in transaction
		Message message = jmsConsumer.consumeOneMessage(DELAYED_QUEUE_NAME);
		
		if (message != null){
			int deliveryCount = 0;
			try {
				deliveryCount = message.getIntProperty("deliveryCount");
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (deliveryCount > REDELIVERY_MAX_COUNT){
				jmsProducer.sendMessage(DEAD_LETTER_QUEUE_NAME, message);
			}
			else{
				jmsProducer.sendMessage(message);
			}
		}
	}
	
	public JmsConsumer getJmsConsumer() {
		return jmsConsumer;
	}
	public void setJmsConsumer(JmsConsumer jmsConsumer) {
		this.jmsConsumer = jmsConsumer;
	}

	public JmsProducer getJmsProducer() {
		return jmsProducer;
	}

	public void setJmsProducer(JmsProducer jmsProducer) {
		this.jmsProducer = jmsProducer;
	}
}
