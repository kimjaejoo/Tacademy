package com.taca;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "sessionProc", description = "세션정보를 이용하여 비즈니스 로직 수행 후 화면처리 및 세션종료", urlPatterns = { "/sessionProc" })
public class SessionProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SessionProc() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 . session 정보 획득
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("uid"));
		System.out.println(session.getAttribute("upw"));
		String uid = (String)session.getAttribute("uid");
		String upw = (String)session.getAttribute("upw");
		// session 정보 누락시
		
		if(uid == null || upw == null ){
			response.getWriter().println("<scrpit>alert('정보누락');histoty.back();</script>");
		}else{
			// 디비쿼리
			// 실패면 => 백
			// 성공이면
			session.invalidate(); // session 정보 해제
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
