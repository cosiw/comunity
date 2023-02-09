package com.comunity.Comunity.board.model.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("user")
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	private int userid;
	private String accountid;
	private String nickName;
	private String accountType;
	private int quit;
	
}
