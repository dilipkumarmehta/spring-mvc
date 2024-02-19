package com.dilip.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class InitializingBeanExampleBean implements InitializingBean {

	@Autowired
	private Environment environment;
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("***************InitializingBeanExampleBean*********");
		Thread.sleep(100);
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String s : beanDefinitionNames) {
			System.out.println(s);
		}

	}
}