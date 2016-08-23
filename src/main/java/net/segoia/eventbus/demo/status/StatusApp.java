package net.segoia.eventbus.demo.status;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import net.segoia.event.eventbus.util.EBus;

@WebListener
public class StatusApp implements ServletContextListener {
    /* how many peers can a user follow */
    public static int maxPartnersPerUser = 5;
    public static int maxQuoteAgents = maxPartnersPerUser + 1;
    public static int maxStatusLength=500;

    public static StatusAppStats stats = new StatusAppStats();

    public static final String STATUS = "status";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
	
	/* start app init agent */
	new StatusAppManagerAgent();
	
	/* start the stats listener */
	new StatusAppStatsNode().init();
	

	/* start quote agents */

	for (int i = 0; i < maxQuoteAgents; i++) {
	    QuoteAsStatusAgent ag = new QuoteAsStatusAgent();
	}

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
	System.out.println("Terminating main node.");
	EBus.getMainNode().terminate();
	System.out.println("Main node terminated.");

    }

}
