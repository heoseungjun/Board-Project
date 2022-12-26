package com.campus.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.campus.myapp.dao.ReplyBoardDAO;
import com.campus.myapp.vo.ReplyBoardVO;

@Service
public class ReplyBoardServiceImpl implements ReplyBoardService {
	@Inject
	ReplyBoardDAO dao;

	@Override
	public int replyBoardWrite(ReplyBoardVO vo) {
		return dao.replyBoardWrite(vo);
	}

	@Override
	public List<ReplyBoardVO> replyAllSelect(int no) {
		return dao.replyAllSelect(no);
	}

	@Override
	public int replyEditOk(ReplyBoardVO vo) {
		return dao.replyEditOk(vo);
	}
}
