package com.idev.boot;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
	 * 요청 처리 메소드 : request handler method (url과 메소드가 매핑되는 형식입니다.)
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";    //view파일이 home.jsp
	}
	
	//ViewResovler 테스트 : view 파일 설정 
	
	
	//@RequestMapping 은 요청 url을 지정합니다. 
	//요청 method에 따라 구분할때에는 @GetMapping - Get 요청처리 , 	@PostMapping - Post 요청 처리
	@RequestMapping(value="test")
	public void test() {
		//리턴 문자열이 없으면 view 파일은 url 요청 이름과 같은 파일명 즉, test.jsp 가 됩니다.
		logger.info("[MyInfo] test() 메소드 실행");	
		//System.out.println 대신에 로그를 관리하고 출력합니다.
	}
	
	
	@RequestMapping(value="hello")
	public void hello() {
		//view 파일이 hello.jsp
	}
	
	@RequestMapping(value="spring")
	public String spring() {
		return "home";		//view 파일이 home.jsp 가 됩니다.
	}

	
	//여기서부터는 파라미터 테스트 : url 쿼리스트링으로 전달되는 파라미터를  -> controller 메소드 
	@RequestMapping(value="search")
	public String search(int age,String name) {   
		//메소드인자로 파라미터가 저장될 변수를 선언합니다.변수명은 파라미터 이름과 일치(순서 무관)시킵니다.
		logger.info("[MyInfo] name =" + name + ",age="+age);
		return "redirect:/";	//redirect:url 는 서버가 재요청을 보낼 주소 지정합니다.
	}
	
	@RequestMapping(value="search2")
//	public String search(String name) {  //파라미터는 2개 , 변수는 1개 : 오류없이 1개만 저장
//	public String search(String name,int age,int city) {  //파라미터는 2개 , 변수는 3개 : 오류
	public String search(String name,int age,
		@RequestParam(defaultValue = "1") int city) {  
			//파라미터는 2개 , 변수는 3개 : 전달 안된 파라미터의 기본값 설정
		logger.info("[MyInfo] name =" + name );
		return "redirect:spring";
	}
	
	@RequestMapping(value = "search3")  
	public String search(@RequestParam Map<String,Object> param) {
		//@RequestParam : 요청파라미터 어노테이션. map으로 저장할때는 필수
		logger.info("[MyInfo] param =" + param);
		logger.info("[MyInfo] param =" + param.get("city"));	//map의 key 지정하여 value가져오기
		return "redirect:spring";
	}
	
	@RequestMapping(value = "search4")
	public String search(TestVo vo) {
		logger.info("[MyInfo] TestVo =" + vo);
		return "redirect:spring";
	}
	

	
// url 로 order를 요청하면 orderForm.jsp를 view 출력.  3가지 항목 입력한값 전달 받아서 logger.info출력
	// 파라미터 테스트 : view  -> controller 메소드 
	//orderForm.jsp 를 화면에 띄웁니다. 
		@RequestMapping(value = "order", method = RequestMethod.GET)
		public String form() {
			return "orderForm";		//view 파일이름(확장자 제외) 지정
		}
		
		//폼 양식에 입력된값을 가져오기 합니다.
		@RequestMapping(value = "order", method = RequestMethod.POST)
		public String order(Order order) {
			//메소드의 매개변수 파라미터(폼양식 입력값)가 여러개일때 model 객체를 사용합니다.
			logger.info("[My]"+order);		//확인
			return "home";     // url 은 order, 화면은 home.jsp
		}
}
