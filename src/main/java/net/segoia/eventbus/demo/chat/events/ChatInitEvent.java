package net.segoia.eventbus.demo.chat.events;

import java.util.Set;

import net.segoia.event.eventbus.CustomEvent;

public class ChatInitEvent extends CustomEvent<ChatInitData>{

    public ChatInitEvent(ChatInitData data) {
	super(ChatInitEvent.class);
	this.data=data;
    }
    
    public ChatInitEvent(String chatKey, Set<String> participants) {
	this(new ChatInitData(chatKey, participants));
    }

}
