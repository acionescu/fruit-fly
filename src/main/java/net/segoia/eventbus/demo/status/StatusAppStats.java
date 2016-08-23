package net.segoia.eventbus.demo.status;

import net.segoia.event.eventbus.EventContext;
import net.segoia.event.eventbus.EventListener;
import net.segoia.eventbus.events.web.util.WebEventsUtil;
import net.segoia.eventbus.stats.HierarchycalStats;
import net.segoia.eventbus.stats.HttpRequestStats;

public class StatusAppStats implements EventListener{
    private long visitors;
    private long activeUsers;
    
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
	
	HierarchycalStats local = (HierarchycalStats)httpStats.getNestedStats().get("::1");
	if(local!= null) {
	    System.out.println(local.getNestedStats());
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
