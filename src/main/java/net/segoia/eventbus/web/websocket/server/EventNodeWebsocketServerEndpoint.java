package net.segoia.eventbus.web.websocket.server;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.constants.EventParams;
import net.segoia.event.eventbus.constants.Events;
import net.segoia.event.eventbus.util.EBus;
import net.segoia.eventbus.web.websocket.WsEndpoint;

@ServerEndpoint(value = "/ws/eventbus")
public class EventNodeWebsocketServerEndpoint extends WsEndpoint {
    private WebsocketServerEventNode localNode;

    @OnOpen
    public void onOpen(Session session) {
	this.session = session;

	localNode = new WebsocketServerEventNode(this);

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
	t.printStackTrace();
	localNode.terminate();
    }

    protected void sendConnectedEvent() {
	Event event = Events.builder().ebus().peer().connected().build();
	event.addParam(EventParams.clientId, localNode.getId());

	sendEvent(event);
    }

    @Override
    public String getLocalNodeId() {
	return localNode.getId();
    }

    @Override
    public void onAccepted() {
	state = ACCEPTED;
	
	sendAuthenticated();
	/* now we can register to the main node */
	EBus.getMainNode().registerPeer(localNode, new TrueCondition());

	System.out.println(session.getId());
    }
    
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
