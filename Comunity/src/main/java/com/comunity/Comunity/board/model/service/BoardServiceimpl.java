package com.comunity.Comunity.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunity.Comunity.board.model.dao.BoardDAO;
import com.comunity.Comunity.board.model.vo.Board;
import com.comunity.Comunity.board.model.vo.Like;

@Service
public class BoardServiceimpl implements BoardService {

	@Autowired
	BoardDAO dao;


	@Override
	public List<Board> selectList(int userid) {
		
		return dao.selectList(userid);
	}

	@Override
	public int insertBoard(Board board) {
		
		return dao.insertBoard(board);
	}

	@Override
	public int updateBoard(Board board, int userid) {
		
		return dao.updateBoard(board, userid);
	}

	@Override
	public int deleteBoard(int bid, int userid) {
		
		return dao.deleteBoard(bid, userid);
	}

	@Override
	public int likeCheck(Like like) {
	
		return dao.likeCheck(like);
	}

	
	

}
