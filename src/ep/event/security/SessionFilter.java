package ep.event.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.classmate.Filter;

public class SessionFilter implements Filter<Object> {
	private FilterConfig filterConfig;
	public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) {
		HttpSession session =null;
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		try {
			session=req.getSession(false);
				if(session==null) {
					res.sendRedirect("/login"); //If the Active session is null ,we redirect to the timeout.jsp 
				}
				
				chain.doFilter(request, response); 
		}catch (IOException io) {
			System.out.println ("IOException raised in SimpleFilter");
		} catch (ServletException se) {
			System.out.println ("ServletException raised in SimpleFilter");
		}
	}
	
	public FilterConfig getFilterConfig() {
		return this.filterConfig;
	}
	public void setFilterConfig (FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
	
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	@Override
	public boolean include(Object arg0) {
		return false;
	}
}
