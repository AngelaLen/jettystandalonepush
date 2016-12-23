
package com.push;

import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.PushBuilder;
import org.eclipse.jetty.server.Request;

public final class MyPushFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

	
		HttpServletResponse res = (HttpServletResponse) response;

		// if http/1.1 then return. HTTP/2 is running only on secure ports.
		if (request.isSecure() == false) {
			chain.doFilter(request, res);
			return;
		}

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String uri = httpRequest.getRequestURI();
		String fileName = uri.substring(uri.lastIndexOf('/') + 1, uri.length());

		// implement a basic push implementation here
		switch (fileName) {
		case "index.html":

			// test: push only the css files

			PushBuilder pushBuilder = Request.getBaseRequest(request).getPushBuilder();
			
		//pushBuilder.path("Controls/Navigation/NewsletterSubscription.html").push();
		pushBuilder.path("assets/css/custom.css").push();
		pushBuilder.path("assets/css/style.css").push();
		

			break;
		default:
			break;

		}

		// pass the request along the filter chain
		if (!fileName.contains("custom.css")) {
			chain.doFilter(request, res);
		}
	}

	public void destroy() {

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}