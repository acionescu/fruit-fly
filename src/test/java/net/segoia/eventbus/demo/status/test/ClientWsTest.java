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
package net.segoia.eventbus.demo.status.test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.eventbus.demo.status.StatusAppClientAgent;
import net.segoia.eventbus.web.websocket.client.ParallelWebsocketClientNode;

public class ClientWsTest {

    @Test
    public void test() throws Exception {

	URI uri = new URI("ws://localhost:8080/ebus/ws/eventbus");

	ParallelWebsocketClientNode wsProxyNode = new ParallelWebsocketClientNode(uri);
	System.out.println("proxy: "+wsProxyNode.getId());
	
	List<StatusAppClientAgent> clients = new ArrayList<>();
	
	for (int i = 0; i < 200; i++) {
	    StatusAppClientAgent cl = new StatusAppClientAgent();
	    wsProxyNode.registerPeer(cl, new TrueCondition());
	    clients.add(cl);
	    Thread.sleep(100);
	}

	Thread.sleep(20000);

	wsProxyNode.terminate();
	
	
	float messagesProcessed = 0;
	for(StatusAppClientAgent c : clients) {
	    messagesProcessed += c.getStats().getEventsHandledCount();
	}

	System.out.println("Avg events processed : " +  (messagesProcessed/clients.size()));
    }

}
