package com.idev.boot.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TestController {
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletResponse response) {
	//	logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		//쿠키와 세션 비교테스트합니다.(세션은 HttpServletRequest 객체로부터 생성하고, 쿠키는 HttpServletResponse 객체로 응답을 보내어 저장합니다.)
		//쿠키 생성 (쿠키는 데이터타입이 String)
		Cookie coo = new Cookie("view", "abcdefg");    //쿠키는 쿠키이름과 값을 한 쌍으로 생성합니다.(이름:view, 값:abcdefg)
		//coo.setMaxAge(60*3);   //쿠키 유효시간 설정이 필요합니다.(단위: 초)  24시간 24*60*60
		coo.setMaxAge(50*60);
		//coo.setPath(request.getContextPath());    //쿠키의 적용범위  --> http://localhost:8085/freeboard
		//coo.setPath("/");    					  //쿠키의 적용범위  --> http://localhost:8085/
		response.addCookie(coo);     //응답으로 브라우저에게 전달->브라우저에 저장.
		//세션 테스트
		
		
		return "test";
	}
	
	//쿠키 읽어오기 :  request로 읽어옵니다.(테스트용)
	@RequestMapping("hello")
	public void hello(HttpServletRequest request) {
		//쿠키에 저장된 데이터가 많을 수 있으므로 배열로 모두 읽어옵니다.
		Cookie[] coos = request.getCookies();
		
		for(Cookie c : coos) {
			System.out.println(c.getName());
			System.out.println(c.getValue());
		}
	}
	
}
