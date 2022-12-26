package com.campus.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.campus.myapp.service.MemberService;
import com.campus.myapp.vo.MemberVO;
import com.campus.myapp.vo.ZipcodeVO;

@Controller
public class MemberController {
	// 클래스를 찾아 객체를 생성해주는 어노테이션 : Autowired
	@Autowired
	MemberService service;
	
	@RequestMapping("/member/memberForm")
	public String memberForm() {
		// 회원정보 수정 뷰페이지
		return "member/memberForm";
	}
	@RequestMapping("/member/idCheck")
	public String idCheck(@RequestParam("userid") String userid,Model model) {
		// DB에 userid가 있는지 확인
		int result=service.idCheck(userid);
		
		model.addAttribute("userid", userid);  
		model.addAttribute("result", result);  // 아이디 개수(0 or 1)
		return "member/idCheck";
	}
	// 우편번호 검색
	@GetMapping("/member/zipcodeSearch")
	public ModelAndView zipSearch(String doroName) {
		ModelAndView mav=new ModelAndView();
		
		List<ZipcodeVO> zipList=null;
		// 도로명주소가 있는지 확인
		if(doroName!=null && !doroName.equals("")) {   // 도로명주소가 있음
			zipList=service.zipSearch(doroName);
		}
		mav.addObject("zipList", zipList);    // 도로명이 존재하면 vo담긴 List가 리턴됨
		
		mav.setViewName("member/zipSearch");
		return mav;
	}
	// 회원등록
	//@PostMapping("/member/memberFormOk")
	@RequestMapping(value="/member/memberFormOk", method=RequestMethod.POST)
	public ModelAndView memberWrite(MemberVO vo) {
		int result=service.memberWrite(vo);
		ModelAndView mav=new ModelAndView();
		if(result>0) {
			mav.setViewName("redirect:/");   // 뷰페이지 이름은 jsp 파일명 / 다른 컨트롤러 매핑으로 이동(HomeController)
		}else {
			mav.setViewName("member/memberWriteResult");
		}
		return mav;
	}
	
	// 로그인 폼
	@GetMapping("/member/login")
	public String login() {
		return "member/login";
	}
	
	// 로그인(DB)
	@PostMapping("/member/loginOk")
	public ModelAndView loginOk(MemberVO vo, HttpSession session) {
		// 아이디와 비밀번호가 일치하면 이름과 아이디 선택
		MemberVO result=service.loginOk(vo);
		ModelAndView mav=new ModelAndView();
		if(result==null) {
			mav.setViewName("redirect:login");
		}else {
			// 세션에 아이디와 비번 세팅
			session.setAttribute("logid", result.getUserid());
			session.setAttribute("logname", result.getUsername());
			session.setAttribute("logStatus", "Y");
			mav.setViewName("redirect:/");
		}
		return mav;
	}
	
	// 로그아웃
	@RequestMapping("/member/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
	
	// 회원정보 폼
	@RequestMapping("/member/selectForm")
	public ModelAndView selectForm(MemberVO vo,HttpSession session){
		vo.setUserid((String)session.getAttribute("logid"));
		ModelAndView mav=new ModelAndView();
		mav.addObject("userid", vo.getUserid());
		mav.addObject("vo", service.selectForm(vo));
		mav.setViewName("member/selectForm");
		
		return mav;
	}
	
	// 회원정보 수정
	@PostMapping("/member/updateForm")
	public ModelAndView updateForm(MemberVO vo,HttpSession session) {
		vo.setUserid((String)session.getAttribute("logid"));
		ModelAndView mav=new ModelAndView();
		int result=service.updateForm(vo);
		if(result>0) {
			mav.addObject("userid", vo.getUserid());
			mav.setViewName("redirect:/");
		}else {
			mav.setViewName("member/selectForm");
		}
		return mav;
	}
}
