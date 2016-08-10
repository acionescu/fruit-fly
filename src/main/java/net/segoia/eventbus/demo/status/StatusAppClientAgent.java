package net.segoia.eventbus.demo.status;

import java.util.Timer;
import java.util.TimerTask;

import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventHandle;
import net.segoia.event.eventbus.constants.Events;
import net.segoia.event.eventbus.peers.AgentNode;
import net.segoia.eventbus.demo.status.events.PeersViewUpdateEvent;
import net.segoia.eventbus.demo.status.events.RefreshPeersRequestEvent;
import net.segoia.eventbus.demo.status.events.StatusAppInitEvent;

public class StatusAppClientAgent extends AgentNode {

    protected StatusAppModel model;

    private Timer timer;

    @Override
    protected void nodeInit() {
	// TODO Auto-generated method stub

    }

    @Override
    protected void registerHandlers() {
	super.registerHandlers();
	addEventHandler(StatusAppInitEvent.class, (c) -> {
	    StatusAppInitEvent appInitEvent = c.getEvent();
	    model = appInitEvent.getData().getModel();
	    start();
	});

	addEventHandler(PeersViewUpdateEvent.class, (c) -> {
	    PeersViewUpdateEvent event = c.getEvent();
	    model.setPeersData(event.getData().getPeersData());
	    System.out.println("refreshed");

	});
	
	addEventHandler("PEER:STATUS:UPDATED", (c) -> {
	    Event event = c.getEvent();
	    System.out.println(getId()+" -> "+event.from()+" : "+event.getParam("status"));
	});

    }

    protected void start() {
	System.out.println(getId()+" starting..");
	timer = new Timer(true);
	timer.scheduleAtFixedRate(new TimerTask() {

	    @Override
	    public void run() {
		requestNewPeers();
		setStatus(model.getStatus().split("-")[0]+"-just refreshed "+System.currentTimeMillis());
		
	    }
	}, 1, 6000);

	System.out.println(getId()+" started");
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
	    // forwardToAll(eh.event());

	    forwardToAllKnown(eh.event());
	}
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
