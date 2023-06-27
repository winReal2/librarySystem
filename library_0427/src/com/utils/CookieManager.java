package com.utils;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {

	public CookieManager() {
		
	}
	// 총 3개의 메서드 생성

	// 첫번째 매서드
	// 이름, 값, 유지시간을 매개변수로 받아서 쿠키를 생성 후 응답객체에 담기
	
	public static void makeCookie(HttpServletResponse response, String cName, String cValue, int cTime) {
		// 쿠키생성
		Cookie cookie = new Cookie(cName, cValue);
		// 경로설정
		cookie.setPath("/");
		// 유지기간 설정
		cookie.setMaxAge(cTime);
		// 응답객체에 추가
		response.addCookie(cookie);
	}
	//=================================================
	// 두번째 메서드
	// 쿠키이름을 받아서 쿠키값을 반환
	public static String readCookie(HttpServletRequest request, String cName) {
		String cookieValue="";
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			//쿠키배열을 돌면서 일치하는 이름을 찾아서 값을 반환
			for(Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				if(cName.equals(cookieName)) {
					cookieValue = cookie.getValue();
					break;
				}
			}
		}
		return cookieValue;
	}
	
	//=================================================
	// 세번째 메서드
	// 쿠키이름을 매개변수로 받아서 쿠키 삭제(시간을 0으로 설정)
	public static void deleteCookie(HttpServletResponse response, String cName) {
		// 값은 ""(공백), 시간은 0
		makeCookie(response, cName, "", 0);
	}
	
	
	
}
