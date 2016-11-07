
package com.push;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.push.*;
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
		switch (fileName) {
		case "index.html":
			List<String> resourcesToPush = ResourceUtil.getHtmlImgReferences(fileName);
			PushBuilder pushBuilder = Request.getBaseRequest(request).getPushBuilder();
			
			for (String resource : resourcesToPush) {
				pushBuilder.path(resource).push();
			}
			
			break;
		default:
			break;

		}
		
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void destroy() {

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}