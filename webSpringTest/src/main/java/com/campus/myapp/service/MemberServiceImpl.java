package com.campus.myapp.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.campus.myapp.dao.MemberDAO;
import com.campus.myapp.vo.MemberVO;
import com.campus.myapp.vo.ZipcodeVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Inject
	MemberDAO dao;

	@Override
	public int idCheck(String userid) {
		return dao.idCheck(userid);
	}
	@Override
	public List<ZipcodeVO> zipSearch(String doroName) {
		return dao.zipSearch(doroName);
	}
	@Override
	public int memberWrite(MemberVO vo) {
		return dao.memberWrite(vo);
	}
	@Override
	public MemberVO loginOk(MemberVO vo) {
		return dao.loginOk(vo);
	}
	@Override
	public MemberVO selectForm(MemberVO vo) {
		return dao.selectForm(vo);
	}
	@Override
	public int updateForm(MemberVO vo) {
		return dao.updateForm(vo);
	}
}