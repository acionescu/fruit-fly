package net.segoia.eventbus.demo.status;

import java.util.Timer;
import java.util.TimerTask;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventTracker;
import net.segoia.event.eventbus.util.EBus;

/**
 * This is a simple agent that will emulate an user by setting as status a random quote.
 * 
 * @author adi
 *
 */
public class QuoteAsStatusAgent extends StatusAppClientAgent {

    private long statusUpdatePeriod;

    private Timer timer;

    @Override
    protected void agentInit() {
	super.agentInit();
	
	/* update status every 2 minutes */
	statusUpdatePeriod = 3000;//1000 * 60 * 2;
	
	mainNode = EBus.getMainNode();
	mainNode.registerPeer(this,new TrueCondition());
    }

    protected void start() {
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

    @Override
    public void cleanUp() {
	timer.cancel();
	System.out.println("Canceling timer");
    }


}
