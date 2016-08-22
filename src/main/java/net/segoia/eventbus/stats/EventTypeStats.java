package net.segoia.eventbus.stats;

import net.segoia.event.eventbus.EventContext;

public class EventTypeStats extends HierarchycalStats{

    @Override
    protected SimpleStats buildNestedStats() {
	return new SimpleStats();
    }

    @Override
    protected void processNested(EventContext ec) {
	String et = ec.event().getEt();
	getNested(et, true).onEvent(ec);
    }

}
