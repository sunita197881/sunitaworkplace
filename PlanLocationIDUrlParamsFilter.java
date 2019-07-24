package com.adobe.training.core.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.sling.SlingFilter;
import org.apache.felix.scr.annotations.sling.SlingFilterScope;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

//	http://localhost:4502/system/console/status-slingfilter
// 	http://localhost:4502/content/trainingproject/plan-104105/location-9999/FirstVignetteContent.104105.9999.html
// 	http://localhost:4502/content/trainingproject/plan-104105/location-9999/FirstVignetteContent.104139.0101.html

@SlingFilter(generateComponent = false, generateService = true, order = -50001, scope = SlingFilterScope.REQUEST) 

@Component(immediate = true, metatype = false)

public class PlanLocationIDUrlParamsFilter implements Filter {
	Logger log = LoggerFactory.getLogger(this.getClass());
	String planIDinJCR1="104105";
	String locationIDinJCR1="9999";
	String planIDinJCR2="104139";
	String locationIDinJCR2="0101";
	String[] arrSplit;
	int i;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Usually, do nothing
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (!(request instanceof SlingHttpServletRequest) || !(response instanceof SlingHttpServletResponse)) {			
            // Proceed with the rest of the Filter chain           
			chain.doFilter(request, response); 
			return;
		}

		final SlingHttpServletResponse slingResponse = (SlingHttpServletResponse) response;
		final SlingHttpServletRequest slingRequest = (SlingHttpServletRequest) request;
		log.info("request for {}, with selector {}", slingRequest.getRequestPathInfo().getResourcePath(), slingRequest.getRequestPathInfo().getSelectorString());
		String multiselector=slingRequest.getRequestPathInfo().getSelectorString();		
		log.info("MULTISELECTOR is ----------- ====**** " + multiselector);		
		final Resource resource = slingRequest.getResource();
		log.info("resource is ----------- ====**** " + resource);
		
		if (resource.getPath().startsWith("/content/trainingproject")) {
			
			PageManager pageManager =  resource.getResourceResolver().adaptTo(PageManager.class);
	        Page page = pageManager.getPage(resource.getPath());
	        String templatePath =  page.getContentResource().getValueMap().get("cq:template").toString();
	        if(templatePath.equals("/apps/trainingproject/templates/vignete-home"))
	        {
	        	arrSplit = multiselector.split(" ");
	 		    for (i=0; i < arrSplit.length; i++)
	 		    {
	 		     log.info("multiselector INSIDE FOR STATEMENT is ====**** " + arrSplit[i] );
	 		    }       	
	        	
	        	//log.info("OUTSIDE FOR STATEMENT is ----------- ====**** " +  arrSplit[i] );
	        	
	 		    slingResponse.setHeader("Content-Type", "text/html");
	        	if(multiselector.contains(planIDinJCR1))
	    		{
	    			//log.info("multiselector is ----------- ====**** " + planIDinJCR1 );	    			
		       	 	slingResponse.getWriter().println("<h1>PlanID is " + planIDinJCR1 + "</h1>");		       	 
	    		}	        	
	        	if(multiselector.contains(planIDinJCR2))
	    		{	    				    			
		       	 	slingResponse.getWriter().println("<h1>PlanID is " + planIDinJCR2 + "</h1>");		       	 
	    		}	        		        	
	        	if(multiselector.contains(locationIDinJCR1))
	    		{
		       	 	slingResponse.getWriter().println("<h1>LocationID is " + locationIDinJCR1 + "</h1>");		       	 
	    		}
	        	
	        	if(multiselector.contains(locationIDinJCR2))
	    		{
		       	 	slingResponse.getWriter().println("<h1>LocationID is " + locationIDinJCR2 + "</h1>");		       	 
	    		}
	        	/*if (!multiselector.contains(planIDinJCR1) && !multiselector.contains(locationIDinJCR1) || !multiselector.contains(planIDinJCR2) && !multiselector.contains(locationIDinJCR2))
	        	{
		       	 	slingResponse.getWriter().println("<h1>Wrong PlanID/LocationID Parameters. Please Check </h1>");	
	        	}*/	        	       	
	        	   	
	            return;
	        }
	      			
		}	
		
		 chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		// Usually, do Nothing
	}

}