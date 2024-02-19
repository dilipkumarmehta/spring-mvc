package com.dilip.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;

@Component
public class InitializingBeanExampleBean implements InitializingBean, WebApplicationInitializer {

	@Autowired
	private Environment environment;
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("***************afterPropertiesSet *********");
		Thread.sleep(100);
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String s : beanDefinitionNames) {
			System.out.println(s);
		}
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("***************onStartup *********");
		System.setProperty("abc.value", "dilip");

	}

}