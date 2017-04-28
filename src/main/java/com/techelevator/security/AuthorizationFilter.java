package com.techelevator.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techelevator.models.User;

public class AuthorizationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		User sessionUser = getUserFromSession(httpRequest);
//		String requestUser = getUserFromRequest(httpRequest);
//		
//		if(requestUser != null && requestUser.equals(sessionUser) == false) {
//			if(sessionUser == null) {
//				redirectToLoginPage(httpRequest, httpResponse);
//			} else {
//				httpResponse.sendError(403);
//			}
//		}
		
		if(httpRequest.getRequestURI().contains("/user/")){
			if(sessionUser == null){
				redirectToLoginPage(httpRequest, httpResponse);
			}
		}
		else if (httpRequest.getRequestURI().contains("/employee/")) {
			if (sessionUser == null || sessionUser.getUserType() < 2) {
				httpResponse.sendError(403);
			}
		}
		else if (httpRequest.getRequestURI().contains("/admin/")) {
			if (sessionUser == null || sessionUser.getUserType() < 3) {
				httpResponse.sendError(403);
			}
		}
		
		chain.doFilter(request, response);
	}

	private void redirectToLoginPage(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws IOException {
		
		String originalRequest = httpRequest.getRequestURL().toString();
		String queryString = httpRequest.getQueryString();
		if(queryString != null) {
			originalRequest = originalRequest + "?" + queryString;
		}
		
		String context = httpRequest.getServletContext().getContextPath();
		httpResponse.sendRedirect(context+"/login?destination="+URLEncoder.encode(originalRequest, "UTF-8"));
	}

	private User getUserFromSession(HttpServletRequest httpRequest) {
		return (User)httpRequest.getSession().getAttribute("currentUser");
	}

//	private String getUserFromRequest(HttpServletRequest httpRequest) {
//		String requestUser = null;
//		
//		return requestUser;
//	}

	@Override
	public void destroy() {
		
	}

}
