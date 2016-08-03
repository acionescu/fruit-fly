package net.segoia.eventbus.demo.status.test;

import java.net.URI;

import org.junit.Test;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.eventbus.web.websocket.client.ParallelWebsocketClientNode;

public class ClientWsTest {
    
    @Test
    public void test() throws Exception{
	
	URI uri = new URI("ws://localhost:8080/ebus/ws/eventbus");
	
	ParallelWebsocketClientNode wsProxyNode = new ParallelWebsocketClientNode(uri);
	
	StatsAppWsClientTestNode cl1 = new StatsAppWsClientTestNode();
	cl1.init();
	wsProxyNode.registerPeer(cl1, new TrueCondition());
	
	Thread.sleep(5000);
	
	wsProxyNode.terminate();
	
    }

}
