package com.yedam.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {
	private int page;
	private String searchCondition;
	private String keyword;
	private int boardNo;
}
