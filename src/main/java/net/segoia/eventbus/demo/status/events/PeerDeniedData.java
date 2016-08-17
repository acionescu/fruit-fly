package net.segoia.eventbus.demo.status.events;

public class PeerDeniedData extends PeerReplaceData{
    private String reason;

    public PeerDeniedData(PeerReplaceData data, String reason) {
	super(data.getOldPeerId(), data.getNewPeerId());
	this.reason = reason;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("PeerDeniedData [");
	if (super.toString() != null)
	    builder.append("toString()=").append(super.toString()).append(", ");
	if (reason != null)
	    builder.append("reason=").append(reason);
	builder.append("]");
	return builder.toString();
    }
    
    

}
