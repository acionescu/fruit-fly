package net.segoia.eventbus.web.websocket;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import javax.websocket.Session;

import net.segoia.event.eventbus.Event;

public abstract class WsEndpoint {
    /**
     * We will use a fixed thread pool to send events to all websocket connected peers
     */
    private static ExecutorService sendThreadPool = Executors.newFixedThreadPool(10, new ThreadFactory() {

	@Override
	public Thread newThread(Runnable r) {
	    Thread th = new Thread(r);
	    th.setDaemon(true);
	    return th;
	}
    });

    protected Session session;
    
    protected WsEndpointState state;
    
    public abstract String getLocalNodeId();
    
    /**
     * Called when a peer connection is accepted
     */
    public abstract void onAccepted();
    
    public abstract void onError(Throwable e);

    public Future<Void> sendEvent(Event event) {
	WsEndpoint endpoint = this;
	return (Future<Void>) sendThreadPool.submit(new Callable<Void>() {

	    @Override
	    public Void call() throws Exception {
		synchronized (session) {
		    try {
			session.getBasicRemote().sendText(event.toJson());
		    } catch (IOException e) {
			e.printStackTrace();
			endpoint.onError(e);
		    }
		}
		return null;
	    }

	});

    }

    public void terminate() {
	if (!session.isOpen()) {
	    return;
	}

	try {
	    session.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
