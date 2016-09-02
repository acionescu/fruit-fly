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
