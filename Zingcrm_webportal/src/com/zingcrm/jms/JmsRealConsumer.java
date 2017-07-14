package com.zingcrm.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;

public class JmsRealConsumer implements JmsConsumer {
	@Autowired
	private ConnectionFactory connFactory;
	@Autowired
	private JmsProducer jmsProducer;
	
	@Override
	public void consume() {
		// TODO Auto-generated method stub
		Connection connection = null; 
		Session session = null;
		Destination destination = null;
		MessageConsumer consumer = null;
		Message message = null;
			
		try{
			connection = connFactory.createConnection();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("DELAYED");
			consumer = session.createConsumer(destination);
			message = consumer.receiveNoWait();
			
			if(message != null){
				if (jmsProducer.sendMessage(message)){
					session.commit();
				}
			}
		} catch(JMSException e){
			e.printStackTrace();
		}
	}

	@Override
	public Message consumeOneMessage(String destinationName) {
//		Connection connection = null; 
//		try{
////			connection = connFactory.createConnection();
////			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
////			destination = session.createQueue("DELAYED");
////			consumer = session.createConsumer(destination);
////			message = consumer.receiveNoWait();
////			
////			if(message != null){
////				if (jmsProducer.sendMessage(message)){
////					session.commit();
//				}
//			}
//		} catch(JMSException e){
//			e.printStackTrace();
//		}
		return null;
	}

}
