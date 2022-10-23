package com.idev.boot;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ExampleController {
	private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);
	
	//controller ->view 로 데이터를 전달
	
	@RequestMapping(value="model")
	public void model(Model model) {		//핸들러 메소드 인자로 선언된 Model 객체에 애트리뷰트 저장합니다.
		model.addAttribute("name", "이사나");
		model.addAttribute("page", 11);
	}  
	
	//여기서부터는 파라미터 -> controller ->view 로 데이터를 전달
	@RequestMapping(value="modelAttr")
	public void search(@ModelAttribute(value="name")String name,
			@ModelAttribute(value="age")int age) {  
		logger.info("[MyInfo] name =" + name + ",age="+age);
	}
	
	@RequestMapping(value = "modelAttr2")  
	public void search(@RequestParam Map<String,Object> param,Model model) {
		//@RequestParam : 요청파라미터 어노테이션. map으로 저장할때는 필수
		logger.info("[MyInfo] param =" + param);
		logger.info("[MyInfo] param =" + param.get("city"));	//map의 key 지정하여 value가져오기
		model.addAttribute("map", param);
	}
	
	@RequestMapping(value = "modelAttr3")
	public void search(@ModelAttribute(value="vo") TestVo vo) {
		logger.info("[MyInfo] TestVo =" + vo);
	}
	
// url 로 order2를 요청하면 orderForm2.jsp를 view 출력.  
	@RequestMapping(value = "order2", method = RequestMethod.GET)
	public String form() {
		return "orderForm2";		//view 파일이름(확장자 제외) 지정
	}
	
	
	@RequestMapping(value="orderAttr")
	public String orderAttr
	(@ModelAttribute(value="order") 
	   Order order) {
		
		return "modelAttr4";
	}
	
	
	//리다이렉트 url 로 파라티터 전달
		@RequestMapping(value="search5")
		public String search(int age,String name) {   
			//메소드인자로 파라미터가 저장될 변수를 선언합니다.변수명은 파라미터 이름과 일치(순서 무관)시킵니다.
			logger.info("[MyInfo] name =" + name + ",age="+age);
			return "home";	//redirect:url 는 서버가 재요청을 보낼 주소 지정합니다.
		}
	
		@RequestMapping(value = "redirectParam")
		public String search() throws UnsupportedEncodingException {
			//String name = URLEncoder.encode("트와이스", "UTF-8");
			String name="트와이스";
			return "redirect:search5?name="+name+"&age=25";
		}
//http://localhost:8080/app/search5?name=트와이스&age=25 직접 주소창 입력하면 UTF-8인코딩 기본.
		
		@RequestMapping(value="redirectAttr")		// 스프링부트에서는 리다이렉트로 파라미터 전달시 Model 객체 사용 못함.
		public String redirect(RedirectAttributes rdattr) {
			rdattr.addAttribute("name", "트와이스");
			rdattr.addAttribute("age", 25);
	 		return "redirect:search5";   
	//return "redirect:search?name=트와이스&age=25"; 와 같은 실행결과
	//결론 : model은 redirect 에서는 get 요청 파라미터값		
//			실행 했을때 url을 확인합시다. 
		}
}
