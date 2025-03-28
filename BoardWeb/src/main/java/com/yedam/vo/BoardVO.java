package com.yedam.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private Date writeDate;
}
