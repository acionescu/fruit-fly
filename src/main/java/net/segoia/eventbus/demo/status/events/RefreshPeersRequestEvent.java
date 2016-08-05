package net.segoia.eventbus.demo.status.events;

import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;

@EventType(value="STATUS-APP:REQUEST:REFRESH-PEERS")
public class RefreshPeersRequestEvent extends CustomEvent<Object>{

    public RefreshPeersRequestEvent() {
	super(RefreshPeersRequestEvent.class);
    }

}
