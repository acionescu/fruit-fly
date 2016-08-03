package net.segoia.eventbus.web.websocket.client;

import java.net.URI;

import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.peers.EventNode;
import net.segoia.event.eventbus.peers.PeerEventContext;

public class SerialClientWebsocketRelay extends ParallelClientWebsocketRelay {

    public SerialClientWebsocketRelay(String id, EventNode parentNode, URI uri) {
	super(id, parentNode, uri);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.event.eventbus.peers.EventRelay#sendEvent(net.segoia.event.eventbus.Event)
     */
    @Override
    protected void sendEvent(Event event) {
	/*
	 * instead of sending the event to the peer relay, we'll sent it to the parent node, to distribute it to all the
	 * peers
	 */
	parentNode.onRemoteEvent(new PeerEventContext(this, event));
    }

}
