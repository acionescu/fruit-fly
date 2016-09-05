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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.constants.EventParams;
import net.segoia.event.eventbus.peers.AgentNode;
import net.segoia.event.eventbus.util.EBus;
import net.segoia.eventbus.demo.chat.events.ChatData;
import net.segoia.eventbus.demo.chat.events.ChatInitEvent;
import net.segoia.eventbus.demo.chat.events.ChatJoinRequestEvent;
import net.segoia.eventbus.demo.chat.events.ChatJoinedEvent;
import net.segoia.eventbus.demo.chat.events.ChatLeaveRequestEvent;
import net.segoia.eventbus.demo.chat.events.ChatLeftEvent;
import net.segoia.eventbus.demo.chat.events.ChatMessageEvent;

public class ChatManagerAgent extends AgentNode {
    /**
     * Keeps active chats by chat key
     */
    private Map<String, Chat> chats;

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.event.eventbus.peers.AgentNode#nodeInit()
     */
    @Override
    protected void nodeInit() {
	super.nodeInit();

	chats = new HashMap<>();

	mainNode = EBus.getMainNode();
	mainNode.registerPeerAsAgent(this, new TrueCondition());
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.event.eventbus.peers.AgentNode#nodeConfig()
     */
    @Override
    protected void nodeConfig() {
	super.nodeConfig();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.event.eventbus.peers.EventNode#registerHandlers()
     */
    @Override
    protected void registerHandlers() {
	super.registerHandlers();

	// addEventHandler("EBUS:PEER:NEW", (c)->{
	//
	// });

	addEventHandler("EBUS:PEER:REMOVED", (c) -> {

	    Event event = c.getEvent();
	    String peerId = (String) event.getParam(EventParams.peerId);

	    removePeerFromAllChats(peerId);

	});

	addEventHandler(ChatJoinRequestEvent.class, (c) -> {
	    ChatJoinRequestEvent event = c.event();
	    ChatData chatData = event.getData();
	    String chatKey = chatData.getChatKey();

	    Set<String> chatPartners = getChatParticipants(chatKey);

	    String newPeerId = event.from();
	    ChatJoinedEvent chatJoinedEvent = new ChatJoinedEvent(chatKey, newPeerId);

	    /* create a snapshot of the current partners */
	    Set<String> partnersSnapshot = setCopy(chatPartners);
	    /* notify the current participants of the new peer */
	    forwardTo(chatJoinedEvent, partnersSnapshot);

	    ChatInitEvent chatInitEvent = new ChatInitEvent(chatKey, partnersSnapshot);
	    /* send a chat init event to the new peer */
	    forwardTo(chatInitEvent, newPeerId);

	    /* finally, add the new peer to the participants set */
	    chatPartners.add(newPeerId);

	});

	addEventHandler(ChatLeaveRequestEvent.class, (c) -> {
	    ChatLeaveRequestEvent event = c.event();
	    removePeerFromChat(event.getData().getChatKey(), event.from(), true);
	});
    }

    private Set<String> setCopy(Set<String> source) {
	return (Set<String>) ((HashSet<String>) source).clone();
    }

    /**
     * Remove a peer from chat
     * 
     * @param chatKey
     * @param peerId
     * @param cleanUp
     * @return - true if the chat is empty ( has no more participants ) false otherwise
     */
    private boolean removePeerFromChat(String chatKey, String peerId, boolean cleanUp) {
	Set<String> ccp = getChatParticipants(chatKey);

	/* remove the peer from chat participants */
	ccp.remove(peerId);

	if (ccp.size() > 0) {
	    /* notify the chat partners that the peer left */
	    forwardTo(new ChatLeftEvent(chatKey, peerId), ccp);
	    return false;
	} else {
	    if (cleanUp) {
		/* this chat has no participants, remove it */
		removeChat(chatKey);
	    }
	    return true;
	}

    }

    private void removePeerFromAllChats(String peerId) {
	Set<String> chatsToRemove = new HashSet<>();
	chats.keySet().forEach((chatkey) -> {
	    boolean toRemove = removePeerFromChat(chatkey, peerId, false);
	    if (toRemove) {
		chatsToRemove.add(chatkey);
	    }
	});

	/* remove unused chats */
	chatsToRemove.forEach((chatKey) -> {
	    removeChat(chatKey);
	});
    }

    private Set<String> getChatParticipants(String chatKey) {
	return getChatByKey(chatKey).getParticipants();
    }

    private Chat getChatByKey(String chatKey) {
	Chat chat = chats.get(chatKey);
	if (chat == null) {
	    chat = createChatForKey(chatKey);
	    chats.put(chatKey, chat);
	}
	return chat;
    }

    private Chat createChatForKey(String chatKey) {
	return new Chat(chatKey);
    }

    private Chat removeChat(String chatKey) {
	return chats.remove(chatKey);
    }

    @Override
    public void cleanUp() {
	// TODO Auto-generated method stub

    }

    @Override
    protected void onTerminate() {
	// TODO Auto-generated method stub

    }

}
