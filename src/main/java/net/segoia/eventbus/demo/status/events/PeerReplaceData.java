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

public class PeerReplaceData {
    private String oldPeerId;
    private String newPeerId;

    public PeerReplaceData(String oldPeerId, String newPeerId) {
        super();
        this.oldPeerId = oldPeerId;
        this.newPeerId = newPeerId;
    }

    /**
     * @return the oldPeerId
     */
    public String getOldPeerId() {
        return oldPeerId;
    }

    /**
     * @return the newPeerId
     */
    public String getNewPeerId() {
        return newPeerId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Data [");
        if (oldPeerId != null)
    	builder.append("oldPeerId=").append(oldPeerId).append(", ");
        if (newPeerId != null)
    	builder.append("newPeerId=").append(newPeerId);
        builder.append("]");
        return builder.toString();
    }

}