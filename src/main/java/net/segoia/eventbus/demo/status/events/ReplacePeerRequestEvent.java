package net.segoia.eventbus.demo.status.events;

import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;

@EventType(value="STATUS-APP:REQUEST:PEER-REPLACE")
public class ReplacePeerRequestEvent extends CustomEvent<PeerReplaceData> {

    public ReplacePeerRequestEvent(String oldPeerId, String newPeerId) {
	super(ReplacePeerRequestEvent.class);
	this.data=new PeerReplaceData(oldPeerId,newPeerId);
    }
}
