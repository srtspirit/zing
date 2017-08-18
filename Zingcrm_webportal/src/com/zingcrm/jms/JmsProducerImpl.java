package com.zingcrm.jms;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class JmsProducerImpl implements JmsProducer {
	@Autowired
	protected ConnectionFactory connectionFactory;
	
	protected JmsTemplate springTemplate;
	
	@Override
	public boolean sendObjectMessage(String destinationName, final Serializable obj){
		springTemplate = new JmsTemplate(connectionFactory);
		springTemplate.send(destinationName, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(obj);
			}
		});
		
		if (true) return true;
		
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		Message message = null;
		
		try{
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			producer = createProducer(session, destinationName);
			message = session.createObjectMessage(obj);
			message.setStringProperty("InitialDestination", destinationName);
			producer.send(message);
			//session.commit();
		} catch(JMSException e){
			e.printStackTrace();
			try {
				session.rollback();
			} catch (JMSException e1) {
				e1.printStackTrace();
			}
			return false;
		} finally{
			if (connection != null){
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
				
		}
		
		return true;
	}

	@Override
	public boolean sendMessage(String destinationName, Message message) {
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		
		try{
			connection = connectionFactory.createConnection();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);;
			producer = createProducer(session, destinationName);
			producer.send(message);
			session.commit();
		} catch(JMSException e){
			e.printStackTrace();
			return false;
		} finally{
			if (connection != null){
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
		
		return true;
	}
	
	@Override
	public boolean sendMessage(Message message){
		try {
			return sendMessage(message.getStringProperty("InitialDestination"), message);
		} catch (JMSException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	protected MessageProducer createProducer(Session session, String destinationName) throws JMSException{
		Destination destination = session.createQueue(destinationName);
		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		
		return producer;
	}
	
//	public static void main(String args[]){
//		JmsTemplateImpl jms = new JmsTemplateImpl();
//		jms.connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
//		jms.sendObjectMessage("hui", "Stringmessage");
//	}
}
