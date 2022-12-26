package com.campus.myapp.dao;

import java.util.List;

import com.campus.myapp.vo.ReplyBoardVO;

public interface ReplyBoardDAO {
	public int replyBoardWrite(ReplyBoardVO vo);
	public List<ReplyBoardVO> replyAllSelect(int no);
	public int replyEditOk(ReplyBoardVO vo);
}
