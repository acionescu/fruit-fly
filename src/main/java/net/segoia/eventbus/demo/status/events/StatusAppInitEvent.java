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
