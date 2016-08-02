package net.segoia.eventbus.web.websocket;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class WsRequestListener implements ServletRequestListener{

    @Override
    public void requestDestroyed(ServletRequestEvent arg0) {
	
	
    }

    @Override
    public void requestInitialized(ServletRequestEvent arg0) {
	HttpServletRequest req = (HttpServletRequest)arg0.getServletRequest();
	System.out.println(req.getSession(true).getId()+" "+ req.getProtocol()+ " "+req.getRemoteAddr());
    }

}
