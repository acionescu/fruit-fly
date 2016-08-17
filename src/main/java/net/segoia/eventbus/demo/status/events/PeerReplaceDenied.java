package net.segoia.eventbus.demo.status.events;

import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;

@EventType("STATUS-APP:PEER-REPLACE:DENIED")
public class PeerReplaceDenied extends CustomEvent<PeerDeniedData>{

    public PeerReplaceDenied(PeerReplaceData data,String reason) {
	super(PeerReplaceDenied.class);
	this.data = new PeerDeniedData(data, reason);
    }

}
