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
package net.segoia.eventbus.demo.status;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class StatusAppModel {

    private String clientId;
    private String status;
    private Map<String, PeerStatusView> peersData;
    private Set<String> followers= new LinkedHashSet<>();

    public StatusAppModel(String clientId, String status, Map<String, PeerStatusView> peersData) {
	super();
	this.clientId = clientId;
	this.status = status;
	this.peersData = peersData;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
	return clientId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
	return status;
    }

    /**
     * @param clientId
     *            the clientId to set
     */
    public void setClientId(String clientId) {
	this.clientId = clientId;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
	this.status = status;
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
    
    
    public void removePeer(String peerId) {
	peersData.remove(peerId);
    }
    
    
    public void addPeer(String peerId, PeerStatusView peerView) {
	peersData.put(peerId, peerView);
    }
    
    public void addFollower(String peerId) {
	followers.add(peerId);
    }
    
    public void removeFollower(String peerId) {
	followers.remove(peerId);
    }

    /**
     * @return the followers
     */
    public Set<String> getFollowers() {
        return followers;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("StatusAppModel [");
	if (clientId != null)
	    builder.append("clientId=").append(clientId).append(", ");
	if (status != null)
	    builder.append("status=").append(status).append(", ");
	if (peersData != null)
	    builder.append("peersData=").append(peersData).append(", ");
	if (followers != null)
	    builder.append("followers=").append(followers);
	builder.append("]");
	return builder.toString();
    }

    
    
}
