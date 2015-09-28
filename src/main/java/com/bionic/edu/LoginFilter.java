package com.bionic.edu;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "LoginFilter")
public class LoginFilter implements Filter {

	@Autowired
	private LoginController loginController;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		if (loginController == null) {
			System.out.println("\n\n\n\nNULL\n\n\n\n");
		}

		if ((loginController != null && loginController.isLoggedIn())
				|| req.getRequestURI().endsWith("loginPage.xhtml")) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(req.getContextPath() + "/loginPage.xhtml");
		}

	}

	@Override
	public void destroy() {
	}

}
