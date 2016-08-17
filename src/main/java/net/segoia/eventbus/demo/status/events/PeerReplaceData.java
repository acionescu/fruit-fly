package net.segoia.eventbus.demo.status.events;

public class PeerReplaceData {
    private String oldPeerId;
    private String newPeerId;

    public PeerReplaceData(String oldPeerId, String newPeerId) {
        super();
        this.oldPeerId = oldPeerId;
        this.newPeerId = newPeerId;
    }

    /**
     * @return the oldPeerId
     */
    public String getOldPeerId() {
        return oldPeerId;
    }

    /**
     * @return the newPeerId
     */
    public String getNewPeerId() {
        return newPeerId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Data [");
        if (oldPeerId != null)
    	builder.append("oldPeerId=").append(oldPeerId).append(", ");
        if (newPeerId != null)
    	builder.append("newPeerId=").append(newPeerId);
        builder.append("]");
        return builder.toString();
    }

}