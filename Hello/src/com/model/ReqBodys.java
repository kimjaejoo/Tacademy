package com.model;

import java.util.ArrayList;

public class ReqBodys {

	int head;
	ArrayList<ReqLogin> body;
	// json의 타입이 배열일 경우에는 이렇게 받는다.
	
	public int getHead() {
		return head;
	}
	public void setHead(int head) {
		this.head = head;
	}
	public ArrayList<ReqLogin> getBody() {
		return body;
	}
	public void setBody(ArrayList<ReqLogin> body) {
		this.body = body;
	}

	
	
	
	
	
	

	
	
}
