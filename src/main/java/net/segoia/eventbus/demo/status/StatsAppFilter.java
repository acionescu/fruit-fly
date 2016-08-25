/**
 * fruit-fly - A status sharing demo app, built with web-events and websockets
 * Copyright (C) 2016  Adrian Cristian Ionescu - https://github.com/acionescu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
