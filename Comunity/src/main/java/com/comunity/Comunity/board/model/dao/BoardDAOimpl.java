package com.comunity.Comunity.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.comunity.Comunity.board.model.vo.Board;
import com.comunity.Comunity.board.model.vo.Like;
import com.comunity.Comunity.common.Utils;
@Repository
public class BoardDAOimpl implements BoardDAO {

	@Autowired
	SqlSessionTemplate session;
	
	Utils util = new Utils();

	@Override
	public List<Board> selectList(int userid) {

		List<Board> boardList = session.selectList("boardSQL.selectBoardList", userid);		
		for(Board b : boardList) {
			b.getUser().setAccountType(util.getNtoHGrade(b.getUser().getAccountType()));
		}
	
		return boardList;
	}

	@Override
	public int insertBoard(Board board) {
		
		return session.insert("boardSQL.insertBoard", board);
	}

	@Override
	public int updateBoard(Board board, int userid) {

		int result = session.selectOne("boardSQL.checkUserid", board.getBid());
		
		if(result != userid)	return -1;
		
		return session.update("boardSQL.updateBoard", board);
	}

	@Override
	public int deleteBoard(int bid, int userid) {
		
		int result = session.selectOne("boardSQL.checkUserid", bid);

		if(result != userid)	return -1;

		return session.delete("boardSQL.deleteBoard", bid);
	}


	@Override
	public int likeCheck(Like like) {
		
		Like checkLike = session.selectOne("boardSQL.selectLike", like);
		
		
		if(checkLike == null) {
			// 좋아요 데이터가 없을 경우
			session.update("boardSQL.addLikeCount", like.getBid());
			return session.insert("boardSQL.insertLike", like);
		} else {
			// 좋아요 데이터가 있을 경우
			int likeid = checkLike.getLikeid();
			int bid = checkLike.getBid();
			
			// 좋아요 -> 좋아요 취소 
			if(checkLike.getDelTime().equals("0000-00-00 00:00:00")) {
				session.update("boardSQL.subLikeCount", bid);
				return session.update("boardSQL.updateUnLike", likeid);
			//좋아요 -> 좋아요 취소 
			} else {
				session.update("boardSQL.addLikeCount", bid);
				return session.update("boardSQL.updateLike", likeid);
			}
		}
		
	}


}
