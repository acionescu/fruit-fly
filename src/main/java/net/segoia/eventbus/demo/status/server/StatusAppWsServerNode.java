/**
 * fruit-fly - A status sharing demo app, built with web-events and websockets
 * Copyright (C) 2016  Adrian Cristian Ionescu - https://github.com/acionescu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.segoia.eventbus.demo.status.server;

import java.util.Map;

import net.segoia.event.conditions.AndCondition;
import net.segoia.event.conditions.Condition;
import net.segoia.event.conditions.LooseEventMatchCondition;
import net.segoia.event.conditions.NotCondition;
import net.segoia.event.conditions.OrCondition;
import net.segoia.event.conditions.StrictEventMatchCondition;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventContext;
import net.segoia.event.eventbus.EventTracker;
import net.segoia.event.eventbus.constants.EventParams;
import net.segoia.event.eventbus.constants.Events;
import net.segoia.event.eventbus.peers.events.PeerRegisterRequestEvent;
import net.segoia.event.eventbus.peers.events.PeerRegisteredEvent;
import net.segoia.event.eventbus.peers.events.PeerRequestUnregisterEvent;
import net.segoia.event.eventbus.peers.events.PeerUnregisteredEvent;
import net.segoia.event.eventbus.util.EBus;
import net.segoia.eventbus.demo.status.PeerStatusView;
import net.segoia.eventbus.demo.status.StatusApp;
import net.segoia.eventbus.demo.status.StatusAppModel;
import net.segoia.eventbus.demo.status.events.PeerReplaceAccepted;
import net.segoia.eventbus.demo.status.events.PeerReplaceData;
import net.segoia.eventbus.demo.status.events.PeerReplaceDenied;
import net.segoia.eventbus.demo.status.events.PeersViewUpdateEvent;
import net.segoia.eventbus.demo.status.events.RecentActivityEvent;
import net.segoia.eventbus.demo.status.events.ReplacePeerRequestEvent;
import net.segoia.eventbus.demo.status.events.StatusAppInitEvent;
import net.segoia.eventbus.demo.status.events.StatusErrorEvent;
import net.segoia.eventbus.web.websocket.server.EventNodeWebsocketServerEndpoint;
import net.segoia.eventbus.web.websocket.server.WebsocketServerEventNode;

public class StatusAppWsServerNode extends WebsocketServerEventNode {
    private static Condition acceptedClientEvents = new OrCondition(LooseEventMatchCondition.build("PEER", null),
	    LooseEventMatchCondition.build("STATUS-APP", null));
    private static Condition acceptedServerEvents = new AndCondition(
	    new OrCondition(acceptedClientEvents, LooseEventMatchCondition.build("EBUS", null)),
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
		super.handleServerEvent(event);
	    }
	});

	/* if one of the peers we are registered to leaves, notify the client */
	addEventHandler("EBUS:PEER:REMOVED", (c) -> {
	    Event event = c.event();
	    String peerId = (String) event.getParam(EventParams.peerId);
	    if (model.getPeersData().containsKey(peerId)) {
		super.handleServerEvent(event);
	    }

	});

	addEventHandler(PeerReplaceAccepted.class, (c) -> {
	    /* make sure we unregister from the old peer, and register to the new one */
	    PeerReplaceData data = c.getEvent().getData();

	    String oldPeerId = data.getOldPeerId();
	    String newPeerId = data.getNewPeerId();

	    model.getPeersData().remove(oldPeerId);
	    model.addPeer(newPeerId, new PeerStatusView(newPeerId, ""));

	    unregisterFromPeer(oldPeerId);
	    registerToPeer(newPeerId);

	});
	
	addEventHandler(RecentActivityEvent.class, (c)->{
	    if(getId().equals(c.event().to())) {
		super.handleServerEvent(c.event());
	    }
	});

	// /* when we unregister from a peer */
	// addEventHandler(PeerUnregisteredEvent.class, (c) -> {
	// PeerUnregisteredEvent event = c.event();
	//
	// /* handle events targeting us */
	// if (!getId().equals(event.getData().getPeerId())) {
	// return;
	// }
	//
	// model.removePeer(event.from());
	// });

	// addEventHandler(PeerRegisteredEvent.class, (c) -> {
	// PeerRegisteredEvent event = c.event();
	//
	// /* handle only events that targets us */
	// if (!getId().equals(event.getData().getPeerId())) {
	// return;
	// }
	// String from = event.from();
	// model.addPeer(from, new PeerStatusView(from, ""));
	// });

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.event.eventbus.peers.EventNode#onPeerRegistered(java.lang.String)
     */
    @Override
    protected void onPeerRegistered(PeerRegisteredEvent event) {
	super.onPeerRegistered(event);
	String peerId = event.getData().getPeerId();
	
	model.addFollower(peerId);

	/* sent a status update to newly registered peers */

	forwardTo(getStatusUpdatedEvent(), peerId);
	
	/* notify the client that a peer registered */
	super.handleServerEvent(event);

    }
    

    /* (non-Javadoc)
     * @see net.segoia.event.eventbus.peers.EventNode#onPeerUnregistered(net.segoia.event.eventbus.peers.events.PeerUnregisteredEvent)
     */
    @Override
    protected void onPeerUnregistered(PeerUnregisteredEvent event) {
	super.onPeerUnregistered(event);
	
	String peerId = event.getData().getPeerId();
	model.removeFollower(peerId);
	
	/* notify the client that a peer unregistered */
	super.handleServerEvent(event);
    }

    private Event getStatusUpdatedEvent() {
	Event event = Events.builder().peer().status().updated().topic(getId()).build();
	event.addParam("status", model.getStatus());
	return event;
    }

    private void handleAppInit(StatusAppInitEvent event) {
	model = event.getData().getModel();
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
	    registerToPeer(peerId);
	});
    }

    private void unregisterFromPeers() {
	if (model == null) {
	    return;
	}
	model.getPeersData().forEach((peerId, peerView) -> {
	    unregisterFromPeer(peerId);
	});
    }

    private void registerToPeer(String peerId) {
	PeerRegisterRequestEvent regEvent = new PeerRegisterRequestEvent(getId(), peerRegisterCond);
	forwardTo(regEvent, peerId);
    }

    private void unregisterFromPeer(String peerId) {
	PeerRequestUnregisterEvent ue = new PeerRequestUnregisterEvent(getId());
	forwardTo(ue, peerId);
    }

    @Override
    protected void nodeInit() {
	super.nodeInit();

	/* add handlers for events coming from the ws client */

	wsEventsBus.addEventHandler("PEER:STATUS:UPDATED", (c) -> {
	    Event event = c.event();
	    String status = (String) event.getParam(StatusApp.STATUS);

	    /* don't let status be null or empty */
	    if (status == null || status.isEmpty()) {
		super.handleServerEvent(new StatusErrorEvent("The status can't be null or empty",model.getStatus()));
	    }
	    /* don't let status exceed the max length */
	    else if (status.length() > StatusApp.maxStatusLength) {
		super.handleServerEvent(
			new StatusErrorEvent("The status can't exceed " + StatusApp.maxStatusLength + " characters.",model.getStatus()));
	    } else {
		model.setStatus(status);
		/* forward the status to the registered peers */
		event.getForwardTo().clear();
		forwardToAllKnown(event);
	    }
	    /* we don't want further processing */
	    event.setHandled();
	});

	wsEventsBus.addEventHandler(ReplacePeerRequestEvent.class, (c) -> {
	    ReplacePeerRequestEvent event = c.event();

	    PeerReplaceData replaceData = event.getData();
	    String newPeerId = replaceData.getNewPeerId();
	    String oldPeerId = replaceData.getOldPeerId();

	    Map<String, PeerStatusView> peersData = model.getPeersData();

	    /* basic validation */
	    if (newPeerId == null || oldPeerId == null || newPeerId.isEmpty() || oldPeerId.isEmpty()) {
		super.handleServerEvent(new PeerReplaceDenied(replaceData, "Peers data can't be null or empty"));
	    }
	    /* check if we really are subscribed to the peer we want to replace */
	    else if (!peersData.containsKey(oldPeerId)) {
		super.handleServerEvent(
			new PeerReplaceDenied(replaceData, "You can't replace a peer you're not subscribed to"));

	    } else if (newPeerId.equals(oldPeerId)) {
		super.handleServerEvent(new PeerReplaceDenied(replaceData, "Replacing a peer with itself is futile"));

	    }
	    /* don't allow subscribing to the same peer twice */

	    else if (peersData.containsKey(newPeerId)) {
		super.handleServerEvent(new PeerReplaceDenied(replaceData, "Already subscribed to peer"));

	    }
	    /* finally is this request if valid, forward it to the other nodes */
	    else {
		forwardToAll(event);
	    }

	    /* we don't want further processing */
	    event.setHandled();

	});
	
	
	
	
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
    protected EventTracker handleServerEvent(Event event) {
	if (passToWsClientCond.test(new EventContext(event, null))) {
	    return super.handleServerEvent(event);
	}
	return null;
    }

    @Override
    public void handleWsEvent(Event event) {
	EventContext ec = new EventContext(event, null);

	/* if the event is not handled and accepted do the default action */
	if (!ec.event().isHandled() && acceptedClientEvents.test(ec)) {
	    super.handleWsEvent(event);
	} 
    }

}
