package net.segoia.eventbus.demo.status.server;

import net.segoia.event.conditions.AndCondition;
import net.segoia.event.conditions.Condition;
import net.segoia.event.conditions.LooseEventMatchCondition;
import net.segoia.event.conditions.NotCondition;
import net.segoia.event.conditions.OrCondition;
import net.segoia.event.conditions.StrictEventMatchCondition;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventContext;
import net.segoia.event.eventbus.EventTracker;
import net.segoia.event.eventbus.peers.events.PeerRegisterRequestEvent;
import net.segoia.event.eventbus.peers.events.PeerRequestUnregisterEvent;
import net.segoia.event.eventbus.util.EBus;
import net.segoia.eventbus.demo.status.PeerStatusView;
import net.segoia.eventbus.demo.status.StatusAppModel;
import net.segoia.eventbus.demo.status.events.PeersViewUpdateEvent;
import net.segoia.eventbus.demo.status.events.StatusAppInitEvent;
import net.segoia.eventbus.web.websocket.server.EventNodeWebsocketServerEndpoint;
import net.segoia.eventbus.web.websocket.server.WebsocketServerEventNode;

public class StatusAppWsServerNode extends WebsocketServerEventNode {
    private static Condition acceptedClientEvents = new OrCondition(LooseEventMatchCondition.build("PEER", null),
	    LooseEventMatchCondition.build("STATUS-APP", null));
    private static Condition acceptedServerEvents = new AndCondition(acceptedClientEvents,
	    new NotCondition(LooseEventMatchCondition.build("STATUS-APP", "REQUEST")));

    /**
     * The default event condition to register to a peer
     */
    private static Condition peerRegisterCond = new StrictEventMatchCondition("PEER:STATUS:UPDATED");

    /**
     * Only events satisfying this condition will be passed to the ws client
     */
    private static Condition passToWsClientCond = new NotCondition(
	    new OrCondition(LooseEventMatchCondition.buildWithCategory("REQUEST"),
		    LooseEventMatchCondition.buildWithCategory("RESPONSE"), peerRegisterCond));

    private StatusAppModel model;

    public StatusAppWsServerNode(EventNodeWebsocketServerEndpoint ws) {
	super(ws);
    }

    @Override
    protected void registerHandlers() {
	super.registerHandlers();

	addEventHandler(StatusAppInitEvent.class, (c) -> this.handleAppInit(c.getEvent()));

	addEventHandler(PeersViewUpdateEvent.class, (c) -> this.handlePeersRefresh(c.getEvent()));

	addEventHandler("PEER:STATUS:UPDATED", (c) -> {
	    if (model == null) {
		return;
	    }
	    Event event = c.getEvent();
	    PeerStatusView psv = model.getPeersData().get(event.from());

	    /* only treat update messages that come from peers we are registered to */
	    if (psv != null) {
		psv.setStatus((String) event.getParam("status"));
		super.handleEvent(event);
	    }
	});
    }

    private void handleAppInit(StatusAppInitEvent event) {
	model = event.getData().getModel();
	System.out.println(getId() + " we have " + model.getPeersData().size() + " peers");
	/* register for status update to the peers */
	registerToPeers();
    }

    private void handlePeersRefresh(PeersViewUpdateEvent event) {
	synchronized (model) {
	    /* unregister from old peers */
	    unregisterFromPeers();
	    model.setPeersData(event.getData().getPeersData());
	    /* register to new peers */
	    registerToPeers();
	}
    }

    private void registerToPeers() {
	model.getPeersData().forEach((peerId, peerView) -> {
	    PeerRegisterRequestEvent regEvent = new PeerRegisterRequestEvent(getId(), peerRegisterCond);
	    forwardTo(regEvent, peerId);
	});
    }

    private void unregisterFromPeers() {
	if (model == null) {
	    return;
	}
	model.getPeersData().forEach((peerId, peerView) -> {
	    PeerRequestUnregisterEvent ue = new PeerRequestUnregisterEvent(getId());
	    forwardTo(ue, peerId);
	});
    }

    @Override
    protected void nodeInit() {
	/* register for all events that are not requests */
	EBus.getMainNode().registerPeer(this, acceptedServerEvents);

    }

    protected void onTerminate() {
	/* unregister from peers before we go */
	unregisterFromPeers();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.segoia.eventbus.web.websocket.server.WebsocketServerEventNode#handleEvent(net.segoia.event.eventbus.Event)
     */
    @Override
    protected EventTracker handleEvent(Event event) {
	if (passToWsClientCond.test(new EventContext(event, null))) {
	    return super.handleEvent(event);
	}
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.eventbus.web.websocket.server.WebsocketServerEventNode#onWsEvent(net.segoia.event.eventbus.Event)
     */
    @Override
    public void onWsEvent(Event event) {
	EventContext ec = new EventContext(event, null);
	switch (event.getEt()) {
	/* we want to rewrite this */
	case "PEER:STATUS:UPDATED":
	    event.getForwardTo().clear();
//	    event.setForwardTo(getKnownPeers(ec));
	    forwardToAllKnown(event);
	    return;
	}

	if (acceptedClientEvents.test(ec)) {
	    super.onWsEvent(event);
	}
    }

}
