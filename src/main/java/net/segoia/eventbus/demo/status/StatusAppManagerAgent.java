package net.segoia.eventbus.demo.status;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.constants.EventParams;
import net.segoia.event.eventbus.peers.AgentNode;
import net.segoia.event.eventbus.util.EBus;
import net.segoia.eventbus.demo.status.events.PeerReplaceAccepted;
import net.segoia.eventbus.demo.status.events.PeerReplaceData;
import net.segoia.eventbus.demo.status.events.PeerReplaceDenied;
import net.segoia.eventbus.demo.status.events.PeersViewUpdateEvent;
import net.segoia.eventbus.demo.status.events.RefreshPeersRequestEvent;
import net.segoia.eventbus.demo.status.events.ReplacePeerRequestEvent;
import net.segoia.eventbus.demo.status.events.StatusAppInitEvent;
import net.segoia.util.data.LRUCache;

public class StatusAppManagerAgent extends AgentNode {

    private LRUCache<String, PeerStatusView> recentPeers;

    private Set<String> allPeersIds;
    
    @Override
    protected void nodeConfig() {
	/* we want to handle all incoming events */
	config.setGod(true);
    }
    
    
    @Override
    protected void nodeInit() {
	recentPeers = new LRUCache<>(StatusApp.maxPartnersPerUser);
	allPeersIds=new HashSet<>();

	mainNode = EBus.getMainNode();
	mainNode.registerPeerAsAgent(this, new TrueCondition());
    }

    @Override
    protected void registerHandlers() {
	super.registerHandlers();
	/* cache the last N updates */
	addEventHandler("PEER:STATUS:UPDATED", (c) -> {
	    Event event = c.getEvent();
	    String peerId = event.from();
	    updateRecentPeers(peerId, new PeerStatusView(peerId, (String) event.getParam(StatusApp.STATUS)));
	    
	});

	/* send init info to new peers */

	addEventHandler("EBUS:PEER:NEW", (c) -> {
	    Event event = c.getEvent();
	    String peerId = (String) event.getParam(EventParams.peerId);
	    Map<String, PeerStatusView> peersCopy = recentPeersSnapshot();
	    StatusAppModel model = new StatusAppModel(peerId, "Hi, I'm visitor " + StatusApp.stats.newPeer(),
		    peersCopy);
	    StatusAppInitEvent appInitEvent = new StatusAppInitEvent(model);

	    forwardTo(appInitEvent, peerId);
	    /* update peers */
	    updateRecentPeers(peerId, new PeerStatusView(peerId, model.getStatus()));
	    
	    /* keep this node's id */
	    allPeersIds.add(peerId);

	});

	addEventHandler("EBUS:PEER:REMOVED", (c) -> {
	    Event event = c.getEvent();
	    String peerId = (String) event.getParam(EventParams.peerId);

	    recentPeers.remove(peerId);
	    allPeersIds.remove(peerId);
	});

	addEventHandler(RefreshPeersRequestEvent.class, (c) -> {
	    Map<String, PeerStatusView> peersCopy = recentPeersSnapshot();

	    PeersViewUpdateEvent pvue = new PeersViewUpdateEvent(peersCopy);

	    forwardTo(pvue, c.getEvent().from());

	});
	
	addEventHandler(ReplacePeerRequestEvent.class, (c)->{
	    ReplacePeerRequestEvent event = c.event();
	    PeerReplaceData data = event.getData();
	    String newPeerId = data.getNewPeerId();
	    
	    /* check if the required peer is actually present */
	    if(allPeersIds.contains(newPeerId)) {
		
		forwardTo(new PeerReplaceAccepted(data), event.from());
	    }
	    else {
		/* deny replace if the requested peer is not present */
		forwardTo(new PeerReplaceDenied(data, "Unknown peer id"), event.from());
	    }
	});
    }

    private void updateRecentPeers(String peerId, PeerStatusView status) {
	recentPeers.put(peerId, status);
    }

    private Map<String, PeerStatusView> recentPeersSnapshot() {
	Map<String, PeerStatusView> peersCopy = null;
	synchronized (recentPeers) {
	    peersCopy = (Map<String, PeerStatusView>) recentPeers.clone();
	}
	return peersCopy;
    }

    @Override
    public void cleanUp() {
	// TODO Auto-generated method stub

    }


    @Override
    protected void onTerminate() {
	// TODO Auto-generated method stub

    }

}
