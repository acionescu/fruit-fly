
#Simple event based, status notification demo


##The user ( web client app )

* user loads the page

* Sees a widget with:
 * 5 random peers and their statuses
 * His defaults status (e.g. "Hi, I'm Guest_10009")
 * His own unique address
 
* Can : 
 * update his own status ( max 255 characters )
 * refresh the list of peers ( get 5 random peers )
 * Change the address of one of the peers with a known one ( possibly the one of a friend )
 
##The server app

* Provides :
 * 5 default agents ( bots ) that set as status an interesting quote. The agents will update their status every 2 minutes
 
* Security : 
 * the app will permit a maximum of 10 connections from the same ip. If exceeded, will present a "sorry" message: "Sorry, only 10 concurrent connections allowed from your ip <ip>. Please try again later." 
 
 
 