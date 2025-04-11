package com.yedam.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.EventMapper;
import com.yedam.mapper.ReplyMapper;
import com.yedam.vo.EventVO;


// ReplyService 인터페이스를 구현하는 클래스.
public class EventServiceImpl implements EventService{

	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	EventMapper mapper = sqlSession.getMapper(EventMapper.class);
	
    @Override
    public List<Map<String, Object>> eventList() {
        // TODO Auto-generated method stub
        return mapper.selectEvent();
    }

    @Override
    public boolean addEvent(EventVO evo) {
        // TODO Auto-generated method stub
        return mapper.insertEvent(evo) > 0;
    }

    @Override
    public boolean removeEvent(EventVO evo) {
        // TODO Auto-generated method stub
        return mapper.deleteEvent(evo) > 0;
    }

    @Override
    public List<Map<String, Object>> cntPerWriter() {
        // TODO Auto-generated method stub
        return mapper.selectWriter();
    }

    @Override
    public boolean logCreate(Map<String, String> map) {
        // TODO Auto-generated method stub
        return mapper.insertLogging(map) > 0;
    }

}
