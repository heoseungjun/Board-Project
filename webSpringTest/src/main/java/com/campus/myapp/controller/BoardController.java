package com.campus.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.campus.myapp.service.BoardService;
import com.campus.myapp.vo.BoardVO;
import com.campus.myapp.vo.PagingVO;

@Controller
public class BoardController {
	// Service 객체
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/boardList")
	public ModelAndView boardList(PagingVO pvo) {
		ModelAndView mav=new ModelAndView();
		
		// 총 레코드 수 구하기
		pvo.setTotalRecord(service.totalRecord(pvo));
		
		// 페이징, 검색어에 해당하는 레코드만 선택
		mav.addObject("list",service.boardPageSelect(pvo));
		mav.addObject("pvo", pvo);
		mav.setViewName("board/boardList");
		return mav;
	}
	
	@RequestMapping("/board/boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	// 글쓰기
	@PostMapping("/board/boardWriteOk")
	public ModelAndView boardWriteOk(BoardVO vo,HttpServletRequest req) {    // 제목,글내용,아이디(세션),ip(request)
		// ip 설정
		vo.setIp(req.getRemoteAddr());
		// 세션에 기록된 아이디를 userid로 설정
		vo.setUserid((String)req.getSession().getAttribute("logid"));
		ModelAndView mav=new ModelAndView();
		mav.addObject("result",service.boardInsert(vo));
		mav.setViewName("board/boardWriteOk");
		return mav;
	}
	// 글 내용 보기
	@RequestMapping("/board/boardView")
	public ModelAndView boardView(int no) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("vo", service.boardSelect(no));
		mav.setViewName("board/boardView");
		return mav;
	}
	// 글 수정 폼
	@RequestMapping("/board/boardEdit")
	public ModelAndView boardEdit(@RequestParam("no") int no) {
		ModelAndView mav=new ModelAndView();
		// 글 내용 보기 활용
		mav.addObject("vo", service.boardSelect(no));
		mav.setViewName("board/boardEdit");
		return mav;
	}
	// 글 수정
	@RequestMapping(value="/board/boardEditOk",method=RequestMethod.POST)
	public ModelAndView boardEditOk(BoardVO vo,HttpSession session) {    // 자기글만 수정하도록 설정
		vo.setUserid((String)session.getAttribute("logid"));
		ModelAndView mav=new ModelAndView();
		mav.addObject("result", service.boardUpdate(vo));
		mav.addObject("no",vo.getNo());    // 글 내용 보기로 이동하기 위해서
		mav.setViewName("board/boardEditResult");
		return mav;
	}
	// 글 삭제
	@RequestMapping("/board/boardDel")
	public ModelAndView boardDel(int no,HttpSession session) {
		ModelAndView mav=new ModelAndView();
		String userid=(String)session.getAttribute("logid");
		mav.addObject("result", service.boardDel(no,userid));
		mav.setViewName("board/boardDelOk");
		
		return mav;
	}
}
