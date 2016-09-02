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
package net.segoia.eventbus.demo.status;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import net.segoia.event.eventbus.util.EBus;
import net.segoia.eventbus.demo.chat.ChatManagerAgent;

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
	
	new ChatManagerAgent();
	
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
