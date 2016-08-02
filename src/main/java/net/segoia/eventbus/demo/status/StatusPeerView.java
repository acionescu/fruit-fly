package net.segoia.eventbus.demo.status;


/**
 * Keep a view with the last know data for a peer
 * @author adi
 *
 */
public class StatusPeerView {
    private String peerId;
    private String status;
    
    public StatusPeerView(String peerId, String status) {
	super();
	this.peerId = peerId;
	this.status = status;
    }

    /**
     * @return the peerId
     */
    public String getPeerId() {
        return peerId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((peerId == null) ? 0 : peerId.hashCode());
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	StatusPeerView other = (StatusPeerView) obj;
	if (peerId == null) {
	    if (other.peerId != null)
		return false;
	} else if (!peerId.equals(other.peerId))
	    return false;
	if (status == null) {
	    if (other.status != null)
		return false;
	} else if (!status.equals(other.status))
	    return false;
	return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("StatusPeerView [");
	if (peerId != null)
	    builder.append("peerId=").append(peerId).append(", ");
	if (status != null)
	    builder.append("status=").append(status);
	builder.append("]");
	return builder.toString();
    }
    
    
}
