package com.comunity.Comunity.board.model.dao;

import java.util.List;

import com.comunity.Comunity.board.model.vo.Board;
import com.comunity.Comunity.board.model.vo.Like;

public interface BoardDAO {


	List<Board> selectList(int userid);
	int insertBoard(Board board);
	int updateBoard(Board board, int userid);
	int deleteBoard(int bid, int userid);
	int likeCheck(Like like);
}
