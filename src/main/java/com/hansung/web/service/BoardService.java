package com.hansung.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansung.web.dao.BoardDao;
import com.hansung.web.model.Board;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public Board getBoardByNumber(int boardNum) {
		return boardDao.getBoardByNumber(boardNum);
	}

	public String getBoardByWriter(int boardNum) {
		return boardDao.getBoardByWriter(boardNum);
	}

	public List<Board> getBoardList() {
		return boardDao.getBoardList();
	}

	public void addBoard(Board board) {
		boardDao.addBoard(board);
	}

	public void deleteBoard(Board board) {
		boardDao.deleteBoard(board);
	}

	public void updateBoard(Board board) {
		boardDao.updateBoard(board);
	}

	public void updateViewCount(int boardNum) {
		boardDao.updateViewCount(boardNum);
	}

	public List<Board> getBoardListPaging(int displayPost, int postNum) {
		return boardDao.getBoardListPaging(displayPost, postNum);
	}

	public int getBoardCount() {
		return boardDao.getBoardCount();
	}

}
