package com.campus.myapp.service;

import java.util.List;

import com.campus.myapp.vo.ReplyBoardVO;

public interface ReplyBoardService {
	public int replyBoardWrite(ReplyBoardVO vo);
	public List<ReplyBoardVO> replyAllSelect(int no);
	public int replyEditOk(ReplyBoardVO vo);
}
