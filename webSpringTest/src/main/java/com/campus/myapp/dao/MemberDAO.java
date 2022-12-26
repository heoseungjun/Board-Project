package com.campus.myapp.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.campus.myapp.vo.MemberVO;
import com.campus.myapp.vo.ZipcodeVO;

public interface MemberDAO {
	// ���̵� �ߺ� �˻�
	public int idCheck(String userid);
	// �����ȣ �˻�
	public List<ZipcodeVO> zipSearch(String doroName);
	// ȸ�����
	public int memberWrite(MemberVO vo);
	// �α���
	public MemberVO loginOk(MemberVO vo);
	// ȸ������ �����ֱ�
	public MemberVO selectForm(MemberVO vo);
	// ȸ������ ����
	public int updateForm(MemberVO vo);
}
