package com.yedam.common;

import lombok.Getter;
import lombok.ToString;

// 게시글 건수에 따른 페이지 갯수.
@Getter
@ToString
public class PageDTO {
	private int startPage; // 시작페이지.
	private int endPage;
	private int currentPage; // 현재페이지.
	private boolean prev, next; // 이전, 이후 10개 페이지여부.

	public PageDTO(int totalCnt, int page) {
		currentPage = page;
		endPage = (int) (Math.ceil(page / 10.0)) * 10;
		startPage = endPage - 9;
		int realEnd = (int) (Math.ceil(totalCnt / 5.0));
		endPage = endPage > realEnd ? realEnd : endPage; // 계산한 마지막페이지? 실제 마지막 페이지.
		// 이전 10개 페이지의 유무.
		prev = startPage > 1 ? true : false;
		next = endPage < realEnd ? true : false;
	}
}
