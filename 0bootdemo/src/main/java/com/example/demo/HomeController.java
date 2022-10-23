package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = {"/", "home"})	// 핸들러 메소드에 매핑되는 url이 배열(여러개)
	public String home() {
		logger.info("spring boot 테스트 중....");
		return "home";		// view 파일 이름 지정 해야함.
	}
}
