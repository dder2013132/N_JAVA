package com.yedam.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	private int replyNo; //댓글번호
	private String reply; //댓글내용.
	private String replyer; //댓글작성자.
	private Date replyDate; //댓글작성일시.
	private int boardNo; //원본글번호.
}
