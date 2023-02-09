package com.comunity.Comunity.common;

import java.util.HashMap;
import java.util.Map;

import com.comunity.Comunity.board.model.vo.Header;

public class Utils {

	
	public Header AuthtoHeader(String auth) {
		//Authentication -> Header 객체
		try {
			String[] aSplit = auth.split(" ");
			int id = Integer.parseInt(aSplit[1]);
			
			Header header = new Header();
			header.setAuth(aSplit[0]);
			header.setUserid(id);
			return header;
		} catch(Exception e) {
			System.out.println(e.toString());
			return null;
		}

	}
	
	
	
	public int getEtoNGrade(String auth) {
		// DB에 회원 타입에 따라 숫자로 넣음
		int grade = 0;
		switch(auth) {
		case "Realtor" : grade = 1; break;
		case "Lessor" : grade = 2; break;
		case "Lessee" : grade = 3; break;
		}
		
		return grade;
	}
	
	public String getNtoHGrade(String auth) {
		// 프론트에 나타낼 때 변환
		String grade = "";
		switch(auth) {
		case "1" : grade = "공인중개사"; break;
		case "2" : grade = "임대인"; break;
		case "3" : grade = "임차인"; break;
		}
		
		return grade;
	}
	
}
