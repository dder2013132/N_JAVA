package com.yedam.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.ReplyMapper;
import com.yedam.vo.ReplyVO;

public class AppMain {
	public static void main(String[] args) {
//		MemberService svc = new MemberServiceImpl();
//		MemberVO member = svc.login("user01", "1111");
//
//		System.out.println(member.toString());
	    SqlSession sqlSession = DataSource.getInstance().openSession(true);
	    ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
	    
	    ReplyVO rvo = new ReplyVO();
	    rvo.setBoardNo(112);
	    rvo.setReply("112번글 입력~");
	    rvo.setReplyer("user03");
//	    int cnt = mapper.insertReply(rvo);
	    int cnt = mapper.deleteReply(4);
	    if (cnt > 0) {
	        System.out.println("삭제성공 ╮(╯▽╰)╭");
	    }
	    
	    List<ReplyVO> list = mapper.selectList(112);
	    for (ReplyVO rep : list) {
	        System.out.println(rep);
	    }
	}
}
