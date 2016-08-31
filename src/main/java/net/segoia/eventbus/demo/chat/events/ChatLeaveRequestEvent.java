package net.segoia.eventbus.demo.chat.events;

import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;

@EventType("PEER:CHAT:LEAVE")
public class ChatLeaveRequestEvent extends CustomEvent<ChatData>{

    public ChatLeaveRequestEvent(ChatData chatData) {
	super(ChatLeaveRequestEvent.class);
	this.data = chatData;
    }

}
