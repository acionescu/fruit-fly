function StatusAppClient(url, autoConnect, handler, activeState) {
    EventWsEndpoint.call(this, url, autoConnect, handler, activeState);
}

StatusAppClient.prototype = Object.create(EventWsEndpoint.prototype);

StatusAppClient.prototype.constructor = StatusAppClient;

StatusAppClient.prototype.setStatus = function(status) {
    this.send({
	et : "PEER:STATUS:UPDATED",
	params : {
	    status : status
	}
    })
}

StatusAppClient.prototype.requestPeersRefresh = function() {
    this.send({
	et : "STATUS-APP:REQUEST:REFRESH-PEERS"
    });
}

StatusAppClient.prototype.requestPeerReplace = function(oldPeerId, newPeerId) {
    this.send({
	et : "STATUS-APP:REQUEST:PEER-REPLACE",
	data : {
	    oldPeerId : oldPeerId,
	    newPeerId : newPeerId
	}
    });
}

StatusAppClient.prototype.getRecentActivity = function() {
    this.send({
	et : "STATUS-APP:REQUEST:GET-RECENT-ACTIVITY"
    });
}

StatusAppClient.prototype.joinChat = function(chatKey) {
    this.send({
	et : "PEER:CHAT:JOIN",
	data : {
	    chatKey : chatKey
	}
    });
}

StatusAppClient.prototype.sendChatMessage = function(chatKey, message) {
    this.send({
	et : "PEER:CHAT:MESSAGE",
	data : {
	    chatKey : chatKey,
	    message: message
	}
    });
}
