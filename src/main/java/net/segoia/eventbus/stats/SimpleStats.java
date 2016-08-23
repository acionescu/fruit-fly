package net.segoia.eventbus.stats;

import net.segoia.event.eventbus.EventContext;
import net.segoia.event.eventbus.EventListener;

public class SimpleStats implements EventListener{
    private long startTime=System.currentTimeMillis();
    private long eventsCount;
    private long lastEventTs;
    private long lastEventsTsDiff;
    private float recentFrequency;

    @Override
    public void onEvent(EventContext ec) {
	eventsCount++;
	long now = System.currentTimeMillis();
	lastEventsTsDiff = now - lastEventTs;
	lastEventTs = now;
	
	recentFrequency=recentFrequency*0.9f+getLastFrequency()*0.01f;
    }
    
    
    private long duration() {
	return 1+((System.currentTimeMillis() - startTime))/1000;
    }

    public float getAvgFrequency() {
	return ((float)eventsCount)/duration();
    }
    
    public float getLastFrequency() {
	if(lastEventsTsDiff <=0 ) {
	    return 0;
	}
	return ((float)1000)/lastEventsTsDiff;
    }
    
    public float getRecentFrequency() {
	return recentFrequency;
    }
    
    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @return the eventsCount
     */
    public long getEventsCount() {
        return eventsCount;
    }

    /**
     * @return the lastEventTs
     */
    public long getLastEventTs() {
        return lastEventTs;
    }
    
    public float getActivityIndex() {
	return recentFrequency*0.1f+0.9f*((float)eventsCount)/duration();
    }
    

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("SimpleStats [startTime=").append(startTime).append(", eventsCount=").append(eventsCount)
		.append(", lastEventTs=").append(lastEventTs).append(", lastEventsTsDiff=").append(lastEventsTsDiff)
		.append(", recentFrequency=").append(recentFrequency).append(", duration()=").append(duration())
		.append(", getAvgFrequency()=").append(getAvgFrequency()).append(", getLastFrequency()=")
		.append(getLastFrequency()).append("]");
	return builder.toString();
    }

    @Override
    public void init() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void terminate() {
	// TODO Auto-generated method stub
	
    }

}
