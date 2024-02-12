package com.dilip.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Service
public class RequesthandelerInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("interceptor get call");
		if (true) {
			response.setContentType("text/html");
			response.getWriter().println("Getting response from interceptor not forwardign the request to controller");
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}
