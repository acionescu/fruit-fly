package net.segoia.eventbus.demo.chat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.constants.EventParams;
import net.segoia.event.eventbus.peers.AgentNode;
import net.segoia.event.eventbus.util.EBus;
import net.segoia.eventbus.demo.chat.events.ChatData;
import net.segoia.eventbus.demo.chat.events.ChatInitEvent;
import net.segoia.eventbus.demo.chat.events.ChatJoinRequestEvent;
import net.segoia.eventbus.demo.chat.events.ChatJoinedEvent;
import net.segoia.eventbus.demo.chat.events.ChatLeaveRequestEvent;
import net.segoia.eventbus.demo.chat.events.ChatLeftEvent;
import net.segoia.eventbus.demo.chat.events.ChatMessageEvent;

public class ChatManagerAgent extends AgentNode {
    /**
     * Keeps active chats by chat key
     */
    private Map<String, Chat> chats;

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.event.eventbus.peers.AgentNode#nodeInit()
     */
    @Override
    protected void nodeInit() {
	super.nodeInit();

	chats = new HashMap<>();
	
	mainNode = EBus.getMainNode();
	mainNode.registerPeerAsAgent(this, new TrueCondition());
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.event.eventbus.peers.AgentNode#nodeConfig()
     */
    @Override
    protected void nodeConfig() {
	super.nodeConfig();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.segoia.event.eventbus.peers.EventNode#registerHandlers()
     */
    @Override
    protected void registerHandlers() {
	super.registerHandlers();

	// addEventHandler("EBUS:PEER:NEW", (c)->{
	//
	// });

	addEventHandler("EBUS:PEER:REMOVED", (c) -> {

	    Event event = c.getEvent();
	    String peerId = (String) event.getParam(EventParams.peerId);

	    removePeerFromAllChats(peerId);

	});

	addEventHandler(ChatJoinRequestEvent.class, (c) -> {
	    ChatJoinRequestEvent event = c.event();
	    ChatData chatData = event.getData();
	    String chatKey = chatData.getChatKey();

	    Set<String> chatPartners = getChatParticipants(chatKey);

	    String newPeerId = event.from();
	    ChatJoinedEvent chatJoinedEvent = new ChatJoinedEvent(chatKey, newPeerId);

	    /* create a snapshot of the current partners */
	    Set<String> partnersSnapshot = setCopy(chatPartners);
	    /* notify the current participants of the new peer */
	    forwardTo(chatJoinedEvent, partnersSnapshot);

	    ChatInitEvent chatInitEvent = new ChatInitEvent(chatKey, partnersSnapshot);
	    /* send a chat init event to the new peer */
	    forwardTo(chatInitEvent, newPeerId);
	    
	    /* finally, add the new peer to the participants set */
	    chatPartners.add(newPeerId);

	});

	addEventHandler(ChatLeaveRequestEvent.class, (c) -> {
	    ChatLeaveRequestEvent event = c.event();
	    removePeerFromChat(event.getData().getChatKey(), event.from());
	});
    }

    private Set<String> setCopy(Set<String> source) {
	return (Set<String>) ((HashSet<String>) source).clone();
    }

    private void removePeerFromChat(String chatKey, String peerId) {
	Set<String> ccp = getChatParticipants(chatKey);

	/* remove the peer from chat participants */
	ccp.remove(peerId);

	if (ccp.size() > 0) {
	    /* notify the chat partners that the peer left */
	    forwardTo(new ChatLeftEvent(chatKey, peerId), ccp);
	} else {
	    /* this chat has no participants, remove it */
	    removeChat(chatKey);
	}

    }

    private void removePeerFromAllChats(String peerId) {
	chats.keySet().forEach((chatkey) -> {
	    removePeerFromChat(chatkey, peerId);
	});
    }

    private Set<String> getChatParticipants(String chatKey) {
	return getChatByKey(chatKey).getParticipants();
    }

    private Chat getChatByKey(String chatKey) {
	Chat chat = chats.get(chatKey);
	if (chat == null) {
	    chat = createChatForKey(chatKey);
	    chats.put(chatKey, chat);
	}
	return chat;
    }

    private Chat createChatForKey(String chatKey) {
	return new Chat(chatKey);
    }

    private Chat removeChat(String chatKey) {
	return chats.remove(chatKey);
    }

    @Override
    public void cleanUp() {
	// TODO Auto-generated method stub

    }

    @Override
    protected void onTerminate() {
	// TODO Auto-generated method stub

    }

}
