package net.segoia.eventbus.demo.status;

import java.util.Map;

public class StatusAppModel {

    private String clientId;
    private String status;
    private Map<String, PeerStatusView> peersData;

    public StatusAppModel(String clientId, String status, Map<String, PeerStatusView> peersData) {
	super();
	this.clientId = clientId;
	this.status = status;
	this.peersData = peersData;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
	return clientId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
	return status;
    }

    /**
     * @param clientId
     *            the clientId to set
     */
    public void setClientId(String clientId) {
	this.clientId = clientId;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
	this.status = status;
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
    
    
    public void removePeer(String peerId) {
	peersData.remove(peerId);
    }
    
    
    public void addPeer(String peerId, PeerStatusView peerView) {
	peersData.put(peerId, peerView);
    }
    

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("StatusAppModel [");
	if (clientId != null)
	    builder.append("clientId=").append(clientId).append(", ");
	if (status != null)
	    builder.append("status=").append(status).append(", ");
	if (peersData != null)
	    builder.append("peersData=").append(peersData);
	builder.append("]");
	return builder.toString();
    }
    
}
