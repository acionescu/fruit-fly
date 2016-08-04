package net.segoia.eventbus.demo.status;

import java.util.Timer;
import java.util.TimerTask;

import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventHandle;
import net.segoia.event.eventbus.EventTracker;
import net.segoia.event.eventbus.constants.Events;
import net.segoia.event.eventbus.peers.AgentNode;
import net.segoia.event.eventbus.peers.RemoteEventContext;
import net.segoia.event.eventbus.peers.RemoteEventHandler;
import net.segoia.eventbus.demo.status.events.PeersViewUpdateEvent;
import net.segoia.eventbus.demo.status.events.RefreshPeersRequestEvent;
import net.segoia.eventbus.demo.status.events.StatusAppInitEvent;

public class StatusAppClientAgent extends AgentNode {

    protected StatusAppModel model;

    private Timer timer;

    @Override
    protected void agentInit() {
	// TODO Auto-generated method stub

    }

    @Override
    protected void registerHandlers() {
	addEventHandler(StatusAppInitEvent.class, new RemoteEventHandler<StatusAppClientAgent>() {

	    @Override
	    public void handleRemoteEvent(RemoteEventContext<StatusAppClientAgent> rec) {
		StatusAppInitEvent appInitEvent = (StatusAppInitEvent) rec.getEvent();
		model = appInitEvent.getModel();
		start();
	    }
	});

	addEventHandler(PeersViewUpdateEvent.class, new RemoteEventHandler<StatusAppClientAgent>() {

	    @Override
	    public void handleRemoteEvent(RemoteEventContext<StatusAppClientAgent> rec) {
		PeersViewUpdateEvent event = (PeersViewUpdateEvent)rec.getEvent();
		model.setPeersData(event.getPeersData());
	    }
	});

    }

    protected void start() {
	timer = new Timer(true);
	timer.scheduleAtFixedRate(new TimerTask() {

	    @Override
	    public void run() {
		requestNewPeers();
	    }
	}, 1, 5000);

	System.out.println("starting..");
    }

    protected void requestNewPeers() {
	RefreshPeersRequestEvent e = new RefreshPeersRequestEvent();
	forwardToAll(e);
    }

    protected void setStatus(String status) {
	model.setStatus(status);
	sendStatusUpdatedEvent();
    }

    private void sendStatusUpdatedEvent() {
	EventHandle eh = Events.builder().peer().status().updated().topic(getId()).getHandle();
	if (eh.isAllowed()) {
	    eh.addParam("status", model.getStatus());
	    // TODO: maybe we should post this via the main node reference
	    forwardToAll(eh.event());
	}
    }

    @Override
    public void cleanUp() {
	// TODO Auto-generated method stub

    }

    @Override
    protected EventTracker handleEvent(Event event) {

	return null;
    }

}
