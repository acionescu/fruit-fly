package net.segoia.eventbus.web.websocket.server;

import net.segoia.event.eventbus.peers.EventNode;
import net.segoia.event.eventbus.peers.EventRelay;

public class ServerWebsocketRelay extends EventRelay{
    private EventNodeWebsocketServerEndpoint ws;

    public ServerWebsocketRelay(String id, EventNode parentNode, EventNodeWebsocketServerEndpoint ws) {
	super(id, parentNode);
	this.ws = ws;
    }

    @Override
    protected void init() {
	// TODO Auto-generated method stub
	
    }

    @Override
    protected void start() {
	// TODO Auto-generated method stub
	
    }

    @Override
    protected void cleanUp() {
	// TODO Auto-generated method stub
	
    }
    
    

}
