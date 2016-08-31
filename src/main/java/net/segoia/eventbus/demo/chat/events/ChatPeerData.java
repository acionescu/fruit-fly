package net.segoia.eventbus.demo.chat.events;

public class ChatPeerData extends ChatData{

    private String peerId;
    
    public ChatPeerData(String chatKey, String peerId) {
	super(chatKey);
	this.peerId=peerId;
    }

    /**
     * @return the peerId
     */
    public String getPeerId() {
        return peerId;
    }

    /**
     * @param peerId the peerId to set
     */
    public void setPeerId(String peerId) {
        this.peerId = peerId;
    }
    
}
