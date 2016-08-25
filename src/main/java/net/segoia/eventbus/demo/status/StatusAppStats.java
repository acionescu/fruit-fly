/**
 * fruit-fly - A status sharing demo app, built with web-events and websockets
 * Copyright (C) 2016  Adrian Cristian Ionescu - https://github.com/acionescu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
