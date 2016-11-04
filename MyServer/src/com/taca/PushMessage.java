package com.taca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

@WebServlet(name ="push", urlPatterns="/push", description = "안드로이드 fcm 메세지 발송")
public class PushMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MulticastResult result;
    
	public PushMessage() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 발송기 객체 생성
		Sender sender = new Sender("AIzaSyCn_nHGJP4Cgroh3jTZVSTVQTxN_ZFD-pk");
		
		// 2. 메세지 구성
		Message message = new Message.Builder().addData("data", "hi 방가 123 !@#").build();
		
		// 3. 고객정보 (토큰) 획득 -> DB
		String token = "e5aW2a54Qg8:APA91bGdzY2XM0NI1RarEamryKhE2xUmj8aygkolB0qLkr-gNxWUWswQiWLOR00wvY5d_Nu27ix7qgZ06iXefPfaQlPFyyhK3-la2o1Jg3-Rx7Ay3PO73dZRhRX4n94q48u39QdSFEfV";
		List<String> list = new ArrayList<String>();
		list.add(token); // 여러명에게 동시에 보내기 위함
		
		// 4. 발송
		
		sender.send(message, list, 5);
		if(result != null){
			for(Result r : result.getResults()){
				System.out.println(r.getMessageId());
			}
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
