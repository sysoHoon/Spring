package com.psh.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psh.boot.dao.MemberMapper;
import com.psh.boot.dto.Member;

@RestController		// 비동기 통신 요청을 처리할 컨트롤러
@RequestMapping("member/")
public class RestMemberController {
	// 의존관계 자동 주입
	private MemberMapper dao;
	public RestMemberController(MemberMapper dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public String list() throws JsonProcessingException { 
	// 회원리스트 조회 결과
		List<Member> list = dao.selectAll();
		int result = dao.getCount();
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("members", list);
		
		// 응답 보낼 json 만들기
		ObjectMapper jmapper = new ObjectMapper();
		return jmapper.writeValueAsString(map);
	}
//	@RequestMapping(value = "getOne", method = RequestMethod.GET)
// @PathVariable은 URI 경로로 파라미터가 전달되는 변수를 지정한다.
// URI(URL) ajaxex/{mno}에서 {mno}는 파라미터
	@RequestMapping(value = "ajaxex/{mno}", method = RequestMethod.GET)
	@ResponseBody
	public String getOne(@PathVariable int mno) throws JsonProcessingException {
		Member member = dao.selectByMno(mno);
		
		Map<String, Object> map = new HashMap<>();
		map.put("member", member);
		
		ObjectMapper jmapper = new ObjectMapper();
		
		return jmapper.writeValueAsString(map);
	}
	
	@RequestMapping(value = "ajaxex", method = RequestMethod.POST)
	@ResponseBody		// return은 요청에 대한 응답으로 json 형식 문자열
	public String post(@RequestBody String json) throws JsonMappingException, JsonProcessingException {	// 메소드 인자는 요청으로 보내는 데이터가 json 형식 문자열
		// 0. json 형식은 Map과 형식이 비슷하다.
		// 1. json 문자열을 자바 객체로 변환해야 한다.(jackson-databind 라이브러리 사용)
		ObjectMapper jmapper = new ObjectMapper();
		jmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);	// 역직렬화 설정
		Member vo = jmapper.readValue(json, Member.class);		// json 문자열을 Member 객체로 변환
		// 2. 테이블 insert
		int result = dao.addMember(vo);
		
		// 3. 응답으로 요청에 대한 처리 결과
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result",result);		// map에 응답으로 보낼 데이터를 저장
		
		return jmapper.writeValueAsString(map);		// 응답을 문자열로 보내기 위해 json 문자열로 변환
				// writeValueAsString() : 응답을 문자열로 변환
	}
	
//	@RequestMapping(value = "ajaxex", method = RequestMethod.DELETE)
//	public String delete(int mno) throws JsonProcessingException {
	@RequestMapping(value = "ajaxex/{mno}", method = RequestMethod.DELETE)
	public String delete(@PathVariable int mno) throws JsonProcessingException {
		int result = dao.delete(mno);
		ObjectMapper jmapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result",result);
		map.put("message", "메시지");
		return jmapper.writeValueAsString(map);
	}
	
	@RequestMapping(value = "ajaxex", method = RequestMethod.GET)
	public String checkEmail(String email) throws JsonProcessingException {
		int result = dao.checkEmail(email);
		ObjectMapper jmapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return jmapper.writeValueAsString(map);
	}
	
	@RequestMapping(value = "changepw", method = RequestMethod.PUT)
	@ResponseBody
	public String changepw(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper jmapper = new ObjectMapper();
		jmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		// 문자열 3개(이메일, 기존 비밀번호, 새로운 비밀번호)
		@SuppressWarnings("unchecked")
		Map<String, String> param = jmapper.readValue(json, Map.class);
		
		int result = dao.changePassw(param);	// 비밀번호 변경되면 1, 기존 비밀번호 일치 안하면 0
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		
		return jmapper.writeValueAsString(map);
	}
}
