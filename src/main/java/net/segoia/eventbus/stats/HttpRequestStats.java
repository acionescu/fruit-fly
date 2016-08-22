package net.segoia.eventbus.stats;

import net.segoia.event.eventbus.EventContext;
import net.segoia.eventbus.events.web.util.WebEventsUtil;

public class HttpRequestStats extends HierarchycalStats{

    @Override
    protected SimpleStats buildNestedStats() {
	return new HttpSessionStats();
    }

    @Override
    protected void processNested(EventContext ec) {
	String ip = (String)ec.event().getParam(WebEventsUtil.REMOTE_ADDR,2);
	if(ip == null) {
	    return;
	}
	getNested(ip, true).onEvent(ec);
    }

}
