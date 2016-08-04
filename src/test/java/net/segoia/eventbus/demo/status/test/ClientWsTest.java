package net.segoia.eventbus.demo.status.test;

import java.net.URI;

import org.junit.Test;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.eventbus.demo.status.StatusAppClientAgent;
import net.segoia.eventbus.web.websocket.client.ParallelWebsocketClientNode;

public class ClientWsTest {

    @Test
    public void test() throws Exception {

	URI uri = new URI("ws://localhost:8080/ebus/ws/eventbus");

	ParallelWebsocketClientNode wsProxyNode = new ParallelWebsocketClientNode(uri);

	for (int i = 0; i < 2; i++) {
	    StatusAppClientAgent cl = new StatusAppClientAgent();
	    wsProxyNode.registerPeer(cl, new TrueCondition());
	    
	    Thread.sleep(10);
	}

	Thread.sleep(20000);

	wsProxyNode.terminate();

    }

}
