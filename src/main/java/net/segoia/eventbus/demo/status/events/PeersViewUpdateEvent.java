package net.segoia.eventbus.demo.status.events;

import java.util.Map;

import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventType;
import net.segoia.eventbus.demo.status.PeerStatusView;

@EventType("STATUS-APP:PEERS:REFRESH")
public class PeersViewUpdateEvent extends Event{
    private Map<String, PeerStatusView> peersData;
    
    public PeersViewUpdateEvent(Map<String, PeerStatusView> peersData) {
	this();
	this.peersData = peersData;
    }

    public PeersViewUpdateEvent() {
	super(PeersViewUpdateEvent.class);
    }

    /**
     * @return the peersData
     */
    public Map<String, PeerStatusView> getPeersData() {
        return peersData;
    }

    /**
     * @param peersData the peersData to set
     */
    public void setPeersData(Map<String, PeerStatusView> peersData) {
        this.peersData = peersData;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("PeersViewUpdateEvent [");
	if (peersData != null)
	    builder.append("peersData=").append(peersData);
	builder.append("]");
	return builder.toString();
    }
    
}
