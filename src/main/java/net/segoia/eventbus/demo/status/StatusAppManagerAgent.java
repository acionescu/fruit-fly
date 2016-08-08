package net.segoia.eventbus.demo.status;

import java.util.Map;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventTracker;
import net.segoia.event.eventbus.constants.EventParams;
import net.segoia.event.eventbus.peers.AgentNode;
import net.segoia.event.eventbus.util.EBus;
import net.segoia.eventbus.demo.status.events.PeersViewUpdateEvent;
import net.segoia.eventbus.demo.status.events.RefreshPeersRequestEvent;
import net.segoia.eventbus.demo.status.events.StatusAppInitEvent;
import net.segoia.util.data.LRUCache;

public class StatusAppManagerAgent extends AgentNode {

    private LRUCache<String, PeerStatusView> recentPeers;

    @Override
    protected void agentInit() {
	recentPeers = new LRUCache<>(StatusApp.maxPartnersPerUser);

	mainNode = EBus.getMainNode();
	mainNode.registerPeer(this, new TrueCondition());
    }

    @Override
    protected void registerHandlers() {

	/* cache the last N updates */
	addEventHandler("PEER:STATUS:UPDATED", (c) -> {
	    Event event = c.getEvent();
	    String peerId = event.from();
	    recentPeers.put(peerId, new PeerStatusView(peerId, (String) event.getParam(StatusApp.STATUS)));

	});

	/* send init info to new peers */

	addEventHandler("EBUS:PEER:NEW", (c) -> {
	    Event event = c.getEvent();
	    String peerId = (String) event.getParam(EventParams.peerId);

	    Map<String, PeerStatusView> peersCopy = recenPeersSnapshot();
	    StatusAppModel model = new StatusAppModel(peerId, "Hi, I'm visitor " + StatusApp.stats.newPeer(),
		    peersCopy);
	    StatusAppInitEvent appInitEvent = new StatusAppInitEvent(model);

	    forwardTo(appInitEvent, peerId);

	    /* update peers */
	    recentPeers.put(peerId, new PeerStatusView(peerId, model.getStatus()));

	});

	RefreshPeersRequestEvent.class.getName();
	addEventHandler(RefreshPeersRequestEvent.class, (c) -> {
	    Map<String, PeerStatusView> peersCopy = recenPeersSnapshot();

	    PeersViewUpdateEvent pvue = new PeersViewUpdateEvent(peersCopy);

	    forwardTo(pvue, c.getEvent().from());

	});

    }

    private void updateRecentPeers(String peerId, PeerStatusView status) {
	synchronized (recentPeers) {
	    recentPeers.put(peerId, status);
	}
    }

    private Map<String, PeerStatusView> recenPeersSnapshot() {
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
    protected EventTracker handleEvent(Event event) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected void agentConfig() {
	// TODO Auto-generated method stub

    }

    @Override
    protected void onTerminate() {
	// TODO Auto-generated method stub
	
    }

}
