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
	// Ŭ������ ã�� ��ü�� �������ִ� ������̼� : Autowired
	@Autowired
	MemberService service;
	
	@RequestMapping("/member/memberForm")
	public String memberForm() {
		// ȸ������ ���� ��������
		return "member/memberForm";
	}
	@RequestMapping("/member/idCheck")
	public String idCheck(@RequestParam("userid") String userid,Model model) {
		// DB�� userid�� �ִ��� Ȯ��
		int result=service.idCheck(userid);
		
		model.addAttribute("userid", userid);  
		model.addAttribute("result", result);  // ���̵� ����(0 or 1)
		return "member/idCheck";
	}
	// �����ȣ �˻�
	@GetMapping("/member/zipcodeSearch")
	public ModelAndView zipSearch(String doroName) {
		ModelAndView mav=new ModelAndView();
		
		List<ZipcodeVO> zipList=null;
		// ���θ��ּҰ� �ִ��� Ȯ��
		if(doroName!=null && !doroName.equals("")) {   // ���θ��ּҰ� ����
			zipList=service.zipSearch(doroName);
		}
		mav.addObject("zipList", zipList);    // ���θ��� �����ϸ� vo��� List�� ���ϵ�
		
		mav.setViewName("member/zipSearch");
		return mav;
	}
	// ȸ�����
	//@PostMapping("/member/memberFormOk")
	@RequestMapping(value="/member/memberFormOk", method=RequestMethod.POST)
	public ModelAndView memberWrite(MemberVO vo) {
		int result=service.memberWrite(vo);
		ModelAndView mav=new ModelAndView();
		if(result>0) {
			mav.setViewName("redirect:/");   // �������� �̸��� jsp ���ϸ� / �ٸ� ��Ʈ�ѷ� �������� �̵�(HomeController)
		}else {
			mav.setViewName("member/memberWriteResult");
		}
		return mav;
	}
	
	// �α��� ��
	@GetMapping("/member/login")
	public String login() {
		return "member/login";
	}
	
	// �α���(DB)
	@PostMapping("/member/loginOk")
	public ModelAndView loginOk(MemberVO vo, HttpSession session) {
		// ���̵�� ��й�ȣ�� ��ġ�ϸ� �̸��� ���̵� ����
		MemberVO result=service.loginOk(vo);
		ModelAndView mav=new ModelAndView();
		if(result==null) {
			mav.setViewName("redirect:login");
		}else {
			// ���ǿ� ���̵�� ��� ����
			session.setAttribute("logid", result.getUserid());
			session.setAttribute("logname", result.getUsername());
			session.setAttribute("logStatus", "Y");
			mav.setViewName("redirect:/");
		}
		return mav;
	}
	
	// �α׾ƿ�
	@RequestMapping("/member/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
	
	// ȸ������ ��
	@RequestMapping("/member/selectForm")
	public ModelAndView selectForm(MemberVO vo,HttpSession session){
		vo.setUserid((String)session.getAttribute("logid"));
		ModelAndView mav=new ModelAndView();
		mav.addObject("userid", vo.getUserid());
		mav.addObject("vo", service.selectForm(vo));
		mav.setViewName("member/selectForm");
		
		return mav;
	}
	
	// ȸ������ ����
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
