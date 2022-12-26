package com.campus.myapp.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campus.myapp.service.BoardService;
import com.campus.myapp.vo.BoardVO;
import com.campus.myapp.vo.PagingVO;

@Controller
public class AjaxController {
	@Autowired
	BoardService service;
	
	@RequestMapping("/ajax/ajaxView")
	public String ajaxView() {
		return "ajax/ajaxView";
	}
	//                           리턴이 String일 때 한글 깨짐 처리(서버->클라이언트)
	@RequestMapping(value="/ajaxString", method=RequestMethod.GET, produces="application/text;charset=UTF-8")
	@ResponseBody    // String, ModelAndView로 리턴되는 경우 jsp 파일명이 있다는 뜻
					 // 비동기식 : 뷰페이지가 필요 없음 -> 리턴되는 객체가 곧 컨텐츠라는 의미의 어노테이션 필요, 매핑주소에서 컨텍스트 없애기
	public String ajaxString(int num,String name) {
		System.out.println("num->"+num);
		System.out.println("name->"+name);
		
		return "<h1>번호 : "+num+"</h1><h2>이름 : "+name+"</h2>";
	}
	
	@GetMapping("/ajaxObject")
	@ResponseBody
	public BoardVO ajaxObject(String username,String tel,int age) {
		System.out.println(username+","+tel+","+age);
		BoardVO vo=new BoardVO();
		vo.setNo(1234);
		vo.setSubject("가짜 글제목");
		vo.setUserid("마음대로");
		return vo;
	}
	
	@GetMapping("/ajaxList")
	@ResponseBody
	public List<BoardVO> ajaxList(PagingVO pvo) {
		pvo.setTotalRecord(service.totalRecord(pvo));
		List<BoardVO> list=service.boardPageSelect(pvo);
		
		return list;
	}
	
	@GetMapping("/ajaxMap")
	@ResponseBody
	public HashMap ajaxMap(PagingVO pvo){
		// 총 레코드 수(PagingVO)와 글 내용(BoardVO)을 리턴
		int totalRecord=service.totalRecord(pvo);
		BoardVO vo = service.boardSelect(39);
		
		HashMap map = new HashMap();
		map.put("totalRecord",totalRecord);
		map.put("boardVO", vo);
		map.put("msg","맵을 이용한 비동기식 처리");
		
		return map;
	}
}
