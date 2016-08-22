package net.segoia.eventbus.events.web.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RequestToEventFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	HttpServletRequest req = (HttpServletRequest)request;
	/* trigger a new event for this request */
	WebEventsUtil.triggerNewRequestEvent(req);
	
	/* trigger also a new session event if the case */
	WebEventsUtil.triggerNewSessionEvent(req);
	
	chain.doFilter(req, response);
	
    }

    @Override
    public void destroy() {
	// TODO Auto-generated method stub
	
    }

}
