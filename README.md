
##This is an anonymous status sharing demo app, built with [web-events](https://github.com/acionescu/web-events) and websockets

You can check the demo [here](https://fruit-fly.herokuapp.com/client/statusapp.html)

Currently, this uses a single server to route the messages between peers, but the goal is for anyone to be able to host its own
server that will act as a digital broker between their data and the peers. So instead of talking with a central server,
each client will talk with its own sever instance which in turn will talk with the server instances of its peers.

The vision is to be able to share any type of data in this manner, not just text messages.

This is a model that can pave the way for more private social networking, where the ones interested, can actually have complete
control over their data, while still being able to share it with whomever they please, without the need to trust a third party.


For now, here are the specs for the current proof of concept:


##The user 

 * User loads the page, and will be automatically connected
 
 * Sees : 
 	 * The unique id assigned for this connection
	 * A default status (e.g. "Hi, I'm visitor xyz") that can be changed
	 * 5 random peers that he/she follows
	 * The ids of the followers
	 * The last 5 most recent updates, autorefreshing every 30 seconds ( can be refreshed manually as well )
 
 * Can : 
	 * Update the status ( max 500 characters )
	 * Replace the list of peers ( get 5 random peers )
	 * Replace one of the peers with a known one 
	 * Engage in an ephemeral chat with a follower that they follow back
	 * Get the last 5 most recent updates from all the users connected
 
##The server app

* Provides :
 * 5 default agents ( bots ) that set as status an interesting quote. The agents will update their status every 2 minutes
 
* Security : 
 * The app will permit only a certain level of activity per ip and connection
 * If that is exceeded, it will close/refuse the connection  
 