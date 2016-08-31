package net.segoia.eventbus.demo.chat;

import java.util.LinkedHashSet;
import java.util.Set;

public class Chat {
    private String chatKey;
    private Set<String> participants = new LinkedHashSet<>();

    public Chat(String chatKey) {
	super();
	this.chatKey = chatKey;
    }

    public void addParticipant(String peerId) {
	participants.add(peerId);
    }

    public boolean removeParticipant(String peerId) {
	return participants.remove(peerId);
    }

    /**
     * @return the chatKey
     */
    public String getChatKey() {
	return chatKey;
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
