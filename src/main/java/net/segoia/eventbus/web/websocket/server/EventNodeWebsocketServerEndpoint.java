package net.segoia.eventbus.web.websocket.server;

import javax.websocket.EndpointConfig;
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
    public void onOpen(Session session, EndpointConfig config) {
	setUp(session, config);
	state = CONNECTED;
	sendConnectedEvent();

    }

    @OnClose
    public void onClose(Session session) {
	System.out.println("terminating node");
	localNode.terminate();
    }

    @OnMessage
    public void onMessage(String message) {
	try {
	    Event event = buildEventFromMessage(message);
	    onEvent(event);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @OnError
    public void onError(Throwable t) {
	System.err.println("Terminating node " + getLocalNodeId() + " due to error ");
	t.printStackTrace();
	localNode.terminate();
    }

    /**
     * Set up this endpoint on open
     * 
     * @param session
     * @param config
     */
    protected void setUp(Session session, EndpointConfig config) {
	this.session = session;
	localNode = buildLocalNode();
    }

    protected Event buildEventFromMessage(String message) {
	return Event.fromJson(message);
    }

    /**
     * Called when an event is received
     * 
     * @param event
     */
    protected void onEvent(Event event) {
	/* by default delegate this to the current state */
	state.handleEvent(event, this);
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

    }

    /**
     * This is called once the client websocket connection is accepted </br>
     * Override to initialize the local event node for this connection
     * 
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
