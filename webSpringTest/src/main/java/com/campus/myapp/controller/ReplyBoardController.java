package com.campus.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.campus.myapp.service.ReplyBoardService;
import com.campus.myapp.vo.ReplyBoardVO;

// RestController로 설정하면 ResponseBody가 필요없다
// 단, 뷰페이지로 이동할 떄는 무조건 ModelAndView를 리턴한다
@RestController
public class ReplyBoardController {
	@Autowired
	ReplyBoardService service;
	
	@PostMapping("/replyBoardWrite")
	public int replyWrite(ReplyBoardVO vo,HttpSession session) {
		vo.setUserid((String)session.getAttribute("logid"));
		
		return service.replyBoardWrite(vo);
	}
	
	@GetMapping("/replyList")
	public List<ReplyBoardVO> replyList(int no){
		List<ReplyBoardVO> list=service.replyAllSelect(no);
		return list;
	}
	
	@PostMapping("/replyBoardEditOk")
	public int replyBoardEditOk(ReplyBoardVO vo,HttpSession session) {
		vo.setUserid((String)session.getAttribute("logid"));
		return service.replyEditOk(vo);
	}
}
