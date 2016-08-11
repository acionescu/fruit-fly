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
