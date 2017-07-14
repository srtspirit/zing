package com.zingcrm.confing;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoaderListener;

import com.zingcrm.jms.JmsConsumer;

public class ContextLoaderListenerEnableJMS extends ContextLoaderListener {
	@Autowired
	public JmsConsumer jmsConsumer;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		super.contextInitialized(event);
		System.out.println("hui");
	}

}
