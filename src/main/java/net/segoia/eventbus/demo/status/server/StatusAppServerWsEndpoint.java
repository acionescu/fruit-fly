package net.segoia.eventbus.demo.status.server;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventContext;
import net.segoia.eventbus.events.web.util.WebEventsUtil;
import net.segoia.eventbus.stats.SimpleStats;
import net.segoia.eventbus.web.websocket.server.EventNodeEndpointConfigurator;
import net.segoia.eventbus.web.websocket.server.EventNodeWebsocketServerEndpoint;
import net.segoia.eventbus.web.websocket.server.WebsocketServerEventNode;

@ServerEndpoint(value = "/ws/eventbus", configurator = EventNodeEndpointConfigurator.class)
public class StatusAppServerWsEndpoint extends EventNodeWebsocketServerEndpoint {
    /**
     * Keep a reference to the http session as well to extract client info
     */
    private HttpSession httpSession;
    
    private Event rootEvent;
    
    private SimpleStats stats = new SimpleStats();

    @Override
    protected WebsocketServerEventNode buildLocalNode() {
	return new StatusAppWsServerNode(this);
    }

    @Override
    protected void initLocalNode(WebsocketServerEventNode localNode) {
	localNode.lazyInit();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.eventbus.web.websocket.server.EventNodeWebsocketServerEndpoint#setUp(javax.websocket.Session,
     * javax.websocket.EndpointConfig)
     */
    @Override
    protected void setUp(Session session, EndpointConfig config) {
	super.setUp(session, config);
	/* get the http session reference */
	this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
    }
    
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * net.segoia.eventbus.web.websocket.server.EventNodeWebsocketServerEndpoint#buildEventFromMessage(java.lang.String)
     */
    @Override
    protected Event buildEventFromMessage(String message) {
	Event event =Event.fromJson(message,getRootEvent());
	
	return event;
    }
    
    /* (non-Javadoc)
     * @see net.segoia.eventbus.web.websocket.server.EventNodeWebsocketServerEndpoint#onEvent(net.segoia.event.eventbus.Event)
     */
    @Override
    protected void onEvent(Event event) {
	super.onEvent(event);
	stats.onEvent(new EventContext(event, null));
	if(stats.getActivityIndex() > 10) {
	    System.out.println(getLocalNodeId()+" terminating");
	    terminate();
	}
    }

    private Event getRootEvent() {
	if(this.rootEvent == null) {
	    this.rootEvent = WebEventsUtil.getRootEvent(httpSession);
	}
	return this.rootEvent;
    }

}
