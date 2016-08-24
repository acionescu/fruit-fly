package net.segoia.eventbus.demo.status;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import net.segoia.eventbus.stats.SimpleStats;

@WebFilter(value="/*")
public class StatsAppFilter implements Filter{

    @Override
    public void destroy() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
	    throws IOException, ServletException {
	String remoteAddr = arg0.getRemoteAddr();
	
	SimpleStats ipStats = StatusApp.stats.getHttpStats().getNested(remoteAddr, false);
	if(ipStats != null) {
	    System.out.println(remoteAddr+" -> "+ipStats.getActivityIndex() );
	    if(ipStats.getActivityIndex() > 10) {
		PrintWriter writer = arg1.getWriter();
		writer.println("Sorry, the activity from your ip address is too high. Come back later.");
		writer.close();
		return;
	    }
	}
	
	arg2.doFilter(arg0, arg1);
	
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
	// TODO Auto-generated method stub
	
    }

}
