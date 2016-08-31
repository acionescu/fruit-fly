package net.segoia.eventbus.demo.chat.events;

import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;

@EventType("PEER:CHAT:JOIN")
public class ChatJoinRequestEvent extends CustomEvent<ChatData>{

    public ChatJoinRequestEvent(ChatData chatData) {
	super(ChatJoinRequestEvent.class);
	this.data = chatData;
    }

}
