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
	//                           ������ String�� �� �ѱ� ���� ó��(����->Ŭ���̾�Ʈ)
	@RequestMapping(value="/ajaxString", method=RequestMethod.GET, produces="application/text;charset=UTF-8")
	@ResponseBody    // String, ModelAndView�� ���ϵǴ� ��� jsp ���ϸ��� �ִٴ� ��
					 // �񵿱�� : ���������� �ʿ� ���� -> ���ϵǴ� ��ü�� �� ��������� �ǹ��� ������̼� �ʿ�, �����ּҿ��� ���ؽ�Ʈ ���ֱ�
	public String ajaxString(int num,String name) {
		System.out.println("num->"+num);
		System.out.println("name->"+name);
		
		return "<h1>��ȣ : "+num+"</h1><h2>�̸� : "+name+"</h2>";
	}
	
	@GetMapping("/ajaxObject")
	@ResponseBody
	public BoardVO ajaxObject(String username,String tel,int age) {
		System.out.println(username+","+tel+","+age);
		BoardVO vo=new BoardVO();
		vo.setNo(1234);
		vo.setSubject("��¥ ������");
		vo.setUserid("�������");
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
		// �� ���ڵ� ��(PagingVO)�� �� ����(BoardVO)�� ����
		int totalRecord=service.totalRecord(pvo);
		BoardVO vo = service.boardSelect(39);
		
		HashMap map = new HashMap();
		map.put("totalRecord",totalRecord);
		map.put("boardVO", vo);
		map.put("msg","���� �̿��� �񵿱�� ó��");
		
		return map;
	}
}
