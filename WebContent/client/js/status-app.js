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

StatusAppClient.prototype.requestPeersRefresh = function(){
    this.send({
	et : "STATUS-APP:REQUEST:REFRESH-PEERS"
    });
}

StatusAppClient.prototype.requestPeerReplace = function(oldPeerId, newPeerId){
    this.send({
	et : "STATUS-APP:REQUEST:PEER-REPLACE",
	data : {
	    oldPeerId : oldPeerId,
	    newPeerId : newPeerId
	}
    });
}