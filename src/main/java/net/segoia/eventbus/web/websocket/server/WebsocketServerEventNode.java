package net.segoia.eventbus.web.websocket.server;

import java.util.concurrent.Future;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.AsyncEventTracker;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventContext;
import net.segoia.event.eventbus.EventTracker;
import net.segoia.event.eventbus.peers.DefaultEventRelay;
import net.segoia.event.eventbus.peers.EventNode;
import net.segoia.event.eventbus.peers.EventRelay;
import net.segoia.event.eventbus.peers.PeerEventContext;
import net.segoia.eventbus.demo.status.events.RefreshPeersRequestEvent;
import net.segoia.eventbus.demo.status.events.StatusAppInitEvent;

public class WebsocketServerEventNode extends EventNode{
    
    
    private EventNodeWebsocketServerEndpoint ws;
    
    public WebsocketServerEventNode(EventNodeWebsocketServerEndpoint ws) {
	super();
	this.ws=ws;
	init();
    }
    
    @Override
    protected void init() {
	config.setAutoRelayEnabled(true);
	config.setDefaultRequestedEvents(new TrueCondition());
	
    }
    
//    /* we'll always forward what we get from ws client */
//    public boolean isEventForwardingAllowed(EventContext ec, String peerId) {
//	return (!ec.event().wasRelayedBy(getId()));
//    }

    @Override
    protected EventTracker handleEvent(Event event) {
	if(event.getEt().equals("EBUS:PEER:NEW")) {
	    if(event.getParam("peerId").equals(getId())) {
		System.out.println("YEY "+getId());
	    }
	}
	
	
//	    if(event instanceof StatusAppInitEvent) {
		System.out.println(getId()+" sending: "+event);
//	    }
	Future<Void> future = ws.sendEvent(event);
	return new AsyncEventTracker(future, true);
    }

    @Override
    protected EventRelay buildLocalRelay(String peerId) {
	return new DefaultEventRelay(peerId, this);
    }

    
    public void onWsEvent(Event event) {
	forwardToAll(event);
    }

    @Override
    public void cleanUp() {
	ws.terminate();
    }

    @Override
    protected void handleRemoteEvent(PeerEventContext pc) {
	handleEvent(pc.getEvent());
    }
    
}
