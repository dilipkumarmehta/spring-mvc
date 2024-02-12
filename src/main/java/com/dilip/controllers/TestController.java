package com.dilip.controllers;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dilip.config.AppConfig;
import com.dilip.intercepter.RequesthandelerInterceptor;

/**
 * http://localhost:8080/spring-mvc/index http://localhost:8080/spring-mvc/home
 */
@Controller
public class TestController {

	@Autowired
	AppConfig appConfig;

	@Value("#{systemProperties['java.class.path']}")
	private String systemProperti;

	@Value("${b2c.clientId}")
	public String clientID;

	TestController() {

	}

	@Autowired
	RequesthandelerInterceptor requesthandelerInterceptor;

	@RequestMapping(value = "/home")
	public String home() {
		System.out.println("home call");
		return "index";

	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		System.out.println("systemProperti:  " + systemProperti);
		System.out.println("*************88");
		Properties properties = System.getProperties();
		properties.entrySet().stream().forEach(p -> System.out.print(p));
		System.out.println("index call");
		System.out.println(requesthandelerInterceptor);
		System.out.println("b2c.clientId " + clientID);
		return new ModelAndView("index");

	}

	@RequestMapping(value = "/req", method = RequestMethod.GET)
	public ModelAndView httpReqRes(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("req call");
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute("hello");
		System.out.println(attribute.toString());
		return new ModelAndView("index");

	}

}
