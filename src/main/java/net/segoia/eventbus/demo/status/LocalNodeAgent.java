package net.segoia.eventbus.demo.status;

import java.util.HashMap;
import java.util.Map;

import net.segoia.event.eventbus.peers.EventBusNode;
import net.segoia.event.eventbus.peers.EventBusRelay;
import net.segoia.event.eventbus.peers.PeerEventContext;
import net.segoia.event.eventbus.peers.SimpleEventBusRelay;

public abstract class LocalNodeAgent extends EventBusNode {
    protected EventBusNode mainNode;

    private Map<String, RemoteEventHandler<?>> handlers = new HashMap<>();

    private boolean hasHandlers = false;

    @Override
    protected EventBusRelay buildLocalRelay(String peerId) {
	return new SimpleEventBusRelay(peerId, this);
    }

    protected void addEventHandler(String eventType, RemoteEventHandler<?> handler) {
	handlers.put(eventType, handler);
	hasHandlers = true;
    }

    protected void removeEventHandlers(String eventType) {
	handlers.remove(eventType);
	hasHandlers = (handlers.size() > 0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.segoia.event.eventbus.peers.EventBusNode#handleRemoteEvent(net.segoia.event.eventbus.peers.PeerEventContext)
     */
    @Override
    protected void handleRemoteEvent(PeerEventContext pc) {
	if (!hasHandlers) {
	    return;
	}
	String et = pc.getEvent().getEt();
	RemoteEventHandler h = handlers.get(et);
	if (h != null) {
	    h.handleRemoteEvent(new RemoteEventContext<LocalNodeAgent>(this, pc));
	}

    }

}
