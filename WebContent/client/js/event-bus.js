var EBUS = EBUS || {

    WS : {
	/**
	 * each state may defined handlers for different event types
	 * 
	 */
	STATES : {},

	STATS : {
	    /* how many ws endpoints have been created */
	    instantiated : 0
	}
    }
};

/**
 * Defines the state of a websocket endpoint
 */
function WsState(name, handlers) {
    this.name;
    this.handlers = handlers;
}

WsState.prototype = Object.create(WsState.prototype);
WsState.prototype.constructor = WsState;

/**
 * will receive as parameter an object of this type : {event : <the event>, wse :
 * <the source ws endpoint> }
 */
WsState.prototype.handle = function(ec) {
    /* see if we have a handler */
    var h = this.handlers[ec.event.et];
    if (h) {
	/* call it */
	h(ec);
    }
    
}


EBUS.WS.STATES.OPENED = new WsState("OPENED", {
    "EBUS:PEER:CONNECTED" : function(ec) {
	
	/* set on the endpoint the id assigned by the server */
	ec.wse.remoteId=ec.event.params.clientId;

	var authEvent = {
	    et : "EBUS:PEER:AUTH",
	    params : {
		clientId : ec.event.params.clientId
	    }
	}
	ec.wse.state=EBUS.WS.STATES.CONNECTED;
	ec.wse.send(authEvent);
    }

});

EBUS.WS.STATES.CONNECTED = new WsState("CONNECTED",{
    "EBUS:PEER:AUTHENTICATED" : function(ec){
	ec.wse.state = ec.wse.activeState;
    }
});


function EventWsEndpoint(url, autoConnect, handler, activeState) {
    this.id = "WSE-" + ++EBUS.WS.STATS.instantiated;
    /* target url */
    this.url = url;

    /* ws handlers */
    this.handlers = [ this ];
    
    /**
     * This is the normal functioning state, after we were successfully accepted by the server
     */
    this.activeState=activeState;

    if (handler) {
	this.handlers.push(handler);
    }

    /* websocket instance */
    this.ws;

    /* the current state of the endpoint */
    this.state;
    
    /* the id assigned by the server */
    this.remoteId;
    
    /* internal data for this endpoint */
    this.data ={};
    
    /* the last event received */
    this.lastEvent;

    if (autoConnect) {
	this.connect();
    }

}

EventWsEndpoint.prototype = Object.create(EventWsEndpoint.prototype);

EventWsEndpoint.prototype.constructor = EventWsEndpoint;


/**
 * Starts ws connection
 */
EventWsEndpoint.prototype.connect = function() {
    if ('WebSocket' in window) {
	this.ws = new WebSocket(this.url);
    } else if ('MozWebSocket' in window) {
	this.ws = new MozWebSocket(this.url);
    } else {
	throw 'WebSocket is not supported by this browser.';
    }

    this.bindWs();
}

EventWsEndpoint.prototype.close = function() {
    if (this.ws) {
	this.ws.close();
	this.ws = null;
    }
}

EventWsEndpoint.prototype.handlersDelegate = function(funcName) {
    var that = this;
    return function() {
	that.callHandlers(funcName, arguments);
    }
}

EventWsEndpoint.prototype.callHandlers = function(funcName, args) {
    this.handlers.forEach(function(h) {
	try {
	    h[funcName].apply(h, args);
	} catch (e) {
	    this.log("error: " + e);
	}
    });
}

/**
 * adds ws handlers
 */
EventWsEndpoint.prototype.bindWs = function() {
    this.ws.onopen = this.handlersDelegate("onopen");
    this.ws.onclose = this.handlersDelegate("onclose");
    this.ws.onmessage = this.handlersDelegate("onmessage");
    this.ws.onerror = this.handlersDelegate("onerror");
}

EventWsEndpoint.prototype.log = function(message) {
    console.log(this.id + " : " + message);
}

EventWsEndpoint.prototype.send = function(event) {
    var s = JSON.stringify(event);
    this.log("sending: " + s);
    this.ws.send(s);
}

/* ws.onopen */
EventWsEndpoint.prototype.onopen = function() {
    this.state = EBUS.WS.STATES.OPENED;
    this.log("opened");
}

/* ws.onclose */
EventWsEndpoint.prototype.onclose = function(event) {
    this.log("closed -> " + event);
    
}

/* ws.onmessage */
EventWsEndpoint.prototype.onmessage = function(event) {
    this.log("receiving: "+event.data);
    this.lastEvent = JSON.parse(event.data);
    if (this.state != null) {
	this.state.handle({
	    event : this.lastEvent,
	    wse : this
	});
    }

}

/* ws.onerror */
EventWsEndpoint.prototype.onerror = function(event) {
    this.log("error: " + event);
}
