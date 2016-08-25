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
