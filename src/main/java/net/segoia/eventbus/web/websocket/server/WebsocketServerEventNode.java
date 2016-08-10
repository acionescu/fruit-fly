package net.segoia.eventbus.web.websocket.server;

import java.util.concurrent.Future;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.AsyncEventTracker;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventTracker;
import net.segoia.event.eventbus.peers.AgentNode;
import net.segoia.event.eventbus.peers.DefaultEventRelay;
import net.segoia.event.eventbus.peers.EventRelay;

public abstract class WebsocketServerEventNode extends AgentNode{
    
    
    private EventNodeWebsocketServerEndpoint ws;
    
    public WebsocketServerEventNode(EventNodeWebsocketServerEndpoint ws) {
	/* we don't want the agent to autoinitialize, we will do it */
	super(false);
	this.ws=ws;
    }
    
    @Override
    protected void nodeConfig() {
	config.setAutoRelayEnabled(true);
	config.setDefaultRequestedEvents(new TrueCondition());
	
    }
    
    
    
    
    /* (non-Javadoc)
     * @see net.segoia.event.eventbus.peers.EventNode#registerHandlers()
     */
    @Override
    protected void registerHandlers() {
	super.registerHandlers();
	/* register a generic handler that will send all received events to the websocket endpoint */
	addEventHandler((c) -> this.handleEvent(c.getEvent()));
    }

    protected EventTracker handleEvent(Event event) {
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
    
}
