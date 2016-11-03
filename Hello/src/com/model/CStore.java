package com.model;

import java.sql.Array;

// 편의점 정보 저장 클래스
public class CStore {
	int idx,opt1,opt2,opt3,opt4,opt5,opt6,opt7,opt8,opt9;
	String name ;
	String addr ;
	
	
	
	public CStore(int idx, String name, String addr) {
		super();
		this.idx = idx;
		this.name = name;
		this.addr = addr;
	}

	public String toString() {
		return "CStore [name=" + name + ", addr=" + addr + ", idx=" + idx + ", opt1=" + opt1 + ", opt2=" + opt2
				+ ", opt3=" + opt3 + ", opt4=" + opt4 + ", opt5=" + opt5 + ", opt6=" + opt6 + ", opt7=" + opt7
				+ ", opt8=" + opt8 + ", opt9=" + opt9 + "]";
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getOpt1() {
		return opt1;
	}

	public void setOpt1(int opt1) {
		this.opt1 = opt1;
	}

	public int getOpt2() {
		return opt2;
	}

	public void setOpt2(int opt2) {
		this.opt2 = opt2;
	}

	public int getOpt3() {
		return opt3;
	}

	public void setOpt3(int opt3) {
		this.opt3 = opt3;
	}

	public int getOpt4() {
		return opt4;
	}

	public void setOpt4(int opt4) {
		this.opt4 = opt4;
	}

	public int getOpt5() {
		return opt5;
	}

	public void setOpt5(int opt5) {
		this.opt5 = opt5;
	}

	public int getOpt6() {
		return opt6;
	}

	public void setOpt6(int opt6) {
		this.opt6 = opt6;
	}

	public int getOpt7() {
		return opt7;
	}

	public void setOpt7(int opt7) {
		this.opt7 = opt7;
	}

	public int getOpt8() {
		return opt8;
	}

	public void setOpt8(int opt8) {
		this.opt8 = opt8;
	}

	public int getOpt9() {
		return opt9;
	}

	public void setOpt9(int opt9) {
		this.opt9 = opt9;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
	
	
	
	
}
