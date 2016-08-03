package net.segoia.eventbus.web.websocket.server;

public class AuthError {
    private String fieldName;
    private String expected;
    private String was;
    
    public AuthError(String fieldName, String expected, String was) {
	super();
	this.fieldName = fieldName;
	this.expected = expected;
	this.was = was;
    }
    
    
}
