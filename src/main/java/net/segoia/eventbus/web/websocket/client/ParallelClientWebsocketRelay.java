package net.segoia.eventbus.web.websocket.client;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.peers.EventNode;
import net.segoia.event.eventbus.peers.EventRelay;

/**
 * This websocket relay will simply forward the remote events to the websocket, and all events from the ws to the peer
 * relay, without notifying the parent node
 * 
 * @author adi
 *
 */
public class ParallelClientWebsocketRelay extends EventRelay {
    private EventNodeWebsocketClientEndpoint ws;
    private URI uri;

    public ParallelClientWebsocketRelay(String id, EventNode parentNode, URI uri) {
	super(id, parentNode);
	this.uri = uri;
    }

    @Override
    protected void init() {

	ws = new EventNodeWebsocketClientEndpoint(this);

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.event.eventbus.peers.EventRelay#start()
     */
    @Override
    protected void start() {
	WebSocketContainer wsContainer = ContainerProvider.getWebSocketContainer();
	try {
	    wsContainer.connectToServer(ws, uri);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    protected void cleanUp() {
	ws.terminate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.event.eventbus.peers.EventRelay#onRemoteEvent(net.segoia.event.eventbus.Event)
     */
    @Override
    protected void onRemoteEvent(Event event) {
	event.addRelay(getParentNodeId());
	ws.sendEvent(event);
    }

}
