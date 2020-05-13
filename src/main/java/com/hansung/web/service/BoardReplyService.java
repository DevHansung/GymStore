package com.hansung.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansung.web.dao.BoardReplyDao;
import com.hansung.web.model.Board;
import com.hansung.web.model.BoardReply;

@Service
public class BoardReplyService {

	@Autowired
	private BoardReplyDao boardReplyDao;

	public List<BoardReply> getReplyList(int boardNum) {
		return boardReplyDao.getReplyList(boardNum);
	}

	public void addReply(BoardReply boardReply) {
		boardReplyDao.addReply(boardReply);
	}
	
	public void deleteReply(int replyNum) {
		boardReplyDao.deleteReply(replyNum);
	}

	public BoardReply getReplyByNumber(int replyNum) {
		return boardReplyDao.getReplyByNumber(replyNum);
	}
	
	public String getReplyByWriter(int replyNum) {
		return boardReplyDao.getReplyByWriter(replyNum);
	}

}
