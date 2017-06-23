package com.zingcrm.jms;

import java.io.Serializable;

public interface JmsProducer {

	boolean sendObjectMessage(String destinationName, Serializable obj);
}
