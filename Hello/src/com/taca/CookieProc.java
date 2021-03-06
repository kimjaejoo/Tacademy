package com.taca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "cookieProc", description = "쿠키값을 변경한다", urlPatterns = { "/cookieProc" })
public class CookieProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CookieProc() {
        super();
        
    }

    
	// 쿠키를 삭제하기 위함
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
		// 쿠키 삭제를 할것
		// 삭제하고자 하는 키만 전달한다.
		String key = req.getParameter("delkey"); // 앞에는 delkey=age라는 조건을 주었으며 그것을 기반으로 작동한다.
		if(key == null){
			BufferedReader br =new BufferedReader(new InputStreamReader(req.getInputStream()));
			key = URLDecoder.decode(br.readLine(), "UTF-8");
			key = key.split("=")[1];
		}
		System.out.println(key);
		Cookie c = new Cookie(key, "");
		c.setMaxAge(0);
		resp.addCookie(c);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청한 값을 획득하여 쿠키를 변경하고 돌아간다.
		response.addCookie(new Cookie("uid",request.getParameter("uid")));
		response.addCookie(new Cookie("upw",request.getParameter("upw")));
		request.getRequestDispatcher("cookie_test2.jsp").forward(request, response); // 되돌아가는 코드
	}

}
