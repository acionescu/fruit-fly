/**
 * fruit-fly - A status sharing demo app, built with web-events and websockets
 * Copyright (C) 2016  Adrian Cristian Ionescu - https://github.com/acionescu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
