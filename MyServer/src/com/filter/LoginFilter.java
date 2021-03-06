package com.filter;

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

@WebFilter(description = "로그인 처리시 전 후처리", urlPatterns = { "/LoginFilter" })
public class LoginFilter implements Filter {

	public LoginFilter() {
		System.out.println("Login Filter 생성");
	}

	public void destroy() {
		System.out.println("Login Filter 해제");

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Login Filter 필터링 적용 doFilter()");
		// 특정 session정보가 없으면 홈페이지로 이동
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if(req.getSession().getAttribute("loginon") == null){
			// 요청을 가지고 페이지이동
			System.out.println("logon session이 없으므로 홈페이지로 강제이동");
			req.getRequestDispatcher("index.jsp").forward(req, resp);;
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Login Filter init()");

	}

}
