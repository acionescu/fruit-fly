function StressAgent(url) {
    StatusAppClient.call(this, url, false, null, new WsState("", {}));

    this.interval;

    this.updates = 0;
}

StressAgent.prototype = Object.create(StatusAppClient.prototype);
StressAgent.prototype.constructor = StressAgent;

StressAgent.prototype.start = function(delay) {
    this.connect();

    var self = this;
    this.interval = setInterval(function() {
	self.act();
    }, delay);

}

StressAgent.prototype.stop = function() {
    clearInterval(this.interval);
}

StressAgent.prototype.act = function() {
    this.log("acting");
    if (this.active) {
	/* just send a status update */
	this.setStatus("Status update " + this.updates++);
    }
}