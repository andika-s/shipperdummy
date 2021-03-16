package com.shipperdummy.exception;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		logger.info("[SHIPPER] LOGGING REQUEST : METHOD = {} , URI = {} ", request.getMethod(),
				request.getRequestURI());

		filterChain.doFilter(request, response);

		logger.info("[SHIPPER] LOGGING RESPONSE : STATUS = {} , CONTENT TYPE = {} ", response.getStatus(),
				response.getContentType());
	}

	@Override
	public void destroy() {
		logger.warn("[SHIPPER]  Destructing Filter : {} ", this);
	}

}
