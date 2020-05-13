package com.hansung.web.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hansung.web.model.Board;
import com.hansung.web.model.BoardReply;

@Repository
public class BoardReplyDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<BoardReply> getReplyList(int boardNum) {
		return sqlSession.selectList("ReplyMapper.getReplyList", boardNum);
	}

	public void addReply(BoardReply boardReply) {
		sqlSession.insert("ReplyMapper.insertReply", boardReply);
	}
	
	public void deleteReply(int replyNum) {
		sqlSession.delete("ReplyMapper.deleteReply", replyNum);
	}
	
	public BoardReply getReplyByNumber(int replyNum) {
		return sqlSession.selectOne("ReplyMapper.getReplyByNumber", replyNum);
	}
	
	//댓글 작성자 가져오기
	public String getReplyByWriter(int replyNum) {
		return sqlSession.selectOne("ReplyMapper.getReplyByWriter", replyNum);
	}

}