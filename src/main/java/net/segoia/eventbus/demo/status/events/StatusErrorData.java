package net.segoia.eventbus.demo.status.events;

import net.segoia.event.eventbus.peers.events.ErrorData;

public class StatusErrorData extends ErrorData{
    private String currentStatus;

    public StatusErrorData(String reason, String currentStatus) {
	super(reason);
	this.currentStatus = currentStatus;
    }

    /**
     * @return the currentStatus
     */
    public String getCurrentStatus() {
        return currentStatus;
    }

    /**
     * @param currentStatus the currentStatus to set
     */
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("StatusErrorData [");
	if (super.toString() != null)
	    builder.append("toString()=").append(super.toString()).append(", ");
	if (currentStatus != null)
	    builder.append("currentStatus=").append(currentStatus);
	builder.append("]");
	return builder.toString();
    }
}
