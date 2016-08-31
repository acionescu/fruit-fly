package net.segoia.eventbus.demo.chat.events;

import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;

@EventType("PEER:CHAT:LEFT")
public class ChatLeftEvent extends CustomEvent<ChatPeerData>{

    public ChatLeftEvent(ChatPeerData data) {
	super(ChatLeftEvent.class);
	this.data=data;
    }
    
    public ChatLeftEvent(String chatKey, String peerId) {
	this(new ChatPeerData(chatKey, peerId));
    }

}
