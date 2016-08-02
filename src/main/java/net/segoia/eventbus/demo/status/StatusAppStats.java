package net.segoia.eventbus.demo.status;

public class StatusAppStats {
    private long visitors;
    private long activeUsers;
    
    
    
    public long incVisitors() {
	return ++visitors;
    }
    
    public long visitors() {
	return visitors;
    }
    
    
    
}
