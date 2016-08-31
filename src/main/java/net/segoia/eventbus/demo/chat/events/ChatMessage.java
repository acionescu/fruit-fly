package net.segoia.eventbus.demo.chat.events;

public class ChatMessage extends ChatData{
    private String message;

    
    public ChatMessage(String chatKey, String message) {
	super(chatKey);
	this.message = message;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
