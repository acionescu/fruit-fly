package net.segoia.eventbus.stats;

import java.util.HashMap;
import java.util.Map;

import net.segoia.event.eventbus.EventContext;

public abstract class HierarchycalStats extends SimpleStats{
    private Map<String, SimpleStats> nested = new HashMap<>();

    /* (non-Javadoc)
     * @see net.segoia.eventbus.stats.SimpleStats#onEvent(net.segoia.event.eventbus.EventContext)
     */
    @Override
    public void onEvent(EventContext ec) {
	super.onEvent(ec);
	processNested(ec);
    }
    
    public SimpleStats getNested(String nestedKey, boolean create) {
	SimpleStats ns = nested.get(nestedKey);
	if(ns == null && create) {
	    ns = buildNestedStats();
	    nested.put(nestedKey, ns);
	}
	
	return ns;
    }
    
    public Map<String, SimpleStats> getNestedStats(){
	return nested;
    }
    
    protected abstract SimpleStats buildNestedStats();
    
    protected abstract void processNested(EventContext ec);
}
