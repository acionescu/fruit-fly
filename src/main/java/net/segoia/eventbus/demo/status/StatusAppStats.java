package net.segoia.eventbus.demo.status;

import net.segoia.event.eventbus.EventContext;
import net.segoia.event.eventbus.EventHandle;
import net.segoia.event.eventbus.EventListener;
import net.segoia.event.eventbus.util.EBus;
import net.segoia.eventbus.events.web.util.WebEventsUtil;
import net.segoia.eventbus.stats.HttpRequestStats;
import net.segoia.eventbus.web.stats.events.SimpleStatsEvent;

public class StatusAppStats implements EventListener{
    private long visitors;
    private long activeUsers;
    
    private String NAME="STATUS-APP";
    
    private HttpRequestStats httpStats = new HttpRequestStats();
    
    public long newPeer() {
	activeUsers++;
	return ++visitors;
    }
    
    public void peerRemoved() {
	activeUsers--;
    }
    
    public long visitors() {
	return visitors;
    }

    @Override
    public void onEvent(EventContext ec) {
	httpStats.onEvent(ec);
	
//	HierarchycalStats local = (HierarchycalStats)httpStats.getNestedStats().get("::1");
//	if(local!= null) {
//	    System.out.println(local.getNestedStats());
//	}
	EventHandle eh = EBus.getHandle(new SimpleStatsEvent(NAME, httpStats));
	if(eh.isAllowed()) {
	    eh.post();
	}
    }
    
    
    public void onHttpRequest(EventContext ec) {
	String ip = (String)ec.event().getParam(WebEventsUtil.REMOTE_ADDR);
	httpStats.getNested(ip, true).onEvent(ec);
    }
    
    public void onSessionInit(EventContext ec) {
	
    }
    
    public void onSessionTerminated(EventContext ec) {
	
    }
    
    /**
     * @return the httpStats
     */
    public HttpRequestStats getHttpStats() {
        return httpStats;
    }

    @Override
    public void init() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void terminate() {
	// TODO Auto-generated method stub
	
    }
    
}
