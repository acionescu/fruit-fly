package net.segoia.eventbus.web.websocket;

import net.segoia.event.eventbus.Event;

public abstract class WsEndpointState<W extends WsEndpoint> {

    public abstract void handleEvent(Event event, W wse);

    
}
