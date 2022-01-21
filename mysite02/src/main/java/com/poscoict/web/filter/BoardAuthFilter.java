package com.poscoict.web.filter;

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

import com.mysql.cj.util.StringUtils;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.util.MvcUtil;

/**
 * Servlet Filter implementation class AuthFilter
 */
public class BoardAuthFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 접근 제어
		HttpSession session = ((HttpServletRequest) request).getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		HttpServletRequest req = (HttpServletRequest) request;
		if (authUser == null
				&& !StringUtils.isEmptyOrWhitespaceOnly(req.getParameter("a"))
				&& !"view".equals(req.getParameter("a"))) {
			session.setAttribute("originURL", req.getRequestURI() + "?" + req.getQueryString());
			MvcUtil.redirect(((HttpServletRequest) request).getContextPath() + "/user?a=loginform", ((HttpServletRequest) request), ((HttpServletResponse) response));
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
