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

import net.segoia.event.conditions.EventClassMatchCondition;
import net.segoia.event.conditions.LooseEventMatchCondition;
import net.segoia.event.conditions.NotCondition;
import net.segoia.event.eventbus.EventContext;
import net.segoia.event.eventbus.EventListener;
import net.segoia.event.eventbus.FilteringEventBus;
import net.segoia.event.eventbus.SimpleEventDispatcher;
import net.segoia.event.eventbus.util.EBus;
import net.segoia.eventbus.events.web.util.WebEventsUtil;
import net.segoia.eventbus.web.stats.events.SimpleStatsEvent;

public class StatusAppStatsNode implements EventListener {
    private FilteringEventBus internalBus;

    @Override
    public void onEvent(EventContext ec) {
	internalBus.postEvent(ec.event());
    }

    @Override
    public void init() {
	/* spawn an internal bus on a separate thread */
	internalBus = EBus.buildAsyncFilteringEventBus(100, 1, new SimpleEventDispatcher());
	// internalBus = EBus.buildFilteringEventBusOnMainLoop(new SimpleEventDispatcher());

	EBus.instance().registerListener(this, 9999);

	internalBus.addEventHandler(LooseEventMatchCondition.build("HTTP", "REQUEST"), (c) -> {
	    StatusApp.stats.onHttpRequest(c);
	});

	internalBus.addEventHandler("HTTP:SESSION:INITIALIZED", (c) -> {
	    StatusApp.stats.onSessionInit(c);
	});

	internalBus.addEventHandler("HTTP:SESSION:TERMINATED", (c) -> {
	    StatusApp.stats.onSessionTerminated(c);
	});

	internalBus.addEventHandler(new NotCondition(new EventClassMatchCondition(SimpleStatsEvent.class)), (c) -> {
	    String ip = (String) c.event().getParamUpToDepth(WebEventsUtil.REMOTE_ADDR, 2);
	    /* we're only interested in events coming from a remote peer */
	    if (ip == null) {
		return;
	    }
	    StatusApp.stats.onEvent(c);
	});

    }

    @Override
    public void terminate() {
	internalBus.stop();
    }

}
