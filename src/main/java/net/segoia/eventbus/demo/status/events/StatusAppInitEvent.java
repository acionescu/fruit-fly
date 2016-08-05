package net.segoia.eventbus.demo.status.events;

import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;
import net.segoia.eventbus.demo.status.StatusAppModel;
import net.segoia.eventbus.demo.status.events.StatusAppInitEvent.Data;

@EventType(value = "STATUS-APP:PEER:INIT")
public class StatusAppInitEvent extends CustomEvent<Data> {
    

    public StatusAppInitEvent(StatusAppModel model) {
	super(StatusAppInitEvent.class);
	this.data=new Data(model);
    }

    public class Data {
	private StatusAppModel model;
	
	public Data(StatusAppModel model) {
	    super();
	    this.model = model;
	}

	/**
	 * @return the model
	 */
	public StatusAppModel getModel() {
	    return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(StatusAppModel model) {
	    this.model = model;
	}
    }

   
}
