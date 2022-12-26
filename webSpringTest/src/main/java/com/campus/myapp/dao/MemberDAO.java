package com.campus.myapp.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.campus.myapp.vo.MemberVO;
import com.campus.myapp.vo.ZipcodeVO;

public interface MemberDAO {
	// 아이디 중복 검사
	public int idCheck(String userid);
	// 우편번호 검색
	public List<ZipcodeVO> zipSearch(String doroName);
	// 회원등록
	public int memberWrite(MemberVO vo);
	// 로그인
	public MemberVO loginOk(MemberVO vo);
	// 회원정보 보여주기
	public MemberVO selectForm(MemberVO vo);
	// 회원정보 수정
	public int updateForm(MemberVO vo);
}
