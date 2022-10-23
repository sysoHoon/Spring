package com.psh.boot.controller;

import java.util.List; 
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.psh.boot.dao.MemberMapper;
import com.psh.boot.dto.Member;

@Controller
@RequestMapping(value = "member/")	// sub path(contextPath의 하위 경로)
@SessionAttributes({"member"})		// model.addAttribute로 member를 변경하면 동기화.
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private MemberMapper dao;
	
	public MemberController(MemberMapper dao) {
		this.dao = dao;
	}
	
	
	// 회원가입
	@GetMapping("join.do")
	public String join() {
		return "member/form";
	}
	
	@PostMapping("join.do")
	public String join(Member member, RedirectAttributes rdattr) {
		logger.info("회원 가입 입력 정보: {}", member);
		// 테이블 insert.
		if(dao.addMember(member)==1)
			rdattr.addFlashAttribute("alertm","회원가입이 완료되었습니다.");
		else
			rdattr.addFlashAttribute("alertm","회원가입에 문제가 있습니다.");
		return "redirect:/";
	}
	// 다른 jsp 파일들 동작 핸들러 메소드 만들기.(회원목록, 회원정보수정, 이메일체크)
	@GetMapping("list.do")
	public String list(Model model) {
		List<Member> list = dao.selectAll();
		model.addAttribute("list",list);
		return "member/list";
	}
	
	// 회원정보 수정
	@GetMapping("update.do")
	public String update() {		// 회원 수정할 정보는 세션에서 view로 가져감. (로그인 꼭 필요한 url)
		return "member/update";
	}
	
	@PostMapping("save.do")
	public String update(Member member, Model model, RedirectAttributes rdattr) {	// 여기서 member는 사용자가 수정한 값(파라미터) 저장
		if(dao.updateMember(member)==1)
			rdattr.addFlashAttribute("alertm", "회원정보 수정이 완료 되었습니다.");
//		member = dao.selectByMno(member.getMno());
		model.addAttribute("member", member);
		return "redirect:update.do";
	}
	
	// 이메일 중복확인
	@GetMapping("idCheck.do")
	public String idCheck(String email, Model model) {	// ajax로 변경 해보기
		logger.info("중복 확인 이메일 : {}", email);
		String msg;
		if(dao.checkEmail(email)==0) {
			msg = "사용할 수 있는 이메일 입니다.";
		}else {	// email은 유일한 값. else는 카운트 1
			msg = "사용할 수 없는 중복된 이메일 입니다.";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("email", email);
		return "member/idCheck";
	}
	
	// 비밀번호 변경
	@GetMapping("passw.do")
	public String passw() {
		return "member/passw";
	}
	
	@PostMapping("passw.do")
	public String passw(@RequestParam Map<String, String> map) {
		logger.info("비밀번호 변경 파라미터 : {}", map);
		// 패스워드 변경 여부 logger.info로 출력 해보기
		if(dao.changePassw(map)==1)
			logger.info("패스워드 변경 완료");
		else logger.info("현재 비밀번호 불일치");
		return "redirect:update.do";
	}
	
}
