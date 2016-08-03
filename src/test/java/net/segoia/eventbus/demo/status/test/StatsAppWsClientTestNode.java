package net.segoia.eventbus.demo.status.test;

import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventTracker;
import net.segoia.event.eventbus.peers.AgentNode;
import net.segoia.event.eventbus.peers.PeerEventContext;

public class StatsAppWsClientTestNode extends AgentNode{

   

    @Override
    protected void init() {
	System.out.println(getId());
    }

    @Override
    public void cleanUp() {
	// TODO Auto-generated method stub
	
    }

    @Override
    protected EventTracker handleEvent(Event event) {
	
	return null;
    }

    /* (non-Javadoc)
     * @see net.segoia.event.eventbus.peers.AgentNode#handleRemoteEvent(net.segoia.event.eventbus.peers.PeerEventContext)
     */
    @Override
    protected void handleRemoteEvent(PeerEventContext pc) {
	System.out.println(pc.getEvent());
    }
    
    

}
