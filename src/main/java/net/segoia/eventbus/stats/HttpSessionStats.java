package net.segoia.eventbus.stats;

import net.segoia.event.eventbus.EventContext;
import net.segoia.eventbus.events.web.util.WebEventsUtil;

public class HttpSessionStats extends HierarchycalStats{

    @Override
    protected SimpleStats buildNestedStats() {
	return new EventTypeStats();
    }

    @Override
    protected void processNested(EventContext ec) {
	String sessionId = (String)ec.event().getParam(WebEventsUtil.SESSION_ID,1);
	if(sessionId == null) {
	    return;
	}
	getNested(sessionId, true).onEvent(ec);
	
    }

}
