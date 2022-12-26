package com.campus.myapp.service;

import java.util.List;

import com.campus.myapp.vo.BoardVO;
import com.campus.myapp.vo.PagingVO;

public interface BoardService {
	public int boardInsert(BoardVO vo);
	public List<BoardVO> boardPageSelect(PagingVO pvo);
	public int totalRecord(PagingVO pvo);
	public BoardVO boardSelect(int no);
	public int boardUpdate(BoardVO vo);
	public int boardDel(int no,String userid);
}
