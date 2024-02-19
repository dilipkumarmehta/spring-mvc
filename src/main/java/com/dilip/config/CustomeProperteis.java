package com.dilip.config;

import org.springframework.beans.factory.InitializingBean;

public class CustomeProperteis implements InitializingBean {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out
				.println("setting properteis from java calass while starign the server and seting on xml name: " + name);

	}

}
