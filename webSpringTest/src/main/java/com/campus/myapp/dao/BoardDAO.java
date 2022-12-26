package com.campus.myapp.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.campus.myapp.vo.BoardVO;
import com.campus.myapp.vo.PagingVO;

public interface BoardDAO {
	public int boardInsert(BoardVO vo);
	// 해당 페이지 레코드 선택
	public List<BoardVO> boardPageSelect(PagingVO pvo);
	// 총 레코드 수
	public int totalRecord(PagingVO pvo);
	// 글 선택
	public BoardVO boardSelect(int no);
	// 글 수정
	public int boardUpdate(BoardVO vo);
	// 글 삭제
	public int boardDel(int no,String userid);
}
