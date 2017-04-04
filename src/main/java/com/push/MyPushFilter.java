
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

		PushBuilder pushBuilder = Request.getBaseRequest(request).getPushBuilder();
		pushBuilder.path("js-images.css").push();
		pushBuilder.path("Layout.css").push();

		chain.doFilter(request, response);
	}

	public void destroy() {

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}