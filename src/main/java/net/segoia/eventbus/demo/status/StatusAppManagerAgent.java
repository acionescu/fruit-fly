package net.segoia.eventbus.demo.status;

import java.util.Map;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.Event;
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
    protected void nodeInit() {
	recentPeers = new LRUCache<>(StatusApp.maxPartnersPerUser);

	mainNode = EBus.getMainNode();
	mainNode.registerPeer(this, new TrueCondition());
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
	    System.out.println("processing new peer event "+event.getId() +" from "+event.from() + " via "+event.getLastRelay());
	    Map<String, PeerStatusView> peersCopy = recenPeersSnapshot();
	    StatusAppModel model = new StatusAppModel(peerId, "Hi, I'm visitor " + StatusApp.stats.newPeer(),
		    peersCopy);
	    StatusAppInitEvent appInitEvent = new StatusAppInitEvent(model);

	    forwardTo(appInitEvent, peerId);

	    /* update peers */
	    updateRecentPeers(peerId, new PeerStatusView(peerId, model.getStatus()));

	});

	addEventHandler("EBUS:PEER:REMOVED", (c) -> {
	    Event event = c.getEvent();
	    String peerId = (String) event.getParam(EventParams.peerId);

	    recentPeers.remove(peerId);
	    System.out.println("Removed peer "+peerId);
	});

	RefreshPeersRequestEvent.class.getName();
	addEventHandler(RefreshPeersRequestEvent.class, (c) -> {
	    Map<String, PeerStatusView> peersCopy = recenPeersSnapshot();

	    PeersViewUpdateEvent pvue = new PeersViewUpdateEvent(peersCopy);

	    forwardTo(pvue, c.getEvent().from());

	});

    }

    private void updateRecentPeers(String peerId, PeerStatusView status) {
	recentPeers.put(peerId, status);
	System.out.println("Recent Peers: "+recentPeers.keySet());
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
    protected void nodeConfig() {
	// TODO Auto-generated method stub

    }

    @Override
    protected void onTerminate() {
	// TODO Auto-generated method stub

    }

}
