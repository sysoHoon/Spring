package com.psh.app;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExampleController {
	private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);
	
	// (1) controller -> view 로 데이터를 전달할 때 Modle 객체 사용
	
	@RequestMapping(value = "model")
	public void model(Model model) {	// 핸들러 메소드 *인자로 선언된 Model 객체*에 애트리뷰트 저장.
		// Model은 인터페이스(직접 객체를 생성 못함 - 구현체 필요함). 핸들러 메소드에서 선언하는 것 아님.
		// view에 데이터를 전달하기 위해 Model 구현체는 스프링이 알아서 생성
		model.addAttribute("name","이사나");
		model.addAttribute("page", 11);
	}
	
	// (2) 파라미터 -> controller에서 애트리뷰트로 저장 -> view로 데이터를 전달
	@RequestMapping(value = "modelAttr")
	public void search(@ModelAttribute(value="name")String name,
			@ModelAttribute(value="age")int age) {		// 밸류와 변수의 이름은 같게
	// **name, age는 요청 파라미터이면서 view에 전달되는 애트리뷰트(@ModelAttribute로 model 객체에 값을 저장)
		logger.info("[MyInfo] name = " + name + ", age = " + age);
	}
	
	@RequestMapping(value = "modelAttr2")
	public void search(@RequestParam Map<String, Object> param, Model model) {
		// @RequestParam : 요청 파라미터 어노테이션. map으로 저장할 때는 필수
		logger.info("[MyInfo] param = " + param); 
		logger.info("[MyInfo] param = " + param.get("city"));	// map의 key 지정하여 value 가져오기
		model.addAttribute("map", param);	// 요청 파라미터를 애트리뷰트에 저장
	}
	
	@RequestMapping(value = "modelAttr3")
	public void search(@ModelAttribute(value = "vo") TestVo vo) {
		logger.info("[MyInfo] TestVo = " + vo);
		// **vo 객체는 요청 파라미터도 저장하고 view에도 전달되는 애트리뷰트
	}
	
	// 연습 : url로 order를 요청하면 orderForm.jsp를 view 출력
	// orderForm2.jsp에서 입력한 값을 modelAttr4.jsp에서 출력하기
	
	@RequestMapping(value = "orderAttr")
	public String orderAttr(@ModelAttribute(value = "order") Order order) {
		return "modelAttr4";
	}
	
	@RequestMapping(value = "order2")
	public String orderForm2() {
		return "orderForm2";
	}
	
	// 리다이렉트 url로 파라미터 전달
	@RequestMapping(value = "search5")
	public String search(int age, String name) {
		// 메소드 인자로 파라미터가 저장될 변수를 선언. 변수명은 파라미터 이름과 일치(순서 무관)시킨다.
		logger.info("[MyInfo] name = " + name + ", age = " + age);
		return "home";	
	}
	
	@RequestMapping(value = "redirectParam")
	public String search() throws UnsupportedEncodingException{
		String name = URLEncoder.encode("트와이스", "UTF-8");
//		String name = "트와이스";
		return "redirect:search5?name=" + name + "&age=25";	 // response.sendRedirect("search5?name="+ name "&age=25");
	}
// redirect:url은 서버가 재요청을 보낼 주소를 지정
// http://localhost:8080/app/search5?name=트와이스&age=25 직접 주소창 입력하면 UTF-8 인코딩 기본
	
	@RequestMapping(value = "redirectAttr")
	public String redirect(Model model) {
		model.addAttribute("name", "트와이스");
		model.addAttribute("age", 25);
		return "redirect:search5";
		
		// return "redirect:search?name=트와이스&age=25"; 와 같은 실행결과
		// 결론 : model은 redirect에서는 get 요청 파라미터 값이 된다.
		// 실행 했을 때 url을 확인하자.
	}
}
