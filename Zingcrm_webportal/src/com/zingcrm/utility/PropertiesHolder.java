package com.zingcrm.utility;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;


@Component
public class PropertiesHolder {

	@Resource(name = "myProperties")
	private Properties myProperties;

	public String getProperty(String key) {
		return myProperties.getProperty(key);
	}
}
