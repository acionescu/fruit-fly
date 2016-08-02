package net.segoia.eventbus.demo.status;

import java.util.Timer;
import java.util.TimerTask;

import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventHandle;
import net.segoia.event.eventbus.EventTracker;
import net.segoia.event.eventbus.constants.Events;
import net.segoia.event.eventbus.peers.PeerEventContext;
import net.segoia.event.eventbus.util.EBus;

/**
 * This is a simple agent that will emulate an user by setting as status a random quote.
 * 
 * @author adi
 *
 */
public class QuoteAsStatusAgent extends LocalNodeAgent {

    private long statusUpdatePeriod;

    private Timer timer;

    private String status;
    

    public QuoteAsStatusAgent() {
	super();
    }

    @Override
    protected void init() {
	mainNode = EBus.getMainNode();
	mainNode.registerPeer(this);

	/* update status every 2 minutes */
	statusUpdatePeriod = 1000 * 60 * 2;
	
	/* for now start immediately, ideally we should wait for a registered event from the main node */
	start();
    }

    private void start() {
	timer = new Timer(true);
	timer.scheduleAtFixedRate(new TimerTask() {

	    @Override
	    public void run() {
		updateStatus();
	    }
	}, 1, statusUpdatePeriod);
    }
    
    private void updateStatus() {
	setStatus(QuotesChest.getRandomQuote());
    }

    private void setStatus(String status) {
	this.status = status;
	sendStatusUpdatedEvent();
    }

    private void sendStatusUpdatedEvent() {
	EventHandle eh = Events.builder().peer().status().updated().topic(getId()).getHandle();
	if (eh.isAllowed()) {
	    eh.addParam("status", status);
	    //TODO: maybe we should post this via the main node reference
	    forwardToAll(eh.event());
	}
    }

    @Override
    public void terminate() {
	timer.cancel();
    }

    @Override
    protected void handleRemoteEvent(PeerEventContext pc) {
	/* Currently do nothing on remote events */

    }

    @Override
    protected EventTracker postInternally(Event event) {
	return null;
    }

}
