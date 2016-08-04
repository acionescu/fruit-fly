package net.segoia.eventbus.demo.status.events;

import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventType;

@EventType(value="STATUS-APP:REQUEST:REFRESH-PEERS")
public class RefreshPeersRequestEvent extends Event{

    public RefreshPeersRequestEvent() {
	super(RefreshPeersRequestEvent.class);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("RefreshPeersRequestEvent [");
	if (super.toString() != null)
	    builder.append("toString()=").append(super.toString());
	builder.append("]");
	return builder.toString();
    }
    
    

}
