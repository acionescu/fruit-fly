package net.segoia.eventbus.demo.status;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventHandle;
import net.segoia.event.eventbus.EventTracker;
import net.segoia.event.eventbus.constants.EventParams;
import net.segoia.event.eventbus.constants.Events;
import net.segoia.event.eventbus.peers.PeerEventContext;
import net.segoia.event.eventbus.util.EBus;
import net.segoia.util.data.LRUCache;

public class AppInitAgent extends LocalNodeAgent {

    private LRUCache<String, StatusPeerView> recentPeers = new LRUCache<>(StatusApp.maxPartnersPerUser);
    

    @Override
    protected void init() {
	
	/* cache the last N updates */
	addEventHandler("PEER:STATUS:UPDATED", new RemoteEventHandler<AppInitAgent>() {

	    @Override
	    public void handleRemoteEvent(RemoteEventContext<AppInitAgent> rec) {
		PeerEventContext pc = rec.getPeerContext();
		Event event = pc.getEvent();
		String peerId = event.sourceBusId();
		recentPeers.put(peerId, new StatusPeerView(peerId, (String) event.getParam(StatusApp.STATUS)));

	    }

	});
	
	
	/* send init info to new peers */
	
	addEventHandler("EBUS:PEER:NEW", new RemoteEventHandler<AppInitAgent>() {

	    @Override
	    public void handleRemoteEvent(RemoteEventContext<AppInitAgent> rec) {
		Event event = rec.getEvent();
		String peerId = (String)event.getParam(EventParams.peerId);
		
		
		EventHandle eh = Events.builder().ebus().peer().init().getHandle();
		
		if(eh.isAllowed()) {
		    eh.addParam(EventParams.clientId, peerId);
		    eh.addParam(StatusApp.STATUS, "Hi, I'm visitor "+StatusApp.stats.incVisitors());
		    eh.addParam(EventParams.peers, recentPeers);
		    eh.send(peerId);
		}
	    }
	});
	

	mainNode = EBus.getMainNode();
	mainNode.registerPeer(this, new TrueCondition());

    }

    @Override
    public void terminate() {
	// TODO Auto-generated method stub

    }

    @Override
    protected EventTracker postInternally(Event event) {
	// TODO Auto-generated method stub
	return null;
    }

}
