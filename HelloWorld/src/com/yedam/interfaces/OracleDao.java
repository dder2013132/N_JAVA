package com.yedam.interfaces;
/*
 * 2차 프로젝트.
 * 등록(add), 수정(modify), 삭제(delete)
 */
public class OracleDao implements Dao {
	@Override
	public void insert() {
		System.out.println("oracle 용 등록.");
	}
	@Override
	public void update() {
		System.out.println("oracle 용 수정.");
	}
	@Override
	public void delete() {
		System.out.println("oracle 용 삭제.");
	}
}
