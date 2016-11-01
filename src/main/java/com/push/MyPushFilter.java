
package com.push;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.jetty.server.PushBuilder;
import org.eclipse.jetty.server.Request;

public final class MyPushFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("Do Filter wordt geroepen");
		// if http/1.1 then return. HTTP/2 is running only on secure ports.
		if (request.isSecure() == false) {
			chain.doFilter(request, response);
			return;
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// System.out.println("HttpRequest is" + httpRequest );
		String uri = httpRequest.getRequestURI();
		String fileName = uri.substring(uri.lastIndexOf('/') + 1, uri.length());
		System.out.println("Switch uri is " + fileName);
		// implement a basic push implementation here
//		switch (fileName) {
//		case "index.html":
//			System.out.println("de case is index.html");
//			PushBuilder pushBuilder = Request.getBaseRequest(request).getPushBuilder();
//			Request r = Request.getBaseRequest(request);
//			System.out.println(r);
//			System.out.println(r.getPushBuilder());
//			pushBuilder.path("images/34.png").push();
//			break;
//		default:
//			break;

		//}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void destroy() {

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}