
##This is a status sharing demo app, built with [web-events](https://github.com/acionescu/web-events) and websockets

Here are the specs:


##The user 

 * User loads the page
 * Hits Connect button
 * Sees a widget with: 
	 * His defaults status (e.g. "Hi, I'm Guest_10009")
	 * His own unique id
	 * 5 random peers and their statuses
 
 * Can : 
	 * update his own status ( max 500 characters )
	 * replace the list of peers ( get 5 random peers )
	 * Change the address of one of the peers with a known one ( possibly the one of a friend )
	 * Engage in an ephemeral chat with a follower that they follow back
 
##The server app

* Provides :
 * 5 default agents ( bots ) that set as status an interesting quote. The agents will update their status every 2 minutes
 
* Security : 
 * The app will permit only a certain level of activity per ip and connection
 * If that is exceeded, it will close/refuse the connection  
 