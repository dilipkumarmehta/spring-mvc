package com.dilip.controllers;

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

/**
 * http://localhost:8080/spring-mvc/index http://localhost:8080/spring-mvc/home
 */
@Controller
public class TestController {

	@Autowired
	AppConfig appConfig;

	@Value("${b2c.clientId}")
	public String clientID;

	@RequestMapping(value = "/home")
	public String home() {
		System.out.println("home call");
		return "index";

	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		
		System.out.println("index call");
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
