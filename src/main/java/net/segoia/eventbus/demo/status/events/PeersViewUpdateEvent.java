package net.segoia.eventbus.demo.status.events;

import java.util.Map;

import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;
import net.segoia.eventbus.demo.status.PeerStatusView;
import net.segoia.eventbus.demo.status.events.PeersViewUpdateEvent.Data;

@EventType("STATUS-APP:PEERS:REFRESH")
public class PeersViewUpdateEvent extends CustomEvent<Data> {
    public PeersViewUpdateEvent(Map<String, PeerStatusView> peersData) {
	this();
	this.data= new Data(peersData);
    }

    public PeersViewUpdateEvent() {
	super(PeersViewUpdateEvent.class);
    }

    public class Data {

	private Map<String, PeerStatusView> peersData;
	
	

	public Data(Map<String, PeerStatusView> peersData) {
	    super();
	    this.peersData = peersData;
	}

	/**
	 * @return the peersData
	 */
	public Map<String, PeerStatusView> getPeersData() {
	    return peersData;
	}

	/**
	 * @param peersData
	 *            the peersData to set
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
	    builder.append("Data [");
	    if (peersData != null)
		builder.append("peersData=").append(peersData);
	    builder.append("]");
	    return builder.toString();
	}
	
	
    }


}
