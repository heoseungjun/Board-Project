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
	// Service ��ü
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/boardList")
	public ModelAndView boardList(PagingVO pvo) {
		ModelAndView mav=new ModelAndView();
		
		// �� ���ڵ� �� ���ϱ�
		pvo.setTotalRecord(service.totalRecord(pvo));
		
		// ����¡, �˻�� �ش��ϴ� ���ڵ常 ����
		mav.addObject("list",service.boardPageSelect(pvo));
		mav.addObject("pvo", pvo);
		mav.setViewName("board/boardList");
		return mav;
	}
	
	@RequestMapping("/board/boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	// �۾���
	@PostMapping("/board/boardWriteOk")
	public ModelAndView boardWriteOk(BoardVO vo,HttpServletRequest req) {    // ����,�۳���,���̵�(����),ip(request)
		// ip ����
		vo.setIp(req.getRemoteAddr());
		// ���ǿ� ��ϵ� ���̵� userid�� ����
		vo.setUserid((String)req.getSession().getAttribute("logid"));
		ModelAndView mav=new ModelAndView();
		mav.addObject("result",service.boardInsert(vo));
		mav.setViewName("board/boardWriteOk");
		return mav;
	}
	// �� ���� ����
	@RequestMapping("/board/boardView")
	public ModelAndView boardView(int no) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("vo", service.boardSelect(no));
		mav.setViewName("board/boardView");
		return mav;
	}
	// �� ���� ��
	@RequestMapping("/board/boardEdit")
	public ModelAndView boardEdit(@RequestParam("no") int no) {
		ModelAndView mav=new ModelAndView();
		// �� ���� ���� Ȱ��
		mav.addObject("vo", service.boardSelect(no));
		mav.setViewName("board/boardEdit");
		return mav;
	}
	// �� ����
	@RequestMapping(value="/board/boardEditOk",method=RequestMethod.POST)
	public ModelAndView boardEditOk(BoardVO vo,HttpSession session) {    // �ڱ�۸� �����ϵ��� ����
		vo.setUserid((String)session.getAttribute("logid"));
		ModelAndView mav=new ModelAndView();
		mav.addObject("result", service.boardUpdate(vo));
		mav.addObject("no",vo.getNo());    // �� ���� ����� �̵��ϱ� ���ؼ�
		mav.setViewName("board/boardEditResult");
		return mav;
	}
	// �� ����
	@RequestMapping("/board/boardDel")
	public ModelAndView boardDel(int no,HttpSession session) {
		ModelAndView mav=new ModelAndView();
		String userid=(String)session.getAttribute("logid");
		mav.addObject("result", service.boardDel(no,userid));
		mav.setViewName("board/boardDelOk");
		
		return mav;
	}
}
