package com.zingcrm.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@WebFilter("/*")
public class LoginFilter implements Filter
{

	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException
	    {
			 //request.setCharacterEncoding("UTF-8");
			 //response.setCharacterEncoding("UTF-8");
			 //chain.doFilter(request,response);
		   HttpSession session=((HttpServletRequest)request).getSession(false);
		   
		   HttpServletRequest httpRequest=(HttpServletRequest)request;
			HttpServletResponse httpResponse=(HttpServletResponse)response;
		    String Incoming_Url=httpRequest.getRequestURI();
		    if(Incoming_Url.contains("css") || Incoming_Url.contains("js") || Incoming_Url.contains("jpg") || Incoming_Url.contains("png") || Incoming_Url.contains("gif") || Incoming_Url.contains("JPEG") || Incoming_Url.contains("PNG")|| Incoming_Url.contains("ico") || Incoming_Url.equals("/") || Incoming_Url.contains("/LoginService") || Incoming_Url.contains("/LoginError") || Incoming_Url.contains("/forgotpwd") || Incoming_Url.contains("/profilesuccess") || Incoming_Url.contains("/404") || Incoming_Url.contains("/zing") || Incoming_Url.contains("/login") || Incoming_Url.contains("/demo") || Incoming_Url.contains("/saveActivity")  || Incoming_Url.contains("/updateActivity") || Incoming_Url.contains("/") )
			{
				   chain.doFilter(request,response);
				   
		    }
		   else if(session!=null && session.getAttribute("userId")!=null)
		   {
			  chain.doFilter(request, response);
		   }
		   else
		   {
			   httpResponse.sendRedirect("/login");
		   }
		   
	        if (response instanceof HttpServletResponse) {
	           // log.info("Applying cache control filter to response");
	            HttpServletResponse httpServletResponse = (HttpServletResponse)response;
	            httpServletResponse.setHeader("Cache-Control", "nocache");
	        }
	    }

	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {}

	    @Override
	    public void destroy() {}
}
