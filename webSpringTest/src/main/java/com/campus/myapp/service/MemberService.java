package com.campus.myapp.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.campus.myapp.vo.MemberVO;
import com.campus.myapp.vo.ZipcodeVO;

public interface MemberService {
	public int idCheck(String userid);
	public List<ZipcodeVO> zipSearch(String doroName);
	public int memberWrite(MemberVO vo);
	public MemberVO loginOk(MemberVO vo);
	public MemberVO selectForm(MemberVO vo);
	public int updateForm(MemberVO vo);
}
