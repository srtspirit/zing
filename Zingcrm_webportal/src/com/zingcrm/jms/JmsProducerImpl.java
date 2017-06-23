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
import org.springframework.stereotype.Component;

@Component
public class JmsProducerImpl implements JmsProducer {
	@Autowired
	ConnectionFactory connectionFactory;
	
	@Override
	public boolean sendObjectMessage(String destinationName, Serializable obj){
		Connection connection = null;
		Session session = null;
		Destination destination = null;
		MessageProducer producer = null;
		Message message = null;
		
		try{
			connection = connectionFactory.createConnection();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			
			destination = session.createQueue(destinationName);
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			message = session.createObjectMessage(obj);
			producer.send(message);
			session.commit();
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
	
//	public static void main(String args[]){
//		JmsTemplateImpl jms = new JmsTemplateImpl();
//		jms.connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
//		jms.sendObjectMessage("hui", "Stringmessage");
//	}
}
