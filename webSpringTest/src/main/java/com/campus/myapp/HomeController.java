package com.campus.myapp;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller    // @Controller : ������̼�
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//    localhost:9090/myapp/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	//    localhost:9090/myapp/test
	// ��ȯ���� �� ������ ���ϸ��� ��
	@RequestMapping("/test")   // /test�� get������� �����Ѵٴ� ��
	public String test(Model model) {
		// �Ű������� �ִ� model��ü�� �����͸� �����ϸ� �� ���������� �ƿ��� �� ����
		model.addAttribute("name", "�����");
		model.addAttribute("num", 7);
		
		//<beans:property name="prefix" value="/WEB-INF/views/" />
		//<beans:property name="suffix" value=".jsp" />
		return "controlTest/viewResult";
	}
	// 
	@RequestMapping("/test2")
	public ModelAndView test2() {
		ModelAndView mav=new ModelAndView();
		mav.addObject("name", "����");
		mav.addObject("num", 123);
		mav.setViewName("controlTest/viewResult");
		return mav;
	}
	
	// Ŭ���̾�Ʈ�κ��� ������ ��������(request) - �̸��� ��ȣ
	@RequestMapping("/test3")
	public String test3(HttpServletRequest req, Model model) {
		String num=req.getParameter("num");
		String name=req.getParameter("name");
		System.out.println("��ȣ : "+num);
		System.out.println("�̸� : "+name);
		
		model.addAttribute("num", num);
		model.addAttribute("name",name);
		return "controlTest/viewResult";
	}
	// localhost:9090/myapp/test4?num=1234&name=ȫ�浿
	@RequestMapping("/test4")
	public ModelAndView test4(@RequestParam("num") int num,@RequestParam("name") String name) {
		System.out.println("num->"+num);
		System.out.println("name->"+name);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("num",num);
		mav.addObject("name",name);
		mav.setViewName("controlTest/viewResult");
		
		return mav;
	}
	// localhost:9090/myapp/test5?num=1234&name=ȫ�浿 -> �� �������� �����Ͱ� �ڵ����� set��(setName(),setNum())
	@RequestMapping("/test5")
	public String test5(TestVO vo) {
		System.out.println("num->"+vo.getNum());
		System.out.println("name->"+vo.getName());
		
		return "controlTest/viewResult";
	}
	// localhost:9090/myapp/test6?num=1234&name=ȫ�浿  
	@RequestMapping("/test6")
	public String test6(int num,String name) {    // �ּ�â���� request�� ���� �Ű������� ���
		System.out.println("num==="+num);
		System.out.println("name==="+name);
		return "controlTest/viewResult";
	}
}
