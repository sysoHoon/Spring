package com.psh.boot.controller;

import java.text.DateFormat; 
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.psh.boot.dao.MemberMapper;
import com.psh.boot.dto.Member;

@Controller
@SessionAttributes({"member","serverTime"}) 
// *애트리뷰트 데이터가 저장되는 Model 저장소에서 member, serverTime은
// 세션 scope(세션 객체에 저장)으로 사용되는 값이라는 설정
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	private MemberMapper mapper;
	// MemberMapper bean 자동 주입
	
	public IndexController(MemberMapper mapper) {
		this.mapper = mapper;
	}
	
	@RequestMapping(value = "ajax")
	public String ajax() {
		return "member/ajaxTest";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
//		model.addAttribute("success", success);		// -> 어노테이션으로
		
		return "home";
	}
	
	

	@GetMapping("login.do")
//	public String login(@ModelAttribute("success") String success) {  //view 로 바로 전달
	public String login(Model model) {
		return "login";
	}
	
	@PostMapping("login.do")   
	public String loginProc(@RequestParam Map<String,String> map, RedirectAttributes rdattr, Model model) {
		logger.info("[My]"+map);
		Member member = mapper.login(map);  //로그인 성공하면 null 아닌값 반환
		String url;
		if(member !=null) {
			//성공 : 메인 화면으로 , session 객체에 로그인 정보를 저장(세션 애트리뷰트로 저장하기) 
			model.addAttribute("member", member);    //@SessionAttributes 로 설정하기.
			rdattr.addFlashAttribute("alertm", "로그인 성공 하였습니다.");		// 리다이렉트 애트리뷰트(파라미터로 사용됨)
			url = "/";    //로그인 성공메시지 alert 띄우기
		}else { //실패 : 다시 로그인 하러가기 . ((미션)) alert 메시지 띄우기 "로그인 정보가 올바르지 않습니다.!"
			rdattr.addFlashAttribute("alertm", "로그인 정보를 확인하세요.");
			url = "login.do";
		}
		return "redirect:"+url;
	}
	
	@GetMapping("logout.do")
	public String logout(SessionStatus status) {  //현재세션상태 객체
		status.setComplete();  //@SessionAttributes 로 설정된 애트리뷰트 값을 clear 한다.
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout2(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();  
//서버가 JSESSIONID는 새로 부여해주지만 @SesstionAttributes 로 설정된 값은 남아있다.
		return "redirect:/";
	}
	
	
//@SessionAttributes로 설정된것은 SessionStatus 로 지운다.	
//status.setComplete();   
//	- JSESSIONID 는 변하지 않고 @SessionAttributes 로 설정된 애트리뷰트 값을 clear 한다.
//	- HttpSession의 removeAttribute() 메소드 동작과 유사v
	
//jsp에서 로그아웃 : session.invalidate();   //JSESSIONID값을 새로운값으로 합니다.
//			     session.removeAttribute("member");   //- JSESSIONID 는 변하지 않고 값 삭제 	
	
	
	
}
