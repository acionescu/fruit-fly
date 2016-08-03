package net.segoia.eventbus.demo.status;

public class StatusAppStats {
    private long visitors;
    private long activeUsers;
    
    
    
    public long newPeer() {
	activeUsers++;
	return ++visitors;
    }
    
    public void peerRemoved() {
	activeUsers--;
    }
    
    public long visitors() {
	return visitors;
    }
    
    
    
}
