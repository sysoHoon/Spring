package  com.idev.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.idev.boot.dto.Board;
import com.idev.boot.dto.PageDto;
import com.idev.boot.service.CommentService;
import com.idev.boot.service.FreeboardService;


@Controller
@RequestMapping("/community")    //-> view 폴더명 동일할때 생략, 리다이렉트 community 생략
public class FreeboardController {
	private static final Logger logger = LoggerFactory.getLogger(FreeboardController.class);
	
	@Autowired
	private FreeboardService service;
	
	@Autowired
	private CommentService  cmtservice;
	
	
//   request mapping을 여러 url 요청으로 할수 있습니다.value 가 배열.
	//게시판 리스트 보기(검색기능 포함)     	
	@RequestMapping(value={"/","/list"})   
	public String pageList(PageDto param,Model model) { //String page,String field,String findText,Model model) {
		
		List<Board> list;
		int totalCount; int pageSize=10;
		if(param==null) param=new PageDto();
		if(param.getPage()==0) param.setPage(1) ;
		
		//page를 사용자 맘대로 문자 대입하면 NumberFormatExceptrion 발생 -> ExceptionHandler 로 처리합니다.
		
		PageDto pageDto;
		//검색 기능사용할 때 검색필드와 검색키워드 뷰에 전달한다.

		totalCount=service.searchCount(param);   //서비스 메소드 타입 변경예정
		pageDto=new PageDto(param.getPage(), pageSize, totalCount, param.getField(), param.getFindText());
		list=service.searchList(pageDto);  
		logger.info("pageDto : " + pageDto);
		Map<String,Object> map = new HashMap<String,Object>();    
		map.put("page", pageDto);			//view에게 전달할 모델객체 설정
		map.put("list",list);				//          "
		model.addAllAttributes(map);	//위에 4개의 put 실행한 map객체를 애트리뷰트에 저장한다.
		
		return "community/list";
	}		// 쿠키값 가져와서 오늘 읽은 글(글번호 출력 -> 링크로 '글 상세보기' 바로 가기) 화면에 출력하기

   @RequestMapping("/cookie")
   public String cookie(HttpServletRequest req, Model model) {
      Cookie[] cookies = req.getCookies();
      for(Cookie c : cookies) {
         if(c.getName().equals("read")) {
            logger.info(c.getName());
            logger.info(c.getValue());
            String [] s = c.getValue().split("/");
            model.addAttribute("list", s);
         }
      }
      return "community/cookie";    
      }
	
	//상세보기 : 
	@RequestMapping("/detail")     
//	public void detail(int idx, int page,String field, String findText, Model model) {     //쿠키로 조회수 카운트 구현하기 이전
	public String detail(int idx, int page,String field, String findText, Model model
			,HttpServletResponse response
			,@CookieValue(name="read",defaultValue = "abcde") String readidx) {  //  read 쿠기값 예시 abcde/3/67/178/
		//읽어올 쿠키이름은 read 쿠키 값이 없다면 기본값 "abcde" , 쿠키값을 저장할 변수는 readidx , default 값이 없으면 처음실행시 쿠키값없어서 오류
		if(!readidx.contains(String.valueOf(idx))) {   //idx 정수값을 String으로 변환
			//읽지 않은 글
			readidx += "/" + idx;    //idx 가 캐스팅 되는것은 아닙니다.
			//조회수 증가 메소드
			service.updateReadCnt(idx);
		}
		
		//쿠키값 없었을 때 또는 새로 변경되었을 때
		Cookie cookie = new Cookie("read", readidx);
		//쿠키 유효시간 설정, 
		cookie.setMaxAge(30*60);    //초 단위, 30분
		cookie.setPath("/board");   //쿠키 경로 설정
		response.addCookie(cookie);		//기존 쿠키 정보에 쿠기 항목 추가  //쿠키는 자바스크립트에서 접근 가능합니다. document.cookie -> 보안상 취약
					//쿠키가 HttpOnly 속성을 true -> 클라이언트 단에서는 쓰기 안됩니다. secure 속성은 암호화해서 전송 https 프로트콜 통신으로만 사용
		
		model.addAttribute("bean",service.getBoardOne(idx) );
		model.addAttribute("cmtlist",cmtservice.commentList(idx) );
		model.addAttribute("cr","\n" );
		model.addAttribute("page",page);
		model.addAttribute("field",field);
		model.addAttribute("findText",findText);
		
		//view는 community/detail
		return "community/detail";
	}
	

	
	//글쓰기 - view  : insert() 메소드 
	@RequestMapping(value="/insert")
	public void insert(@RequestParam(defaultValue = "1") int page,Model model) {
		//로그인 되었을때만 글쓰기
		
		model.addAttribute("page", page);
	}  //view이름은 insert
	
	
	
	//글쓰기 - 저장   : save()메소드  리다이렉트 list로.
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@ModelAttribute Board board) {   
//@ModelAttribute 생략 가능 : form 요소 입력 -> @ModelAttribute -> 컨트롤러  -> @ModelAttribute -> view
		service.insert(board);
		return "redirect:list";
		//  redirect 할 때 , /가 없으면 현재 경로에서 접근
		//  /가 있으면 contextPath  /board 에서 시작
		//  "redirect:community/list" 는 오류 
		//   "redirect:/community/list" 는 정상
	}
	
	//수정 화면 출력
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public void update(@RequestParam Map<String, String> param,Model model) {		//@RequestParam Map<String, String> param
		//로그인 되었을때만
		model.addAttribute("bean", service.getBoardOne(Integer.parseInt(param.get("idx"))));
		model.addAllAttributes(param);
		logger.info(param.toString());			//파라미터 이름 확인하세요.-idx,page,field,findText
		//model.addAttribute("page", param.get("page"));
	}
	
	
	//수정 내용 저장
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String save2(Board board, int idx, int page,String field,String findText,Model model) {
//@ModelAttribute Board board 입니다. update.jsp 의 form 요소 값 -> 컨트롤러		
		service.update(board);
		
		//model.addAttribute("page", page);			//1)
		//return "redirect:list";
		model.addAttribute("idx", board.getIdx());
		model.addAttribute("page", page);
		model.addAttribute("field",field);
		model.addAttribute("findText", findText);
		return "redirect:detail";   //수정
	}
	
	//삭제 
	@RequestMapping(value="delete")
	public String delete(@RequestParam Map<String,Object> param,Model model) {
		service.delete(Integer.parseInt((String)param.get("idx")));
		//model.addAttribute("page", page);
		model.addAllAttributes(param);
		return "redirect:list";
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handleErr(HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", request.getRequestURL());   //애트리뷰트 저장
		mav.setViewName("/error/error");
		return mav;
	}
	
	
	
}
