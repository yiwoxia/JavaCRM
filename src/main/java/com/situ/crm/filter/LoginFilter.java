package com.situ.crm.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.situ.crm.pojo.User;


/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		StringBuffer uri = req.getRequestURL();
		String s = req.getServletPath();
		String requestPath = uri.substring(uri.lastIndexOf("/") + 1 , uri.length());
		System.out.println(requestPath+"-----------------------");
		if (requestPath.equals("login.action") || requestPath.equals("log.action")) {
			chain.doFilter(request, response);
			return;
		}else{
			String httpServlet = req.getServletPath();
			//必须过滤掉注册和登录user为空的信息
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("backuser");
			if (user != null) {
				chain.doFilter(request, response);
				return ;
			} else {
				resp.sendRedirect(req.getContextPath() + "/index/login.action");
				return;
			}
			
			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
