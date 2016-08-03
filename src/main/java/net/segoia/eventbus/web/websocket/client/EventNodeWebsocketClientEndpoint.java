package net.segoia.eventbus.web.websocket.client;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventContext;
import net.segoia.event.eventbus.constants.EventParams;
import net.segoia.event.eventbus.constants.Events;
import net.segoia.eventbus.web.websocket.WsEndpoint;

@ClientEndpoint
public class EventNodeWebsocketClientEndpoint extends WsEndpoint{
    private ParallelClientWebsocketRelay localRelay;
    /* the id assigned by the server */
    private String remoteClientId;
    
    public EventNodeWebsocketClientEndpoint(ParallelClientWebsocketRelay localRelay) {
	super();
	this.localRelay = localRelay;
    }

    @OnOpen
    public void onOpen(Session session) {
	this.session = session;
	state = WAIT_CONNECTED;
	System.out.println("open session");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
	try {
	    Event event = Event.fromJson(message);
	    state.handleEvent(event, this);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
	System.out.println("closed " + closeReason);
    }

    @Override
    public String getLocalNodeId() {
	/* since this is a simple proxy we need to use the remote node id */
	return localRelay.getRemoteNodeId();
    }

    @Override
    public void onAccepted() {
	state = ACCEPTED;
	
    }
    
    protected void sendAuth() {
	Event event = Events.builder().ebus().peer().auth().build();
	event.addParam(EventParams.clientId, remoteClientId);
	sendEvent(event);
    }
    
    
    public static WsClientEndpointState ACCEPTED = new WsClientEndpointState() {
        
        @Override
        public void handleEvent(Event event, EventNodeWebsocketClientEndpoint wse) {
            /* replace remote id with local id */
            if(wse.remoteClientId.equals(event.to())){
        	event.to(wse.getLocalNodeId());
        	System.out.println(event.getEt()+" : "+wse.remoteClientId +" -> "+wse.getLocalNodeId());
            }
	    wse.localRelay.onLocalEvent(new EventContext(event, null));
    	
        }
    };

    public static WsClientEndpointState WAIT_CONNECTED=new WsClientEndpointState() {
        
        @Override
        public void handleEvent(Event event, EventNodeWebsocketClientEndpoint wse) {
            String et = event.getEt();
            
            switch(et) {
            case "EBUS:PEER:CONNECTED" : 
        	wse.remoteClientId = (String)event.getParam(EventParams.clientId);
        	wse.state = WAIT_AUTH_RESPONSE;
        	wse.sendAuth();
        	
            }
    	
        }
    };
    
    public static WsClientEndpointState WAIT_AUTH_RESPONSE=new WsClientEndpointState() {
        
        @Override
        public void handleEvent(Event event, EventNodeWebsocketClientEndpoint wse) {
            String et = event.getEt();
            
            switch(et) {
            case "EBUS:PEER:AUTHENTICATED" : 
        	wse.onAccepted();        	
            }
    	
        }
    };
    
}
