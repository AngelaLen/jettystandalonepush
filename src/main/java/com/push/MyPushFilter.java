
package com.push;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

		// if http/1.1 then return. HTTP/2 is running only on secure ports.
		if (request.isSecure() == false) {
			chain.doFilter(request, response);
			return;
		}

		// gather information about the request here
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String uri = httpRequest.getRequestURI();
		String fileName = uri.substring(uri.lastIndexOf('/') + 1, uri.length());
		String fileExtension = fileName.substring(fileName.lastIndexOf("."));
		if (fileExtension.equals(".html")) {
			System.out.println("yes");
			List<String> resourcesToPush = ResourceUtil.getHtmlImgReferences(fileName);
			PushBuilder pushBuilder = Request.getBaseRequest(request).getPushBuilder();

			for (String resource : resourcesToPush) {
				String test = new String(resource);
				System.out.println(test);
				pushBuilder.path(test).push();

			}

			// pass the request along the filter chain
			chain.doFilter(request, response);
		} else {
			return;
		}
	}

	public void destroy() {

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}