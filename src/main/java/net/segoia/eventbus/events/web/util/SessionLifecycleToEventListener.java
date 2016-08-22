package net.segoia.eventbus.events.web.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionLifecycleToEventListener implements HttpSessionListener{

    @Override
    public void sessionCreated(HttpSessionEvent se) {
	
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
	WebEventsUtil.triggerSessionTerminatedEvent(se.getSession());
    }

}
