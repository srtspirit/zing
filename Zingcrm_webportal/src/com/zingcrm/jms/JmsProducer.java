package com.zingcrm.jms;

import java.io.Serializable;

import javax.jms.Message;

public interface JmsProducer {

	boolean sendObjectMessage(String destinationName, Serializable obj);
	boolean sendMessage(String destinationName, Message message);
	boolean sendMessage(Message message);
}
