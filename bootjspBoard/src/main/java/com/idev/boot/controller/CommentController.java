package com.idev.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.idev.boot.dto.Comments;
import com.idev.boot.service.CommentService;



@Controller
@RequestMapping("/community/comment")
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	// 모든 핸들러 메소드의 url은 동일. 핸들러 메소드 매핑은 params(파라미터 유형)로 한다.
	@RequestMapping(params="action=insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute Comments comt,int page, int func,RedirectAttributes redirectAttributes ) {
		if(func==1) {
			//댓글 idx는 자동증가 컬럼
			service.insert(comt);
			// 확인 필요 : 댓글 갯수 업데이트   
		}else if (func==2){
			service.update(comt.getIdx(),comt.getContent());
		}
	//	model.addAttribute("idx", comt.getMref());
	//	model.addAttribute("page", page);
		redirectAttributes.addAttribute("idx", comt.getMref());
		redirectAttributes.addAttribute("page", page);
		return "redirect:/community/detail";
	}
	
	//이 메소드는 ?action=delete 파라미터로만 매핑합니다. GET,POST 모두 매핑될수 있습니다.
	@RequestMapping(params="action=delete")
	public String delete(int idx,int page,int mref,RedirectAttributes redirectAttributes) {
		service.delete(idx);    //댓글 번호
		// 확인 필요 : 댓글 갯수 업데이트
//		model.addAttribute("idx", mref);    //메인글번호
//		model.addAttribute("page", page);
		redirectAttributes.addAttribute("idx", mref);
		redirectAttributes.addAttribute("page", page);
		return "redirect:/community/detail";
	}
	
}
