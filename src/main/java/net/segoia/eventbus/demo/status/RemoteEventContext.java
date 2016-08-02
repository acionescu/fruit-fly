package net.segoia.eventbus.demo.status;

import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.peers.EventBusNode;
import net.segoia.event.eventbus.peers.PeerEventContext;

public class RemoteEventContext<N extends EventBusNode> {
    private N currentNode;
    private PeerEventContext peerContext;
    
    
    public RemoteEventContext(N currentNode, PeerEventContext peerContext) {
	super();
	this.currentNode = currentNode;
	this.peerContext = peerContext;
    }

    /**
     * @return the currentNode
     */
    public N getCurrentNode() {
        return currentNode;
    }




    /**
     * @return the peerContext
     */
    public PeerEventContext getPeerContext() {
        return peerContext;
    }
    
    public Event getEvent() {
	return peerContext.getEvent();
    }
}
