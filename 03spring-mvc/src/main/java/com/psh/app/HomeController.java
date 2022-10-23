package com.psh.app;

import java.text.DateFormat; 
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * 요청 처리 메소드 : request handler method (url과 메소드가 매핑되는 형식)
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";	// view 파일이 home.jsp
	}
//	url과 핸들러 메소드 매핑할 때 url 사용. url이 같으면 다른 속성으로 매핑할 수 있음.(예 : method)
//	(1) ViewResovler 테스트 : view 파일 설정
	
// @RequestMapping 은 url을 지정
// 요청 method에 따라 구분할 때에는	@GetMapping	- get 요청 처리,  @PostMapping - post 요청 처리
	@RequestMapping(value="test")
	public void test() {
		//리턴 문자열이 없으면 view 파일은 url 요청 이름과 같은 파일명 즉, test.jsp 가 된다.
		logger.info("[MyInfo] test() 메소드 실행");	
		//System.out.println 대신에 로그를 관리하고 출력합니다.
	}
	
	@RequestMapping(value="hello")
	public void hello() {
		//view 파일이 hello.jsp
	}
	
	@RequestMapping(value="spring")
	public String spring() {
		return "home";		//view 파일이 home.jsp 가 된다.
	}
	
	// 파라미터 테스트
	@RequestMapping(value="search")
	public String search(String name, int age) {	// 메소드 인자로 파라미터가 저장될 변수를 선언
		logger.info("[MyInfo] name = " + name + ",age = " + age);
		return "redirect:/";	// redirect:url 는 서버가 재요청을 보낼 주소를 지정
	}
	
	@RequestMapping(value="search2")
//	public String search(String name) {	// 파라미터는 2개, 변수는 1개 : 오류없이 1개만 저장	
//	public String search(String name, int age, int city) {	// 파라미터는 2개, 변수는 3개 : 오류	
	public String search(String name, int age, @RequestParam(defaultValue = "1") int city) {
		// 파라미터는 2개, 변수는 3개 : 전달 안된 파라미터의 기본값 설정
		logger.info("[MyInfo] name = " + name);
		return "redirect:spring";
	}
	
	@RequestMapping(value="search3")
	public String search(@RequestParam Map<String, Object> param) {
		// @RequestParam :  요청 파라미터 어노테이션. map으로 저장할 때는 필수
		logger.info("[MyInfo] param = " + param);
		logger.info("[MyInfo] param = " + param.get("city"));	// map의 key 지정하여 value 가져오기
		return "redirect:spring";
	}
	
	@RequestMapping(value="search4")
	public String search(TestVo vo) {
		logger.info("[MyInfo] TestVo = " + vo);
		return "redirect:spring";
	}
	
	
	// url로 order를 요청하면 orderForm.jsp를 view 출력. 3가지 항목 입력한 값 전달 받아서 logger.info로 출력
	// 파라미터 테스트 : view -> controller 핸들러 메소드
	// orderForm.jsp 를 화면에 띄운다.
	@RequestMapping(value="order", method = RequestMethod.GET)
		public String form() {		// view 파일이름(확장자 제외) 지정
			return "orderForm";
		}

// Map은 form 입력 요소 값 null 오류 발생 안함. Order 클래스를 사용할 때는 입력 요소 값 null일 때 오류 발생.
	// 폼 양식에 입력된 값을 가져오기 한다.
	@RequestMapping(value = "order", method = RequestMethod.POST)
	public String order(Order order) {
		logger.info("[MY]" + order);	// 확인
		return "home";		// url은 order, 화면은 home.jsp    
	}
}
