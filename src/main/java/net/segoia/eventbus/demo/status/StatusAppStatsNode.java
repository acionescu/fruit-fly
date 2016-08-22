package net.segoia.eventbus.demo.status;

import net.segoia.event.conditions.LooseEventMatchCondition;
import net.segoia.event.eventbus.EventContext;
import net.segoia.event.eventbus.EventListener;
import net.segoia.event.eventbus.FilteringEventBus;
import net.segoia.event.eventbus.SimpleEventDispatcher;
import net.segoia.event.eventbus.util.EBus;

public class StatusAppStatsNode implements EventListener{
    private  FilteringEventBus internalBus;

    @Override
    public void onEvent(EventContext ec) {
	internalBus.postEvent(ec.event());
    }

    @Override
    public void init() {
	/* spawn an internal bus on a separate thread */
	internalBus = EBus.buildAsyncFilteringEventBus(100, 1, new SimpleEventDispatcher());
//	internalBus = EBus.buildFilteringEventBusOnMainLoop(new SimpleEventDispatcher());
	
	EBus.instance().registerListener(this,9999);
	
	internalBus.addEventHandler(LooseEventMatchCondition.build("HTTP", "REQUEST"), (c)->{
	   StatusApp.stats.onHttpRequest(c); 
	});
	
	internalBus.addEventHandler("HTTP:SESSION:INITIALIZED", (c)->{
	    StatusApp.stats.onSessionInit(c); 
	});
	
	internalBus.addEventHandler("HTTP:SESSION:TERMINATED", (c)->{
	    StatusApp.stats.onSessionTerminated(c); 
	});
	
	internalBus.addEventHandler((c)->{
	    StatusApp.stats.onEvent(c);
	},999);
	
    }

    @Override
    public void terminate() {
	internalBus.stop();
    }
    
    
    
}
