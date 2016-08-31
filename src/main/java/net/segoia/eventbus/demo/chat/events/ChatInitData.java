package net.segoia.eventbus.demo.chat.events;

import java.util.Set;

public class ChatInitData extends ChatData{
    private Set<String> participants;

    public ChatInitData(String chatKey, Set<String> participants) {
	super(chatKey);
	this.participants = participants;
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
