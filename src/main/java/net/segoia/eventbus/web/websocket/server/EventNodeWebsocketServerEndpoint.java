package net.segoia.eventbus.web.websocket.server;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.constants.EventParams;
import net.segoia.event.eventbus.constants.Events;
import net.segoia.eventbus.web.websocket.WsEndpoint;

public abstract class EventNodeWebsocketServerEndpoint extends WsEndpoint {
    private WebsocketServerEventNode localNode;

    @OnOpen
    public void onOpen(Session session) {
	this.session = session;

	localNode = buildLocalNode();

	state = CONNECTED;
	sendConnectedEvent();

    }

    @OnClose
    public void onClose() {
	localNode.terminate();
    }

    @OnMessage
    public void onMessage(String message) {
	try {
	    Event event = Event.fromJson(message);
	    
	    state.handleEvent(event, this);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @OnError
    public void onError(Throwable t) {
	System.err.println("Terminating node "+getLocalNodeId() + " due to error ");
	t.printStackTrace();
	localNode.terminate();
    }

    protected void sendConnectedEvent() {
	Event event = Events.builder().ebus().peer().connected().build();
	event.addParam(EventParams.clientId, localNode.getId());

	sendEvent(event);
    }

    protected abstract WebsocketServerEventNode buildLocalNode();
    
    @Override
    public String getLocalNodeId() {
	return localNode.getId();
    }

    @Override
    public void onAccepted() {
	state = ACCEPTED;
	
	sendAuthenticated();
	/* now we can init the local node */

	initLocalNode(localNode);

	System.out.println(session.getId());
    }
    
    /**
     * This is called once the client websocket connection is accepted
     * </br>
     * Override to initialize the local event node for this connection
     * @param localNode
     */
    protected abstract void initLocalNode(WebsocketServerEventNode localNode);
    
    public void sendAuthenticated() {
	Event event = Events.builder().ebus().peer().authenticated().build();
	sendEvent(event);
    }

    /* STATES */

    public static WsServerEndpointState CONNECTED = new WsServerEndpointState() {

	@Override
	public void handleEvent(Event event, EventNodeWebsocketServerEndpoint wse) {
	    String et = event.getEt();

	    switch (et) {
	    case "EBUS:PEER:AUTH":
		String clientId = (String) event.getParam(EventParams.clientId);
		if (wse.getLocalNodeId().equals(clientId)) {
		    wse.onAccepted();
		} else {
		    Event error = Events.builder().peer().error().auth().build();
		    error.addParam(EventParams.reason,
			    new AuthError(EventParams.clientId, wse.getLocalNodeId(), clientId));
		    wse.sendEvent(error);
		}
		break;
	    }

	}
    };

    public static WsServerEndpointState ACCEPTED = new WsServerEndpointState() {

	@Override
	public void handleEvent(Event event, EventNodeWebsocketServerEndpoint wse) {
	    wse.localNode.onWsEvent(event);
	}
    };

}
