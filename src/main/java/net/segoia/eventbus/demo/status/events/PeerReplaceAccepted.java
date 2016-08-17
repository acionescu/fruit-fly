package net.segoia.eventbus.demo.status.events;

import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;

@EventType("STATUS-APP:PEER-REPLACE:ACCEPTED")
public class PeerReplaceAccepted extends CustomEvent<PeerReplaceData>{

    public PeerReplaceAccepted(PeerReplaceData data) {
	super(PeerReplaceAccepted.class);
	this.data = data;
    }

}
