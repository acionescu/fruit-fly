package net.segoia.eventbus.web.websocket;

import java.util.concurrent.Future;

import net.segoia.event.eventbus.AsyncEventTracker;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventContext;
import net.segoia.event.eventbus.EventTracker;
import net.segoia.event.eventbus.peers.EventBusNode;
import net.segoia.event.eventbus.peers.EventBusRelay;
import net.segoia.event.eventbus.peers.PeerEventContext;
import net.segoia.event.eventbus.peers.SimpleEventBusRelay;

public class WebsocketEventBusNode extends EventBusNode{
    
    
    private EventBusWebsocketEndpoint ws;
    
    public WebsocketEventBusNode(EventBusWebsocketEndpoint ws) {
	super();
	this.ws=ws;
    }

    @Override
    protected EventTracker postInternally(Event event) {
	Future<Void> future = ws.sendEvent(event);
	return new AsyncEventTracker(future, true);
    }

    @Override
    protected EventBusRelay buildLocalRelay(String peerId) {
	return new SimpleEventBusRelay(peerId, this);
    }

    
    public void onWsEvent(Event event) {
	forwardToAll(event);
    }

    @Override
    protected void init() {

	
    }

    @Override
    public void terminate() {
	//TODO: implement this
    }

    @Override
    protected void handleRemoteEvent(PeerEventContext pc) {
	postInternally(pc.getEvent());
    }
    
}
