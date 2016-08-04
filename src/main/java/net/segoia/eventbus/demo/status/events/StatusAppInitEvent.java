package net.segoia.eventbus.demo.status.events;

import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventType;
import net.segoia.eventbus.demo.status.StatusAppModel;

@EventType(value="STATUS-APP:PEER:INIT")
public class StatusAppInitEvent extends Event{
    private StatusAppModel model;

    public StatusAppInitEvent(StatusAppModel model) {
	super(StatusAppInitEvent.class);
	this.model = model;
    }

    /**
     * @return the model
     */
    public StatusAppModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(StatusAppModel model) {
        this.model = model;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("StatusAppInitEvent [");
	if (super.toString() != null)
	    builder.append("toString()=").append(super.toString()).append(", ");
	if (model != null)
	    builder.append("model=").append(model);
	builder.append("]");
	return builder.toString();
    }

    
    

}
