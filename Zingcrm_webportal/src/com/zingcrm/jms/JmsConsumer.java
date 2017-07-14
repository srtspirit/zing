package com.zingcrm.jms;

import javax.jms.Message;

public interface JmsConsumer {
	void consume();
	Message consumeOneMessage(String destinationName);
}
