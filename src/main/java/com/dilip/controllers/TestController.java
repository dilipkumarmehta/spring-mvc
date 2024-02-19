package com.dilip.controllers;

import java.util.Arrays;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.dilip.config.AppConfig;
import com.dilip.config.InitializingBeanExampleBean;
import com.dilip.intercepter.RequesthandelerInterceptor;

/**
 * http://localhost:8080/spring-mvc/index http://localhost:8080/spring-mvc/home
 */
@Controller
public class TestController {

	@Autowired
	AppConfig appConfig;

	@Autowired
	InitializingBeanExampleBean initializingBeanExampleBean;

	@Value("#{systemProperties['java.class.path']}")
	private String systemProperti;

	@Value("${b2c.clientId}")
	public String clientID;

	TestController() {

	}

	RestTemplate restTemplate = new RestTemplate();

	@Autowired(required = false)
	RequesthandelerInterceptor requesthandelerInterceptor;

	/*
	 */
	public void postCall() {
		// post call
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("X-COM-PERSIST", "NO");
		headers.set("X-COM-LOCATION", "USA");

		Object req = new Object();
		HttpEntity<Object> entity = new HttpEntity<>(req, headers);
		String url = "http://localhost:8080/users/{id}";
		ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
		responseEntity.getBody();

	}

	public void getCall() {
		// post call
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("X-COM-PERSIST", "NO");
		headers.set("X-COM-LOCATION", "USA");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String url = "http://localhost:8080/users/{id}";
		ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
		responseEntity.getBody();
		// call
	}

	@RequestMapping(value = "/home")
	public String home() {
		System.out.println("home api request received");

		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		String string = initializingBeanExampleBean.map.get("b2c.redirectUri");
		System.out.println("geting value from Map created while tomcat startup" + string);
		System.out.println("systemProperti:  " + systemProperti);
		System.out.println("*************88");
		Properties properties = System.getProperties();
		properties.entrySet().stream().forEach(p -> System.out.print(p));
		System.out.println("index api request received");
		System.out.println(requesthandelerInterceptor);
		System.out.println("b2c.clientId " + clientID);
		return new ModelAndView("index");

	}

	@RequestMapping(value = "/req", method = RequestMethod.GET)
	public ModelAndView httpReqRes(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("req api request received call");
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute("sessionobject");
		System.out.println(attribute.toString());
		return new ModelAndView("index");

	}

}
