package com.yedam.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
// lombok설치 & 라이브러리 다운로드.
@Getter
@Setter
@ToString
public class BoardVO {
	private int boardNo; // board_no
	private String title;
	private String content;
	private String writer;
	private Date writeDate; // write_date
}
