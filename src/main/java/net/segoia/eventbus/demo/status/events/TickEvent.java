package net.segoia.eventbus.demo.status.events;

import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;

@EventType("EVENT:UTIL:TICK")
public class TickEvent extends CustomEvent<String>{

    public TickEvent(String type) {
	super(TickEvent.class);
	this.data = type;
    }

}
