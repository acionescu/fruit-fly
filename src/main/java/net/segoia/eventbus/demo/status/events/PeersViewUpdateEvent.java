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

import java.util.Map;

import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;
import net.segoia.eventbus.demo.status.PeerStatusView;
import net.segoia.eventbus.demo.status.events.PeersViewUpdateEvent.Data;

@EventType("STATUS-APP:PEERS:REFRESH")
public class PeersViewUpdateEvent extends CustomEvent<Data> {
    public PeersViewUpdateEvent(Map<String, PeerStatusView> peersData) {
	this();
	this.data= new Data(peersData);
    }

    public PeersViewUpdateEvent() {
	super(PeersViewUpdateEvent.class);
    }

    public class Data {

	private Map<String, PeerStatusView> peersData;
	
	

	public Data(Map<String, PeerStatusView> peersData) {
	    super();
	    this.peersData = peersData;
	}

	/**
	 * @return the peersData
	 */
	public Map<String, PeerStatusView> getPeersData() {
	    return peersData;
	}

	/**
	 * @param peersData
	 *            the peersData to set
	 */
	public void setPeersData(Map<String, PeerStatusView> peersData) {
	    this.peersData = peersData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("Data [");
	    if (peersData != null)
		builder.append("peersData=").append(peersData);
	    builder.append("]");
	    return builder.toString();
	}
	
	
    }


}
