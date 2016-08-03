package net.segoia.eventbus.web.websocket;

import net.segoia.event.eventbus.Event;

public class WsEndpointEventContext {
    public WsEndpoint wse;
    public Event event;

    public WsEndpointEventContext(WsEndpoint wse, Event event) {
	super();
	this.wse = wse;
	this.event = event;
    }

}
