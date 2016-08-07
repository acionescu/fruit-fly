package net.segoia.eventbus.demo.status.server;

import javax.websocket.server.ServerEndpoint;

import net.segoia.eventbus.web.websocket.server.EventNodeWebsocketServerEndpoint;
import net.segoia.eventbus.web.websocket.server.WebsocketServerEventNode;

@ServerEndpoint(value = "/ws/eventbus")
public class StatusAppServerWsEndpoint extends EventNodeWebsocketServerEndpoint{
    
    @Override
    protected WebsocketServerEventNode buildLocalNode() {
	return new StatusAppWsServerNode(this);
    }

    @Override
    protected void initLocalNode(WebsocketServerEventNode localNode) {
	
	localNode.lazyInit();
	
    }

}
