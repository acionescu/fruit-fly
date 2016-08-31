package net.segoia.eventbus.demo.chat.events;

import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;

@EventType("PEER:CHAT:JOINED")
public class ChatJoinedEvent extends CustomEvent<ChatPeerData>{

    public ChatJoinedEvent(ChatPeerData data) {
	super(ChatJoinedEvent.class);
	this.data=data;
    }

    public ChatJoinedEvent(String chatKey, String peerId) {
	this(new ChatPeerData(chatKey, peerId));
    }
}
