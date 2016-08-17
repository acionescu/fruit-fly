package net.segoia.eventbus.demo.status.test;

import org.junit.Test;

import junit.framework.Assert;
import net.segoia.event.conditions.Condition;
import net.segoia.event.conditions.LooseEventMatchCondition;
import net.segoia.event.conditions.OrCondition;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.EventContext;
import net.segoia.eventbus.demo.status.events.ReplacePeerRequestEvent;

public class CondTest {

    @Test
    public void test() {
	Condition acceptedClientEvents = new OrCondition(LooseEventMatchCondition.build("PEER", null),
		LooseEventMatchCondition.build("STATUS-APP", null));
	
	ReplacePeerRequestEvent e = new ReplacePeerRequestEvent("foo", "bar");
	
	Assert.assertTrue(acceptedClientEvents.test(new EventContext(Event.fromJson(e.toJson()), null)));
	
    }

}
