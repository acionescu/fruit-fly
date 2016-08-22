package net.segoia.eventbus.events.web.util;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;

import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventHandle;
import net.segoia.event.eventbus.builders.DefaultComponentEventBuilder;

public class WebEventsUtil {
    public static String REQUEST = "REQUEST";
    public static String SESSION = "SESSION";

    private static DefaultComponentEventBuilder eventBuilder = new DefaultComponentEventBuilder("HTTP");

    private static DefaultComponentEventBuilder sessionEventBuilder = new DefaultComponentEventBuilder(SESSION);

    /**
     * Store the event created from a request as an attribute with this name on that request
     */
    private static final String ROOT_EVENT = "_rootEvent";

    public static String ATTRIBUTE = "ATTRIBUTE";
    public static String ADDED = "ADDED";
    public static String REMOVED = "REMOVED";
    public static String REPLACED = "REPLACED";

    public static String SESSION_MAX_INACTIVE = "maxInactive";

    public static String REQUEST_HEADER = "rheader";
    public static String REQUEST_PARAMS = "rparams";
    public static String URI = "uri";
    public static String REMOTE_ADDR = "raddr";
    public static String REMOTE_HOST = "rhost";
    public static String REMOTE_PORT = "rport";
    public static String REMOTE_USER = "ruser";
    public static String SESSION_ID = "sid";
    public static String REQUESTED_SESSION = "rsid";
    public static String LOCAL_ADDR = "laddr";
    public static String LOCAL_PORT = "lport";
    public static String SERVER_NAME = "sname";
    public static String SERVER_PORT = "sport";
    public static String PROTOCOL="protocol";

    public static EventHandle fromHttpRequest(HttpServletRequest req) {
	EventHandle eh = eventBuilder.category(REQUEST).name(req.getMethod()).getHandle();

	if (!eh.isAllowed()) {
	    return eh;
	}

	/* add headers */
	Enumeration<String> headerNames = req.getHeaderNames();
	Map<String, String> headers = new HashMap<String, String>();
	while (headerNames.hasMoreElements()) {
	    String nh = headerNames.nextElement();
	    headers.put(nh, req.getHeader(nh));
	}

	if (headers.size() > 0) {
	    eh.addParam(REQUEST_HEADER, headers);
	}

	/* add request parameters */

	Map<String, Object> params = new HashMap<>();

	Enumeration<String> pNames = req.getParameterNames();

	while (pNames.hasMoreElements()) {
	    String pName = pNames.nextElement();
	    params.put(pName, Arrays.asList(req.getParameterValues(pName)));
	}

	eh.addParam(REQUEST_PARAMS, params);

	eh.addParam(URI, req.getRequestURI());

	/* session */
	eh.addParam(SESSION_ID, req.getSession().getId());

	String rsid = req.getRequestedSessionId();
	if (rsid != null) {
	    eh.addParam(REQUESTED_SESSION, rsid);
	}

	/* remote data */
	eh.addParam(REMOTE_ADDR, req.getRemoteAddr());
	eh.addParam(REMOTE_HOST, req.getRemoteHost());
	eh.addParam(REMOTE_PORT, req.getRemotePort());
	eh.addParam(REMOTE_USER, req.getRemoteUser());

	/* server data */

	eh.addParam(LOCAL_ADDR, req.getLocalAddr());
	eh.addParam(LOCAL_PORT, req.getLocalPort());

	eh.addParam(SERVER_NAME, req.getServerName());
	eh.addParam(SERVER_PORT, req.getServerPort());
	
	eh.addParam(PROTOCOL, req.getProtocol());

	/* add this event on the request */
	req.setAttribute(ROOT_EVENT, eh.event());

	return eh;
    }

    /**
     * Returns the root event generated for the request
     * 
     * @param req
     * @return
     */
    public static Event getRootEvent(HttpServletRequest req) {
	return (Event) req.getAttribute(ROOT_EVENT);
    }

    /**
     * Returns the root event generated when the passed session was initialized
     * 
     * @param session
     * @return
     */
    public static Event getRootEvent(HttpSession session) {
	try {
	    return (Event) session.getAttribute(ROOT_EVENT);
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public static void triggerNewRequestEvent(HttpServletRequest req) {
	fromHttpRequest(req).post();
    }

    public static void triggerNewSessionEvent(HttpServletRequest req) {
	/* generate a http session init event if the session is new */
	HttpSession sess = req.getSession();
	if (sess.isNew() || sess.getAttribute(ROOT_EVENT) == null) {
	    EventHandle eh = eventBuilder.spawnFrom(getRootEvent(req)).category(SESSION).initialized().getHandle();
	    if (eh.isAllowed()) {
		eh.addParam(SESSION_ID, sess.getId()).addParam(SESSION_MAX_INACTIVE, sess.getMaxInactiveInterval());
		eh.post();
	    }
	    /* store this event as the root event for this session */
	    sess.setAttribute(ROOT_EVENT, eh.event());
	}
    }

    public static void triggerSessionTerminatedEvent(HttpSession session) {
	EventHandle eh = eventBuilder.spawnFrom(getRootEvent(session)).category(SESSION).terminated().getHandle();
	if (eh.isAllowed()) {
	    eh.addParam(SESSION_ID, session.getId()).post();
	}
    }

    public static DefaultComponentEventBuilder spawnFromRootEvent(HttpServletRequest req) {
	return eventBuilder.spawnFrom(getRootEvent(req));
    }

    public static void triggerSessionAttributedAdded(HttpSessionBindingEvent e) {
	EventHandle eh = sessionEventBuilder.spawnFrom(getRootEvent(e.getSession())).category(ATTRIBUTE).name(ADDED)
		.getHandle();
	if (eh.isAllowed()) {
	    eh.addParam("name", e.getName());
	    eh.addParam("value", e.getValue());
	    eh.post();
	}
    }

    public static void triggerSessionAttributeRemoved(HttpSessionBindingEvent e) {

	EventHandle eh = sessionEventBuilder.spawnFrom(getRootEvent(e.getSession())).category(ATTRIBUTE).name(REMOVED)
		.getHandle();
	if (eh.isAllowed()) {
	    eh.addParam("name", e.getName());
	    eh.addParam("value", e.getValue());
	    eh.post();
	}
    }

    public static void triggerSessionAttributeReplaced(HttpSessionBindingEvent e) {
	HttpSession session = e.getSession();
	EventHandle eh = sessionEventBuilder.spawnFrom(getRootEvent(session)).category(ATTRIBUTE).name(REPLACED)
		.getHandle();
	if (eh.isAllowed()) {
	    eh.addParam("name", e.getName());
	    eh.addParam("oldValue", e.getValue());
	    eh.addParam("newValue", session.getAttribute(e.getName()));
	    eh.post();
	}
    }

}
