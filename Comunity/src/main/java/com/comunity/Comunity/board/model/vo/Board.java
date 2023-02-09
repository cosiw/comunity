package com.comunity.Comunity.board.model.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("board")
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Board {

	private int bid;
	private String title;
	private String contents;
	private int userid;
	private int likeCount;
	private User user;
	private String crTime;
	private String modTime;
	private String delTime;
	private int likeChecked;
	
	
	public Board(int bid, String title, String contents, int userid, int likeCount, String crTime, String modTime, String delTime) {
		this.bid = bid;
		this.title = title;
		this.contents = contents;
		this.userid = userid;
		this.likeCount = likeCount;
		this.crTime = crTime;
		this.modTime = modTime;
		this.delTime = delTime;
	}
}


