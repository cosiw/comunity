package com.comunity.Comunity.board.model.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Header {

	private String auth;
	private int userid;
}
