package net.segoia.eventbus.demo.status.events;


import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;

@EventType("STATUS-APP:STATUS:ERROR")
public class StatusErrorEvent extends CustomEvent<StatusErrorData>{

    public StatusErrorEvent(String reason, String currentStatus) {
	super(StatusErrorEvent.class);
	this.data = new StatusErrorData(reason,currentStatus);
    }

}
