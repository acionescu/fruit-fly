package net.segoia.eventbus.web.websocket;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.segoia.event.conditions.TrueCondition;
import net.segoia.event.eventbus.Event;
import net.segoia.event.eventbus.util.EBus;

@ServerEndpoint(value = "/ws/eventbus")
public class EventBusWebsocketEndpoint {
    private Session session;
    private WebsocketEventBusNode localNode;

    /**
     * We will use a fixed thread pool to send events to all websocket connected peers
     */
    private static ExecutorService sendThreadPool = Executors.newFixedThreadPool(10,new ThreadFactory() {
        
        @Override
        public Thread newThread(Runnable r) {
    	Thread th = new Thread(r);
    	th.setDaemon(true);
	return th;
        }
    });

    @OnOpen
    public void onOpen(Session session) {
	this.session = session;

	localNode = new WebsocketEventBusNode(this);

	EBus.getMainNode().registerPeer(localNode, new TrueCondition());
	
	System.out.println(session.getId());
    }

    @OnClose
    public void onClose() {
	EBus.getMainNode().unregisterPeer(localNode);
    }

    @OnMessage
    public void onMessage(String message) {
	try {
	    Event event = Event.fromJson(message);
	    localNode.onWsEvent(event);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @OnError
    public void onError(Throwable t) {
	t.printStackTrace();
    }

    public Future<Void> sendEvent(Event event) {
	return (Future<Void>) sendThreadPool.submit(new Callable<Void>() {

	    @Override
	    public Void call() throws Exception {
		synchronized (session) {
		    try {
			session.getBasicRemote().sendText(event.toJson());
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
		return null;
	    }

	});

    }

}
