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
package net.segoia.eventbus.demo.chat;

import java.util.LinkedHashSet;
import java.util.Set;

public class Chat {
    private String chatKey;
    private Set<String> participants = new LinkedHashSet<>();

    public Chat(String chatKey) {
	super();
	this.chatKey = chatKey;
    }

    public void addParticipant(String peerId) {
	participants.add(peerId);
    }

    public boolean removeParticipant(String peerId) {
	return participants.remove(peerId);
    }

    /**
     * @return the chatKey
     */
    public String getChatKey() {
	return chatKey;
    }

    /**
     * @return the participants
     */
    public Set<String> getParticipants() {
	return participants;
    }

    /**
     * @param participants the participants to set
     */
    public void setParticipants(Set<String> participants) {
        this.participants = participants;
    }
    
    

}
