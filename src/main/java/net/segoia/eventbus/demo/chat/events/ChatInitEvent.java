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
package net.segoia.eventbus.demo.chat.events;

import java.util.Set;

import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;

@EventType("PEER:CHAT:INIT")
public class ChatInitEvent extends CustomEvent<ChatInitData>{

    public ChatInitEvent(ChatInitData data) {
	super(ChatInitEvent.class);
	this.data=data;
    }
    
    public ChatInitEvent(String chatKey, Set<String> participants) {
	this(new ChatInitData(chatKey, participants));
    }

}
