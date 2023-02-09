package com.comunity.Comunity.board.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.comunity.Comunity.board.model.service.BoardService;
import com.comunity.Comunity.board.model.vo.Board;
import com.comunity.Comunity.board.model.vo.Header;
import com.comunity.Comunity.board.model.vo.Like;
import com.comunity.Comunity.common.Utils;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	Utils util = new Utils();

	@RequestMapping(value="/board", method = RequestMethod.POST)
	public ResponseEntity insertBoard(@RequestHeader(value="Authentication") String auth, Board board) {
		
		Header header =	new Header();
		
		header = util.AuthtoHeader(auth);
		
		// header에 대한 내용 체크
		if(header == null || header.getUserid() == 0 || header.getAuth() == null) {
			return new ResponseEntity("no auth", HttpStatus.UNAUTHORIZED);
		}
		
		// body에 대한 내용 체크
		if(board.getTitle() == null || board.getContents() == null) {
			return new ResponseEntity("bad Request", HttpStatus.BAD_REQUEST);
		}
		board.setUserid(header.getUserid());
		
		int result = service.insertBoard(board);
		
		// 게시글 생성이 되지 않을 경우 
		if(result == 0 ) return new ResponseEntity(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity(result, HttpStatus.OK);

	}
	
	@RequestMapping(value="/board", method = RequestMethod.PUT)
	public ResponseEntity updateBoard(@RequestHeader(value="Authentication") String auth,@RequestParam int bid, Board board) {
		board.setBid(bid);
		Header header =	new Header();
		
		header = util.AuthtoHeader(auth);
		// header에 대한 내용 체크
		if(header == null || header.getUserid() == 0 || header.getAuth() == null) {
			return new ResponseEntity("no Auth",HttpStatus.UNAUTHORIZED);
		}
		// body에 대한 내용 체크
		if(board.getTitle() == null || board.getContents() == null) {
			return new ResponseEntity("bad Request", HttpStatus.BAD_REQUEST);
		}
		
		int userid = header.getUserid();
		
		int result = service.updateBoard(board, userid);
		if(result == 0) return new ResponseEntity("bad Request",HttpStatus.BAD_REQUEST);
		
		if(result == -1) return new ResponseEntity("no Auth",HttpStatus.UNAUTHORIZED);
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/board", method = RequestMethod.DELETE)
	public ResponseEntity deleteBoard(@RequestHeader(value="Authentication") String auth,@RequestParam int bid) {

		if(bid == 0) {
			return new ResponseEntity("bad Request", HttpStatus.BAD_REQUEST);
		}
		
		Header header =	new Header();
		header = util.AuthtoHeader(auth);
		
		if(header == null || header.getUserid() == 0 || header.getAuth() == null) {
			return new ResponseEntity("no Auth",HttpStatus.UNAUTHORIZED);
		}
		
		int userid = header.getUserid();
		
		int result = service.deleteBoard(bid, userid);
		
		if(result == -1) return new ResponseEntity("no Auth", HttpStatus.UNAUTHORIZED);
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/board", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Board>> getBoardList(@RequestHeader(value="Authentication") String auth) {
		
		Header header =	util.AuthtoHeader(auth);
		int userid = header.getUserid();
		
		List<Board> boardList = new ArrayList<Board>();
		boardList = service.selectList(userid);
		
		return new ResponseEntity(boardList, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/like", method = RequestMethod.POST)
	public ResponseEntity likeCheck(@RequestHeader(value="Authentication") String auth, Like like) {
		
		Header header =	util.AuthtoHeader(auth);
		
		like.setUserid(header.getUserid());
		
		int result = service.likeCheck(like);
		
		return new ResponseEntity(result, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
