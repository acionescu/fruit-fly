package net.segoia.eventbus.web.websocket.server;

import java.util.concurrent.Future;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.AsyncEventTracker;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventContext;
import net.segoia.event.eventbus.EventTracker;
import net.segoia.event.eventbus.FilteringEventBus;
import net.segoia.event.eventbus.SimpleEventDispatcher;
import net.segoia.event.eventbus.peers.AgentNode;
import net.segoia.event.eventbus.peers.DefaultEventRelay;
import net.segoia.event.eventbus.peers.EventRelay;

public abstract class WebsocketServerEventNode extends AgentNode {

    private EventNodeWebsocketServerEndpoint ws;

    /**
     * Keep a separate bus to handle events coming from the ws client
     */
    protected FilteringEventBus wsEventsBus;

    public WebsocketServerEventNode(EventNodeWebsocketServerEndpoint ws) {
	/* we don't want the agent to autoinitialize, we will do it */
	super(false);
	this.ws = ws;
    }

    @Override
    protected void nodeConfig() {
	config.setAutoRelayEnabled(true);
	config.setDefaultRequestedEvents(new TrueCondition());

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.event.eventbus.peers.AgentNode#nodeInit()
     */
    @Override
    protected void nodeInit() {
	super.nodeInit();

	wsEventsBus = spawnAdditionalBus(new WebsocketServerNodeDispatcher());
	wsEventsBus.start();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.event.eventbus.peers.EventNode#registerHandlers()
     */
    @Override
    protected void registerHandlers() {
	super.registerHandlers();
	/* register a generic handler that will send all received events to the websocket endpoint */
	addEventHandler((c) -> this.handleServerEvent(c.getEvent()));
    }

    /**
     * Called on events coming from the server
     * 
     * @param event
     * @return
     */
    protected EventTracker handleServerEvent(Event event) {
	Future<Void> future = ws.sendEvent(event);
	return new AsyncEventTracker(future, true);
    }

    @Override
    protected EventRelay buildLocalRelay(String peerId) {
	return new DefaultEventRelay(peerId, this);
    }

    public void onWsEvent(Event event) {
	wsEventsBus.postEvent(event);
    }

    /**
     * Called on events coming from the ws client
     * 
     * @param event
     */
    protected void handleWsEvent(Event event) {
	forwardToAll(event);
    }

    @Override
    public void cleanUp() {
	ws.terminate();
    }

    class WebsocketServerNodeDispatcher extends SimpleEventDispatcher {

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.segoia.event.eventbus.SimpleEventDispatcher#dispatchEvent(net.segoia.event.eventbus.EventContext)
	 */
	@Override
	public boolean dispatchEvent(EventContext ec) {
	    Event event = ec.event();
	    /* make sure the client can't inject relays */
	    event.clearRelays();
	    
	    /* process whatever handlers we have for this event */
	    super.dispatchEvent(ec);
	    
	    /* do a final handling of the event */
	    handleWsEvent(ec.getEvent());
	    return true;
	}

    }
}
