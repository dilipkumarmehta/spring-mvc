package com.dilip.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${b2c.clientId}")
	String name;

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
		System.out.println("***************onStartup ********* set the properteis befor loading the xml configuration");
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
			properties.load(resourceStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(properties.size());
		System.out.println("b2c.redirectUri" + properties.get("b2c.redirectUri"));
		System.setProperty("abc.value", properties.get("b2c.redirectUri").toString());
		

	}

}