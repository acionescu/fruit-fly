package net.segoia.eventbus.demo.status.server;

import net.segoia.event.conditions.AndCondition;
import net.segoia.event.conditions.Condition;
import net.segoia.event.conditions.LooseEventMatchCondition;
import net.segoia.event.conditions.NotCondition;
import net.segoia.event.eventbus.util.EBus;
import net.segoia.eventbus.web.websocket.server.EventNodeWebsocketServerEndpoint;
import net.segoia.eventbus.web.websocket.server.WebsocketServerEventNode;

public class StatusAppWsServerNode extends WebsocketServerEventNode {
    private static Condition defaultEventsCond = new AndCondition(LooseEventMatchCondition.build("STATUS-APP", null),
	    new NotCondition(LooseEventMatchCondition.buildWithCategory("REQUEST")));

    public StatusAppWsServerNode(EventNodeWebsocketServerEndpoint ws) {
	super(ws);
    }

    @Override
    protected void registerHandlers() {
	// TODO Auto-generated method stub

    }

    @Override
    protected void agentInit() {
	/* register for all events that are not requests */
	EBus.getMainNode().registerPeer(this, defaultEventsCond);

    }

}
