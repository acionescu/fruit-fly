package net.segoia.eventbus.demo.chat.events;

import net.segoia.event.eventbus.CustomEvent;
import net.segoia.event.eventbus.EventType;

@EventType("PEER:CHAT:MESSAGE")
public class ChatMessageEvent extends CustomEvent<ChatMessage>{

    public ChatMessageEvent(ChatMessage message) {
	super(ChatMessageEvent.class);
	this.data = message;
    }

}
