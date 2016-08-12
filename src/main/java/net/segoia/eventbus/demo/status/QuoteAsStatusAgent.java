package net.segoia.eventbus.demo.status;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.util.EBus;

/**
 * This is a simple agent that will emulate an user by setting as status a random quote.
 * 
 * @author adi
 *
 */
public class QuoteAsStatusAgent extends StatusAppClientAgent {

    @Override
    protected void nodeInit() {
	super.nodeInit();

	/* update status every 2 minutes */
	stateRefreshPeriod = 30000;//1000 * 60 * 2;

	mainNode = EBus.getMainNode();
	mainNode.registerPeer(this, new TrueCondition());
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.eventbus.demo.status.StatusAppClientAgent#registerHandlers()
     */
    @Override
    protected void registerHandlers() {
	super.registerHandlers();
    }

    private void updateStatus() {
	setStatus(QuotesChest.getRandomQuote());
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.eventbus.demo.status.StatusAppClientAgent#updateState()
     */
    @Override
    protected void updateState() {
	updateStatus();
    }

}
