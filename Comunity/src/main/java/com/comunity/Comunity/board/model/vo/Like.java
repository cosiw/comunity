package com.comunity.Comunity.board.model.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("like")
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Like {
	private int likeid;
	private int userid;
	private int bid;
	private String crTime;
	private String delTime;
	
}
