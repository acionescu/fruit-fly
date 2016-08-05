package net.segoia.eventbus.demo.status;

import java.util.Map;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventTracker;
import net.segoia.event.eventbus.constants.EventParams;
import net.segoia.event.eventbus.peers.AgentNode;
import net.segoia.event.eventbus.peers.PeerEventContext;
import net.segoia.event.eventbus.peers.RemoteEventContext;
import net.segoia.event.eventbus.peers.RemoteEventHandler;
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
	addEventHandler("PEER:STATUS:UPDATED", new RemoteEventHandler<StatusAppManagerAgent>() {

	    @Override
	    public void handleRemoteEvent(RemoteEventContext<StatusAppManagerAgent> rec) {
		PeerEventContext pc = rec.getPeerContext();
		Event event = pc.getEvent();
		String peerId = event.from();
		recentPeers.put(peerId, new PeerStatusView(peerId, (String) event.getParam(StatusApp.STATUS)));

	    }

	});

	/* send init info to new peers */

	addEventHandler("EBUS:PEER:NEW", new RemoteEventHandler<StatusAppManagerAgent>() {

	    @Override
	    public void handleRemoteEvent(RemoteEventContext<StatusAppManagerAgent> rec) {
		Event event = rec.getEvent();
		String peerId = (String) event.getParam(EventParams.peerId);

		// EventHandle eh = Events.builder().ebus().peer().init().getHandle();
		//
		// if(eh.isAllowed()) {
		// eh.addParam(EventParams.clientId, peerId);
		// eh.addParam(StatusApp.STATUS, "Hi, I'm visitor "+StatusApp.stats.newPeer());
		// eh.addParam(EventParams.peers, recentPeers);
		// eh.send(peerId);
		// }

		Map<String, PeerStatusView> peersCopy = (Map<String, PeerStatusView>) recentPeers.clone();
		StatusAppInitEvent appInitEvent = new StatusAppInitEvent(
			new StatusAppModel(peerId, "Hi, I'm visitor " + StatusApp.stats.newPeer(), peersCopy));

		// forwardTo(appInitEvent, peerId);
		appInitEvent.to(peerId);
		EBus.postEvent(appInitEvent);

	    }
	});

	RefreshPeersRequestEvent.class.getName();
	addEventHandler(RefreshPeersRequestEvent.class, new RemoteEventHandler<StatusAppManagerAgent>() {

	    @Override
	    public void handleRemoteEvent(RemoteEventContext<StatusAppManagerAgent> rec) {
		Map<String, PeerStatusView> peersCopy = (Map<String, PeerStatusView>) recentPeers.clone();
		
		PeersViewUpdateEvent pvue = new PeersViewUpdateEvent(peersCopy);
		
//		forwardTo(pvue, rec.getEvent().from());
		pvue.to(rec.getEvent().from());
		EBus.postEvent(pvue);
	    }
	});

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

}
