package net.segoia.eventbus.demo.chat.events;

public class ChatData {
    private String chatKey;

    public ChatData(String chatKey) {
	super();
	this.chatKey = chatKey;
    }

    /**
     * @return the chatKey
     */
    public String getChatKey() {
        return chatKey;
    }

    /**
     * @param chatKey the chatKey to set
     */
    public void setChatKey(String chatKey) {
        this.chatKey = chatKey;
    }
    
    
}
