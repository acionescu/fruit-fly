package net.segoia.eventbus.web.websocket.client;

import java.net.URI;
import java.net.URISyntaxException;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventContext;
import net.segoia.event.eventbus.EventTracker;
import net.segoia.event.eventbus.peers.EventNode;
import net.segoia.event.eventbus.peers.EventRelay;

/**
 * This websocket client node will open a new websocket for each peer, in a one to one relationship
 * @author adi
 *
 */
public class ParallelWebsocketClientNode extends EventNode {
    private URI uri;

    public ParallelWebsocketClientNode(URI uri) {
	super();
	this.uri = uri;
	init();
    }

    public ParallelWebsocketClientNode(String uri) throws URISyntaxException {
	this(new URI(uri));
    }

    public void start() throws Exception {
	
    }

    @Override
    protected void init() {
	/* make sure we set autorelay enabled, otherwise all events coming from the websocket will be blocked by default */
	config.setAutoRelayEnabled(true);
	/* let the client send anything */
	config.setDefaultRequestedEvents(new TrueCondition());
    }

    @Override
    public void cleanUp() {
	
    }

    @Override
    protected EventTracker handleEvent(Event event) {
	
	return null;
    }

    @Override
    protected EventRelay buildLocalRelay(String peerId) {
	return new ParallelClientWebsocketRelay(peerId, this, uri);
    }

    
}
