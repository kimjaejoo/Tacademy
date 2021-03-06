package com.taca;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.model.ReqBody;
import com.model.ReqBodys;
import com.model.ReqLogin;

@WebServlet(name = "jt", description = "json 사용방법 이해", urlPatterns = { "/jt" })
public class JsonTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JsonTest() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		// json test
		// JSON은 플랫폼과 언어의 독립적인 데이터 포맷(대표적으로 XML이 있다)
		// =? {...}
		// 키 : 값(순수한 값 , JSONOBJECT, ARRAY(값배열 , JSONARRAY))
		// 데이터 => JSON 문자열 변환 => 데이터 객체=> LIB 을 사용한다.\
		// GSON(GOOGLE), JACKSON, ....

		String jsonStr = "{\"head\" : 10 , \"body\" : [{\"uid\":\"guest\",\"upw\":\"1111\"},{\"uid\":\"admin\",\"upw\":\"1234\"}] }";
		
		// 이것을 사용할때는 키값을 getter, setter로 넘겨준다.
		// 클래스안에 클래스가 들어있는 형태의 경우에는 클래스안에서 다른클래스를 불러 그것을 getter setter로 만들어 사용한다.
		// body의 값이 배열일 경우 
		
		Gson gson = new Gson();
		//ReqLogin req =  gson.fromJson(jsonStr, ReqLogin.class);
		ReqBodys req =  gson.fromJson(jsonStr, ReqBodys.class);
		
		for(ReqLogin login : req.getBody()){
		
		System.out.println(req.getHead());
		
		System.out.println(login.getUid());
		System.out.println(login.getUpw());
		
		}
		/*System.out.println(req.getUid());
		System.out.println(req.getUpw());*/
		
		
		
	}

}
