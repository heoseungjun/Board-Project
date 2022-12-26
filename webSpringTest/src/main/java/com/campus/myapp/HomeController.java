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

@Controller    // @Controller : 어노테이션
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//    localhost:9090/myapp/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	//    localhost:9090/myapp/test
	// 반환형은 뷰 페이지 파일명이 됨
	@RequestMapping("/test")   // /test를 get방식으로 접속한다는 뜻
	public String test(Model model) {
		// 매개변수로 있는 model객체에 데이터를 세팅하면 뷰 페이지에서 아용할 수 있음
		model.addAttribute("name", "손흥민");
		model.addAttribute("num", 7);
		
		//<beans:property name="prefix" value="/WEB-INF/views/" />
		//<beans:property name="suffix" value=".jsp" />
		return "controlTest/viewResult";
	}
	// 
	@RequestMapping("/test2")
	public ModelAndView test2() {
		ModelAndView mav=new ModelAndView();
		mav.addObject("name", "벤투");
		mav.addObject("num", 123);
		mav.setViewName("controlTest/viewResult");
		return mav;
	}
	
	// 클라이언트로부터 데이터 가져오기(request) - 이름과 번호
	@RequestMapping("/test3")
	public String test3(HttpServletRequest req, Model model) {
		String num=req.getParameter("num");
		String name=req.getParameter("name");
		System.out.println("번호 : "+num);
		System.out.println("이름 : "+name);
		
		model.addAttribute("num", num);
		model.addAttribute("name",name);
		return "controlTest/viewResult";
	}
	// localhost:9090/myapp/test4?num=1234&name=홍길동
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
	// localhost:9090/myapp/test5?num=1234&name=홍길동 -> 이 과정에서 데이터가 자동으로 set됨(setName(),setNum())
	@RequestMapping("/test5")
	public String test5(TestVO vo) {
		System.out.println("num->"+vo.getNum());
		System.out.println("name->"+vo.getName());
		
		return "controlTest/viewResult";
	}
	// localhost:9090/myapp/test6?num=1234&name=홍길동  
	@RequestMapping("/test6")
	public String test6(int num,String name) {    // 주소창에서 request한 값이 매개변수로 담김
		System.out.println("num==="+num);
		System.out.println("name==="+name);
		return "controlTest/viewResult";
	}
}
