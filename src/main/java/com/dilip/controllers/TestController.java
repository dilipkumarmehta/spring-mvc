package com.dilip.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * http://localhost:8080/spring-mvc/index http://localhost:8080/spring-mvc/home
 */
@Controller
public class TestController {

	@RequestMapping(value = "/home")
	public String home() {
		System.out.println("home call");
		return "index";

	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		System.out.println("index call");
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
