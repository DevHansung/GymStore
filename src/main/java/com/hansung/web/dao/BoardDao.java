package com.hansung.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hansung.web.model.Board;
import com.hansung.web.model.Product;

@Repository
public class BoardDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Board> getBoardList() {
		return sqlSession.selectList("BoardMapper.getBoardList", null);
	}

	public Board getBoardByNumber(int boardNum) {
		return sqlSession.selectOne("BoardMapper.getBoardByNumber", boardNum);
	}
	
	public String getBoardByWriter(int boardNum) {
		return sqlSession.selectOne("BoardMapper.getBoardByWriter", boardNum);
	}

	public void addBoard(Board board) {
		sqlSession.insert("BoardMapper.insertBoard", board);
	}

	public void deleteBoard(Board board) {
		sqlSession.delete("BoardMapper.deleteBoard", board);
	}

	public void updateBoard(Board board) {
		sqlSession.update("BoardMapper.updateBoard", board);
	}

	public void updateViewCount(int boardNum) {
		sqlSession.update("BoardMapper.updateViewCount", boardNum);
	}

	public List<Board> getBoardListPaging(int displayPost, int postNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("displayPost", displayPost);
		map.put("postNum", postNum);
		return sqlSession.selectList("BoardMapper.getBoardListPaging", map);
	}
	
	public int getBoardCount(){
	 return sqlSession.selectOne("BoardMapper.getBoardCount"); 
	}
	


}