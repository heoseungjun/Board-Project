package com.campus.myapp.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.campus.myapp.vo.BoardVO;
import com.campus.myapp.vo.PagingVO;

public interface BoardDAO {
	public int boardInsert(BoardVO vo);
	// �ش� ������ ���ڵ� ����
	public List<BoardVO> boardPageSelect(PagingVO pvo);
	// �� ���ڵ� ��
	public int totalRecord(PagingVO pvo);
	// �� ����
	public BoardVO boardSelect(int no);
	// �� ����
	public int boardUpdate(BoardVO vo);
	// �� ����
	public int boardDel(int no,String userid);
}
